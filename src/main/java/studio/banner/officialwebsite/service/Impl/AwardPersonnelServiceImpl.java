package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.AwardPersonnel;
import studio.banner.officialwebsite.mapper.AwardPersonnelMapper;
import studio.banner.officialwebsite.service.IAwardPersonnelService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/27 9:28
 */
@Service
public class AwardPersonnelServiceImpl implements IAwardPersonnelService {
    @Resource
    protected AwardPersonnelMapper awardPersonnelMapper;
    @Override
    public boolean insert(Integer memberId, Integer awardId) {
        List<AwardPersonnel> awardPersonnelList = new LambdaQueryChainWrapper<AwardPersonnel>(awardPersonnelMapper)
                .eq(AwardPersonnel::getAwardId,awardId)
                .eq(AwardPersonnel::getMemberId,memberId).list();
        if (awardPersonnelList.size() == 0) {
            if (awardPersonnelMapper.insert(new AwardPersonnel(memberId,awardId)) == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Integer memberId, Integer awardId) {
        LambdaQueryWrapper<AwardPersonnel> queryWrapper = Wrappers.lambdaQuery();
        return awardPersonnelMapper.delete(queryWrapper.eq(AwardPersonnel::getAwardId,awardId).eq(AwardPersonnel::getMemberId,memberId)) == 1;
    }

    @Override
    public boolean deleteAll(Integer awardId) {
        LambdaQueryWrapper<AwardPersonnel> queryWrapper = Wrappers.lambdaQuery();
        return awardPersonnelMapper.delete(queryWrapper.eq(AwardPersonnel::getAwardId,awardId)) >= 0;
    }

    @Override
    public boolean deleteAllByMemberId(Integer memberId) {
        LambdaQueryWrapper<AwardPersonnel> queryWrapper = Wrappers.lambdaQuery();
        int delete = awardPersonnelMapper.delete(queryWrapper.eq(AwardPersonnel::getMemberId, memberId));
        if (delete != 0) {
            System.out.println(delete);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Integer> select(Integer awardId) {
        List<AwardPersonnel> awardPersonnelList = new LambdaQueryChainWrapper<AwardPersonnel>(awardPersonnelMapper)
                .eq(AwardPersonnel::getAwardId,awardId).list();
        List<Integer> memberId = new ArrayList<>();
        for (AwardPersonnel awardPersonnel :awardPersonnelList) {
            memberId.add(awardPersonnel.getMemberId());
        }
        return memberId;
    }
}
