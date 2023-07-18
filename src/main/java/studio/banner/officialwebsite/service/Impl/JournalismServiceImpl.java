package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.Journalism;
import studio.banner.officialwebsite.mapper.JournalismMapper;
import studio.banner.officialwebsite.service.IJournalismService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/8 20:27
 * @role: 新闻信息Service层实现
 */
@Service
public class JournalismServiceImpl implements IJournalismService {
    @Autowired
    protected JournalismMapper journalismMapper;
    @Override
    public boolean insert(Journalism journalism) {
        List<Journalism> journalismTitleList = new LambdaQueryChainWrapper<Journalism>(journalismMapper)
                .eq(Journalism::getJournalismTitle,journalism.getJournalismTitle()).list();
        if (journalismTitleList.size() == 0){
            return journalismMapper.insert(journalism) != 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return journalismMapper.deleteById(id) != 0;
    }

    @Override
    public boolean update(Journalism journalism) {
        return journalismMapper.updateById(journalism) != 0;
    }

    @Override
    public List<Journalism> selectAllJournalism() {
        List<Journalism> journalismList = journalismMapper.selectList(null);
        Collections.sort(journalismList, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                Journalism journalism1 = (Journalism) o1;
                Journalism journalism2 = (Journalism) o2;
                if (journalism1.getJournalismTimeYear()>journalism2.getJournalismTimeYear()){
                    return -1 ;
                } else if (journalism1.getJournalismTimeYear().equals(journalism2.getJournalismTimeYear())){
                    if (journalism1.getJournalismTimeMonth()>journalism2.getJournalismTimeMonth()){
                        return -1 ;
                    } else if (journalism1.getJournalismTimeMonth().equals(journalism2.getJournalismTimeMonth())){
                        if (journalism1.getJournalismTimeDay()>journalism2.getJournalismTimeDay()){
                            return -1 ;
                        } else if (journalism1.getJournalismTimeDay().equals(journalism2.getJournalismTimeDay())){
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
        return journalismList;
    }

    @Override
    public Journalism select(Integer id) {
        return journalismMapper.selectById(id);
    }

    @Override
    public IPage<Journalism> selectJournalismByPage(Integer pageNumber, Integer pageSize) {
        // 第一个参数：当前页    第二个参数：页面大小
        Page<Journalism> page = new Page<>(pageNumber,pageSize);
        QueryWrapper<Journalism> queryWrapper = new QueryWrapper<>();
        // 根据id方式排序
        queryWrapper.orderByAsc("id");
        IPage<Journalism> journalismIPage = journalismMapper.selectPage(page,queryWrapper);
        return journalismIPage;
    }
}
