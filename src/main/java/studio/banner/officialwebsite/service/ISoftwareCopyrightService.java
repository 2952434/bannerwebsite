package studio.banner.officialwebsite.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import studio.banner.officialwebsite.entity.SoftwareCopyright;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/19 15:43
 */
public interface ISoftwareCopyrightService {
    /**
     * 增加软著信息
     * @param softwareCopyright
     * @return
     */
    boolean insert(SoftwareCopyright softwareCopyright);

    /**
     * 根据 id 删除软著信息
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 更改软著信息
     * @param softwareCopyright
     * @return
     */
    boolean update(SoftwareCopyright softwareCopyright);

    /**
     * 根据id查询软组信息
     * @param id
     * @return
     */
    SoftwareCopyright select(Integer id);

    /**
     * 查询所有的软著信息
     * @return
     */
    List<SoftwareCopyright> selectAll();

    /**
     * 根据软著信息查询软著id
     * @param softwareCopyright
     * @return
     */
    Integer selectId(SoftwareCopyright softwareCopyright);

    /**
     * 对软著信息进行分页查询
     * @param pageNumber
     * @param pageSize
     * @return
     */
    IPage<SoftwareCopyright> selectByPage(Integer pageNumber,Integer pageSize);
}
