package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.QrCode;
import studio.banner.officialwebsite.mapper.QrCodeMapper;
import studio.banner.officialwebsite.service.IQrCodeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hyy
 * @date 2021/5/20 16:46
 * @role
 */
@Service
public class QrCodeServiceImpl implements IQrCodeService {
    protected static final Logger logger = LoggerFactory.getLogger(QrCodeServiceImpl.class);
    @Resource
    private QrCodeMapper qrCodeMapper;

    @Override
    public boolean insert(QrCode qrCode) {
        QueryWrapper<QrCode> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("photo_name", qrCode.getPhotoName());
        List<QrCode> list = qrCodeMapper.selectList(queryWrapper);
        if (list.size()==0) {
            return qrCodeMapper.insert(qrCode)==1;
        }
        return false;
    }

    @Override
    public boolean delete(String imageName) {
        QueryWrapper<QrCode> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("photo_name", imageName);
        List<QrCode> list = qrCodeMapper.selectList(queryWrapper);
        if (list.size() != 0) {
            return qrCodeMapper.delete(queryWrapper) >= 0;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer qrCodeId) {
        LambdaQueryWrapper<QrCode> queryWrapper = Wrappers.lambdaQuery();
        return qrCodeMapper.delete(queryWrapper.eq(QrCode::getId,qrCodeId)) == 1;
    }

    @Override
    public boolean update(QrCode qrCode) {
        return qrCodeMapper.updateById(qrCode) == 1;
    }

    @Override
    public QrCode select(String key) {
        QueryWrapper<QrCode> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("photo_name", key);
        List<QrCode> list = qrCodeMapper.selectList(queryWrapper);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<QrCode> selectAll() {
        return qrCodeMapper.selectList(null);
    }

    @Override
    public QrCode selectById(Integer qrCodeId) {
        return qrCodeMapper.selectById(qrCodeId);
    }
}
