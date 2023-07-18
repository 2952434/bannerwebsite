package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.JournalismPhoto;
import studio.banner.officialwebsite.mapper.JournalismPhotoMapper;
import studio.banner.officialwebsite.service.IJournalismPhotoService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/18 18:28
 */
@Service
public class JournalismPhotoServiceImpl implements IJournalismPhotoService {
    @Autowired
    protected JournalismPhotoMapper journalismPhotoMapper;
    @Override
    public boolean insert(JournalismPhoto journalismPhoto) {
        return journalismPhotoMapper.insert(journalismPhoto) != 0;
    }

    @Override
    public boolean delete(Integer id) {
        return journalismPhotoMapper.deleteById(id) != 0;
    }

    @Override
    public boolean deleteAll(Integer journalismId) {
        LambdaQueryWrapper<JournalismPhoto> queryWrapper = Wrappers.lambdaQuery();
        return journalismPhotoMapper.delete(queryWrapper.eq(JournalismPhoto::getJournalismId,journalismId)) != 0;
    }

    @Override
    public boolean update(JournalismPhoto journalismPhoto) {
        return journalismPhotoMapper.updateById(journalismPhoto) != 0;
    }

    @Override
    public JournalismPhoto select(Integer id) {
        return journalismPhotoMapper.selectById(id);
    }

    @Override
    public List<JournalismPhoto> selectAll(Integer id) {
        return new LambdaQueryChainWrapper<JournalismPhoto>(journalismPhotoMapper)
                .eq(JournalismPhoto::getJournalismId,id).list();
    }

    @Override
    public List<JournalismPhoto> selectIsCarousel() {
        List<JournalismPhoto> journalismPhotos = new LambdaQueryChainWrapper<>(journalismPhotoMapper)
                .eq(JournalismPhoto::getIsCarousel, 1).list();
        // 将新闻对象通过id降序排列
        Collections.sort(journalismPhotos, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                JournalismPhoto journalismPhoto1=(JournalismPhoto) o1;
                JournalismPhoto journalismPhoto2=(JournalismPhoto) o2;
                if (journalismPhoto1.getId()>journalismPhoto2.getId()){
                    return -1 ;
                } else if (journalismPhoto1.getId().equals(journalismPhoto2.getId())){
                    return 0 ;
                } else {
                    return 1 ;
                }
            }
        });
        return journalismPhotos;
    }

}
