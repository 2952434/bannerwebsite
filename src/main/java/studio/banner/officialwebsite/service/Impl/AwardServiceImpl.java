package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.Award;
import studio.banner.officialwebsite.mapper.AwardMapper;
import studio.banner.officialwebsite.service.IAwardService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/10 21:44
 * @role: 获奖信息Service层实现
 */
@Service
public class AwardServiceImpl implements IAwardService {
    @Autowired
    protected AwardMapper awardMapper;
    @Override
    public boolean insert(Award award) {
        List<Award> awardList = new LambdaQueryChainWrapper<Award>(awardMapper)
                .eq(Award::getYear,award.getYear())
                .eq(Award::getMonth,award.getMonth())
                .eq(Award::getDay,award.getDay())
                .eq(Award::getAwardClassify,award.getAwardClassify())
                .eq(Award::getAwardName,award.getAwardName())
                .eq(Award::getRaceOrProjectName,award.getRaceOrProjectName()).list();
        if (awardList.size() == 0) {
            return awardMapper.insert(award) == 1;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(Integer id) {
        return awardMapper.deleteById(id) == 1;
    }

    @Override
    public boolean update(Award award) {
        return awardMapper.updateById(award) == 1;
    }

    @Override
    public Award select(Integer id) {
        return awardMapper.selectById(id);
    }

    @Override
    public List<Award> selectAll() {
        return awardMapper.selectList(null);
    }

    @Override
    public List<Award> selectProjectAll() {
        List<Award> awardList =  new LambdaQueryChainWrapper<Award>(awardMapper)
                .eq(Award::getAwardClassify,"项目获奖").list();
        Collections.sort(awardList, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                Award award1=(Award) o1;
                Award award2=(Award) o2;
                if (award1.getYear()>award2.getYear()){
                    return -1 ;
                } else if (award1.getYear().equals(award2.getYear())){
                    if (award1.getMonth()>award2.getMonth()){
                        return -1 ;
                    } else if (award1.getMonth().equals(award2.getMonth())){
                        if (award1.getDay()>award2.getDay()){
                            return -1 ;
                        } else if (award1.getDay().equals(award2.getDay())){
                            return 0;
                        } else {
                            return 1 ;
                        }
                    } else {
                        return 1 ;
                    }
                } else {
                    return 1 ;
                }
            }
        });
        return awardList;
    }

    @Override
    public List<Award> selectCompetitionAll() {
        List<Award> awardList =  new LambdaQueryChainWrapper<Award>(awardMapper)
                .eq(Award::getAwardClassify,"竞赛获奖").list();
        Collections.sort(awardList, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                Award award1=(Award) o1;
                Award award2=(Award) o2;
                if (award1.getYear()>award2.getYear()){
                    return -1 ;
                } else if (award1.getYear().equals(award2.getYear())){
                    if (award1.getMonth()>award2.getMonth()){
                        return -1 ;
                    } else if (award1.getMonth().equals(award2.getMonth())){
                        if (award1.getDay()>award2.getDay()){
                            return -1 ;
                        } else if (award1.getDay().equals(award2.getDay())){
                            return 0;
                        } else {
                            return 1 ;
                        }
                    } else {
                        return 1 ;
                    }
                } else {
                    return 1 ;
                }
            }
        });
        return awardList;
    }

    @Override
    public IPage<Award> selectAwardByPage(Integer pageNumber, Integer pageSize) {
        Page<Award> awardPage = new Page<>(pageNumber,pageSize);
        IPage<Award> awardIPage = awardMapper.selectPage(awardPage,null);
        return awardIPage;
    }

    @Override
    public Integer selectId(Integer year, Integer month, Integer day, String classify, String awardName, String raceOrProjectName) {
        List<Award> awardList = new LambdaQueryChainWrapper<Award>(awardMapper)
                .eq(Award::getYear,year)
                .eq(Award::getMonth,month)
                .eq(Award::getDay,day)
                .eq(Award::getAwardClassify,classify)
                .eq(Award::getAwardName,awardName)
                .eq(Award::getRaceOrProjectName,raceOrProjectName).list();
        return awardList.get(0).getId();
    }
}
