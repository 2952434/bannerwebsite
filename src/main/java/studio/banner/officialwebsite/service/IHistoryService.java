package studio.banner.officialwebsite.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import studio.banner.officialwebsite.entity.History;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/27 19:57
 */
public interface IHistoryService {
    /**
     * 插入工作室发展历程
     * @param history
     * @return
     */
    boolean insert(History history);

    /**
     * 删除工作室发展历程
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 更改工作室发展历程
     * @param history
     * @return
     */
    boolean update(History history);

    /**
     * 查询单个工作室发展历程
     * @param id
     * @return
     */
    History select(Integer id);

    /**
     * 查询所有工作室发展历程
     * @return
     */
    List<History> selectAll();

    /**
     * 分页查询获奖信息
     * @param pageNumber
     * @param pageSize
     * @return
     */
    IPage<History> selectByPage(Integer pageNumber, Integer pageSize);
}
