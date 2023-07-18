package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.History;
import studio.banner.officialwebsite.mapper.HistoryMapper;
import studio.banner.officialwebsite.service.IHistoryService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/27 20:05
 */
@Service
public class HistoryServiceImpl implements IHistoryService {
    @Autowired
    private HistoryMapper historyMapper;
    @Override
    public boolean insert(History history) {
        List<History> histories = new LambdaQueryChainWrapper<History>(historyMapper)
                .eq(History::getTitle,history.getTitle())
                .eq(History::getYear,history.getYear())
                .eq(History::getMonth,history.getMonth())
                .eq(History::getDay,history.getDay()).list();
        if (histories.size() == 0) {
            return historyMapper.insert(history) != 0;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return historyMapper.deleteById(id) != 0;
    }

    @Override
    public boolean update(History history) {
        return historyMapper.updateById(history) != 0;
    }

    @Override
    public History select(Integer id) {
        return historyMapper.selectById(id);
    }

    @Override
    public List<History> selectAll() {
        List<History> historyList = historyMapper.selectList(null);
        Collections.sort(historyList, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                History history1=(History) o1;
                History history2=(History) o2;
                if (history1.getYear()>history2.getYear()){
                    return -1 ;
                } else if (history1.getYear().equals(history2.getYear())){
                    if (history1.getMonth()>history2.getMonth()){
                        return -1 ;
                    } else if (history1.getMonth().equals(history2.getMonth())){
                        if (history1.getDay()>history2.getDay()){
                            return -1 ;
                        } else if (history1.getDay().equals(history2.getDay())){
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
        return historyList;
    }

    @Override
    public IPage<History> selectByPage(Integer pageNumber, Integer pageSize) {
        Page<History> historyPage = new Page<>(pageNumber,pageSize);
        IPage<History> historyIPage = historyMapper.selectPage(historyPage,null);
        return historyIPage;
    }
}
