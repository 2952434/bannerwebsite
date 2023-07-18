package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.SoftwareCopyright;
import studio.banner.officialwebsite.mapper.SoftwareCopyrightMapper;
import studio.banner.officialwebsite.service.ISoftwareCopyrightService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/19 15:51
 */
@Service
public class SoftwareCopyrightServiceImpl implements ISoftwareCopyrightService {
    @Autowired
    protected SoftwareCopyrightMapper softwareCopyrightMapper;
    @Override
    public boolean insert(SoftwareCopyright softwareCopyright) {
        List<SoftwareCopyright> list = new LambdaQueryChainWrapper<SoftwareCopyright>(softwareCopyrightMapper)
                .eq(SoftwareCopyright::getSoftwareCopyrightName,softwareCopyright.getSoftwareCopyrightName())
                .eq(SoftwareCopyright::getYear,softwareCopyright.getYear())
                .eq(SoftwareCopyright::getMonth,softwareCopyright.getMonth())
                .eq(SoftwareCopyright::getDay,softwareCopyright.getDay()).list();
        if (list.size() == 0) {
            return softwareCopyrightMapper.insert(softwareCopyright) != 0;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return softwareCopyrightMapper.deleteById(id) == 1;
    }

    @Override
    public boolean update(SoftwareCopyright softwareCopyright) {
        return softwareCopyrightMapper.updateById(softwareCopyright) != 0;
    }

    @Override
    public SoftwareCopyright select(Integer id) {
        return softwareCopyrightMapper.selectById(id);
    }

    @Override
    public List<SoftwareCopyright> selectAll() {
        List<SoftwareCopyright> softwareCopyrightList = softwareCopyrightMapper.selectList(null);
        Collections.sort(softwareCopyrightList, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                SoftwareCopyright softwareCopyright1=(SoftwareCopyright) o1;
                SoftwareCopyright softwareCopyright2=(SoftwareCopyright) o2;
                if (softwareCopyright1.getYear()>softwareCopyright2.getYear()){
                    return -1 ;
                } else if (softwareCopyright1.getYear().equals(softwareCopyright2.getYear())){
                    if (softwareCopyright1.getMonth()>softwareCopyright2.getMonth()){
                        return -1 ;
                    } else if (softwareCopyright1.getMonth().equals(softwareCopyright2.getMonth())){
                        if (softwareCopyright1.getDay()>softwareCopyright2.getDay()){
                            return -1 ;
                        } else if (softwareCopyright1.getDay().equals(softwareCopyright2.getDay())){
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
        return softwareCopyrightList;
    }

    @Override
    public Integer selectId(SoftwareCopyright softwareCopyright) {
        List<SoftwareCopyright> list = new LambdaQueryChainWrapper<SoftwareCopyright>(softwareCopyrightMapper)
                .eq(SoftwareCopyright::getSoftwareCopyrightName,softwareCopyright.getSoftwareCopyrightName())
                .eq(SoftwareCopyright::getYear,softwareCopyright.getYear())
                .eq(SoftwareCopyright::getMonth,softwareCopyright.getMonth())
                .eq(SoftwareCopyright::getDay,softwareCopyright.getDay()).list();
        return list.get(0).getId();
    }

    @Override
    public IPage<SoftwareCopyright> selectByPage(Integer pageNumber, Integer pageSize) {
        Page<SoftwareCopyright> page = new Page<>(pageNumber,pageSize);
        IPage<SoftwareCopyright> softwareCopyrightIPage = softwareCopyrightMapper.selectPage(page, null);
        return softwareCopyrightIPage;
    }
}
