package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.SoftwareCopyrightPersonnel;
import studio.banner.officialwebsite.mapper.SoftwareCopyrightPersonnelMapper;
import studio.banner.officialwebsite.service.ISoftwareCopyrightPersonnelService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/27 8:22
 */
@Service
public class SoftwareCopyrightPersonnelServiceImpl implements ISoftwareCopyrightPersonnelService {
    @Autowired
    protected SoftwareCopyrightPersonnelMapper softwareCopyrightPersonnelMapper;
    @Override
    public boolean insert(Integer memberId, Integer softwareCopyrightId) {
        List<SoftwareCopyrightPersonnel> personnelList = new LambdaQueryChainWrapper<SoftwareCopyrightPersonnel>(softwareCopyrightPersonnelMapper)
                .eq(SoftwareCopyrightPersonnel::getSoftwareCopyrightId,softwareCopyrightId)
                .eq(SoftwareCopyrightPersonnel::getMemberId,memberId).list();
        if (personnelList.size() == 0) {
            SoftwareCopyrightPersonnel personnel = new SoftwareCopyrightPersonnel(memberId,softwareCopyrightId);
            return softwareCopyrightPersonnelMapper.insert(personnel) != 0;
        }
        return false;
    }

    @Override
    public boolean delete(Integer memberId, Integer softwareCopyrightId) {
        LambdaQueryWrapper<SoftwareCopyrightPersonnel> queryWrapper = Wrappers.lambdaQuery();
        return softwareCopyrightPersonnelMapper.delete(queryWrapper.eq(SoftwareCopyrightPersonnel::getMemberId,memberId).eq(SoftwareCopyrightPersonnel::getSoftwareCopyrightId,softwareCopyrightId)) != 0;
    }

    @Override
    public boolean deleteAllByMemberId(Integer memberId) {
        LambdaQueryWrapper<SoftwareCopyrightPersonnel> queryWrapper = Wrappers.lambdaQuery();
        return softwareCopyrightPersonnelMapper.delete(queryWrapper.eq(SoftwareCopyrightPersonnel::getMemberId,memberId)) >= 0;
    }

    @Override
    public boolean deleteAllBySoftwareCopyrightId(Integer softwareCopyrightId) {
        LambdaQueryWrapper<SoftwareCopyrightPersonnel> queryWrapper = Wrappers.lambdaQuery();
        return softwareCopyrightPersonnelMapper.delete(queryWrapper.eq(SoftwareCopyrightPersonnel::getSoftwareCopyrightId,softwareCopyrightId)) >= 0;
    }

    @Override
    public List<Integer> select(Integer softwareCopyrightId) {
        List<SoftwareCopyrightPersonnel> personnelList = new LambdaQueryChainWrapper<SoftwareCopyrightPersonnel>(softwareCopyrightPersonnelMapper)
                .eq(SoftwareCopyrightPersonnel::getSoftwareCopyrightId,softwareCopyrightId).list();
        List<Integer> memberId = new ArrayList<>();
        for (SoftwareCopyrightPersonnel personnel :personnelList) {
            memberId.add(personnel.getMemberId());
        }
        return memberId;
    }
}
