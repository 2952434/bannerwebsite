package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.Project;
import studio.banner.officialwebsite.mapper.ProjectMapper;
import studio.banner.officialwebsite.service.IProjectService;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/11 18:18
 * @role: 组内项目Service层实现
 */
@Service
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    protected ProjectMapper projectMapper;
    @Override
    public boolean insert(Project project) {
        List<Project> projects = new LambdaQueryChainWrapper<Project>(projectMapper)
                .eq(Project::getProjectName,project.getProjectName()).list();
        if (projects.size() == 0) {
            return projectMapper.insert(project) != 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return projectMapper.deleteById(id) != 0;
    }

    @Override
    public boolean update(Project project) {
        return projectMapper.updateById(project) != 0;
    }

    @Override
    public Project select(Integer id) {
        return projectMapper.selectById(id);
    }

    @Override
    public List<Project> selectAll() {
        List<Project> projects = new QueryChainWrapper<Project>(projectMapper)
                .orderByDesc("id").list();
        return projects;
    }

    @Override
    public Integer selectId(Project project) {
        List<Project> projects = new LambdaQueryChainWrapper<Project>(projectMapper)
                .eq(Project::getProjectBrief,project.getProjectBrief())
                .eq(Project::getProjectName,project.getProjectName()).list();
        if (projects.size() != 0) {
            return projects.get(0).getId();
        }
        return null;
    }

    @Override
    public IPage<Project> selectProjectByPage(Integer pageNumber, Integer pageSize) {
        Page<Project> page = new Page<>(pageNumber,pageSize);
        IPage<Project> projectIPage = projectMapper.selectPage(page,null);
        return projectIPage;
    }
}
