package studio.banner.officialwebsite.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import studio.banner.officialwebsite.entity.Project;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/11 18:12
 * @role: 组内项目Service层接口
 */
public interface IProjectService {
    /**
     * 增加项目信息
     * @param project
     * @return
     */
    boolean insert(Project project);

    /**
     * 删除项目信息
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 更改项目信息
     * @param project
     * @return
     */
    boolean update(Project project);

    /**
     * 根据id查询项目信息
     * @param id
     * @return
     */
    Project select(Integer id);

    /**
     * 查询所有项目信息
     * @return
     */
    List<Project> selectAll();

    /**
     * 根据项目信息查询项目id
     * @return
     * @param project
     */
    Integer selectId(Project project);

    /**
     * 分页查询项目信息
     * @param pageNumber
     * @param pageSize
     * @return
     */
    IPage<Project> selectProjectByPage(Integer pageNumber,Integer pageSize);
}
