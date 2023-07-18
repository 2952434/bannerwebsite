package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.ProjectPersonnel;
import studio.banner.officialwebsite.mapper.ProjectPersonnelMapper;
import studio.banner.officialwebsite.service.IProjectPersonnelService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/26 17:03
 */
@Service
public class ProjectPersonnelServiceImpl implements IProjectPersonnelService {
    @Autowired
    protected ProjectPersonnelMapper projectPersonnelMapper;
    @Override
    public boolean insert(Integer memberId, Integer projectId) {
        List<ProjectPersonnel> personnelList = new LambdaQueryChainWrapper<ProjectPersonnel>(projectPersonnelMapper)
                .eq(ProjectPersonnel::getProjectId,projectId)
                .eq(ProjectPersonnel::getMemberId,memberId).list();
        if (personnelList.size() == 0) {
            ProjectPersonnel projectPersonnel = new ProjectPersonnel(memberId,projectId);
            return projectPersonnelMapper.insert(projectPersonnel) != 0;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(Integer memberId, Integer projectId) {
        return projectPersonnelMapper.delete(new LambdaQueryChainWrapper<>(projectPersonnelMapper).eq(ProjectPersonnel::getMemberId,memberId).eq(ProjectPersonnel::getProjectId,projectId)) !=  0;
    }

    @Override
    public boolean deleteAllByMemberId(Integer memberId) {
        LambdaQueryWrapper<ProjectPersonnel> queryWrapper = Wrappers.lambdaQuery();
        return projectPersonnelMapper.delete(queryWrapper.eq(ProjectPersonnel::getMemberId,memberId)) >= 0;
    }

    @Override
    public boolean deleteAllByProjectId(Integer projectId) {
        LambdaQueryWrapper<ProjectPersonnel> queryWrapper = Wrappers.lambdaQuery();
        return projectPersonnelMapper.delete(queryWrapper.eq(ProjectPersonnel::getProjectId,projectId)) >= 0;
    }

    @Override
    public List<Integer> selectById(Integer projectId) {
        List<Integer> list = new ArrayList<>();
        List<ProjectPersonnel> projectPersonnelList = new LambdaQueryChainWrapper<ProjectPersonnel>(projectPersonnelMapper)
                .eq(ProjectPersonnel::getProjectId,projectId).list();
        for (ProjectPersonnel projectPersonnel :projectPersonnelList) {
            list.add(projectPersonnel.getMemberId());
        }
        return list;
    }
}
