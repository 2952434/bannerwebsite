package studio.banner.officialwebsite.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import studio.banner.officialwebsite.entity.Journalism;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/8 20:25
 * @role: 新闻信息Service层接口
 */
public interface IJournalismService {
    /**
     * 插入一个新闻对象
     * @param journalism
     * @return
     */
    boolean insert(Journalism journalism);

    /**
     * 根据id删除新闻对象
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 更改新闻信息
     * @param journalism
     * @return
     */
    boolean update(Journalism journalism);

    /**
     * 查看所有新闻
     * @return
     */
    List<Journalism> selectAllJournalism();

    /**
     * 查看新闻
     * @return
     * @param id
     */
    Journalism select(Integer id);

    /**
     * 分页查询新闻信息
     * @param pageNumber
     * @param pageSize
     * @return
     */
    IPage<Journalism> selectJournalismByPage(Integer pageNumber, Integer pageSize);
}
