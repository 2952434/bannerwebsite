package studio.banner.officialwebsite.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.Member;
import studio.banner.officialwebsite.entity.Project;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Re
 * @Date: 2021/5/11 18:28
 * @role 项目控制层
 */
@RestController
@Api(tags = "项目接口", value = "ProjectController")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ProjectController {
    protected static Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Resource
    protected IProjectService iProjectService;
    @Resource
    protected ITencentYunService iTencentYunService;
    @Resource
    protected IProjectPersonnelService iProjectPersonnelService;
    @Resource
    protected IMemberService iMemberService;

    @PostMapping(value = "/project/insertProject")
    @ApiOperation(value = "增加项目信息", httpMethod = "POST")
    public RespBean insert(String projectName, String projectBrief, String path, String projectAddress) {
        Project project = new Project(projectName, projectBrief, path, projectAddress);
        if (iProjectService.insert(project)) {
            Integer id = iProjectService.selectId(project);
            logger.info("项目名称为" + project.getProjectName());
            return RespBean.ok("项目添加成功", id);
        } else {
            logger.info("项目添加失败");
            return RespBean.error("项目添加失败");
        }
    }

    @PostMapping(value = "/project/insertMember")
    @ApiOperation(value = "增加项目关联的成员", httpMethod = "POST")
    public RespBean insertMember(Integer memberId, Integer projectId) {
        if (iProjectPersonnelService.insert(memberId, projectId)) {
            logger.info("增加项目关联的成员成功");
            return RespBean.ok("增加项目关联的成员成功");
        } else {
            logger.info("增加项目关联的成员失败");
            return RespBean.error("增加项目关联的成员失败");
        }
    }

    @DeleteMapping(value = "/project/deleteMember")
    @ApiOperation(value = "根据项目id和成员id删除项目成员")
    public RespBean deleteMember(Integer memberId, Integer projectId) {
        if (iProjectPersonnelService.delete(memberId, projectId)) {
            logger.info("删除项目关联的成员成功");
            return RespBean.ok("删除项目关联的成员成功");
        } else {
            logger.info("删除项目关联的成员失败");
            return RespBean.error("删除项目关联的成员失败");
        }
    }

    @DeleteMapping(value = "/project/deleteProject")
    @ApiOperation(value = "根据项目id删除项目信息")
    public RespBean delete(Integer id) {
        if (id != null) {
            Project project = iProjectService.select(id);
            if (project.getProjectPhotoAddress() != null && !"".equals(project.getProjectPhotoAddress())) {
                iTencentYunService.delete(project.getProjectPhotoAddress().split("/")[4]);
            }
            if (iProjectService.delete(id)) {
                logger.info("项目删除成功");
                return RespBean.ok("项目删除成功");
            } else {
                logger.info("项目删除失败");
                return RespBean.error("项目删除失败");
            }
        } else {
            logger.info("传入的id为空");
            return RespBean.error("传入的id为空");
        }
    }

    @DeleteMapping(value = "/project/deleteAllByProjectId")
    @ApiOperation(value = "根据项目id删除所有关联成员")
    public RespBean deleteAllByProjectId(Integer projectId) {
        if (iProjectPersonnelService.deleteAllByProjectId(projectId)) {
            logger.info("根据项目id删除所有关联成员成功");
            return RespBean.ok("删除成功");
        } else {
            logger.info("根据项目id删除所有关联成员失败");
            return RespBean.error("删除失败");
        }
    }

    @PutMapping(value = "/project/updateProject")
    @ApiOperation(value = "更改项目信息")
    public RespBean update(Integer id, String projectName, String projectBrief, String path, String projectAddress) {
        Project project = new Project(id, projectName, projectBrief, path, projectAddress);
        Project select = iProjectService.select(id);
        if (!"".equals(project.getProjectPhotoAddress())) {
            if (select.getProjectPhotoAddress() != null && !"".equals(select.getProjectPhotoAddress())) {
                logger.info("项目图片删除成功");
                iTencentYunService.delete(iProjectService.select(id).getProjectPhotoAddress().substring(36));
            }
        }
        if (iProjectService.update(project)) {
            logger.info("项目更改为" + project);
            return RespBean.ok("项目更改成功");
        } else {
            logger.info("项目更改失败");
            return RespBean.error("项目更改失败");
        }
    }

    @GetMapping(value = "/project/selectProject")
    @ApiOperation(value = "根据id查询项目信息")
    public RespBean select(Integer id) {
        if (id != null) {
            List<String> memberNameList = new ArrayList<>();
            List<Integer> memberIdList = iProjectPersonnelService.selectById(id);
            for (Integer i : memberIdList) {
                memberNameList.add(iMemberService.select(i).getMemberName());
                logger.info("用户名" + iMemberService.select(i).getMemberName());
            }
            Project project = iProjectService.select(id);
            List list = new ArrayList();
            list.add(project);
            list.add(memberNameList);
            if (project != null) {
                logger.info("查找成功");
                return RespBean.ok("查找成功", list);
            } else {
                logger.info("未查找到该项目");
                return RespBean.error("未查找到该项目");
            }
        } else {
            logger.info("id为空无法查找");
            return RespBean.error("id为空无法查找");
        }
    }

    @GetMapping(value = "/project/selectAllMember")
    @ApiOperation(value = "根据项目id查询项目关联成员信息")
    public RespBean selectAllMember(Integer projectId) {
        List<Integer> ids = iProjectPersonnelService.selectById(projectId);
        if (ids.size() != 0) {
            logger.info("根据项目id查询关联成员成功");
            return RespBean.ok("查询成功", ids);
        } else {
            logger.info("根据项目id查询关联成员失败");
            return RespBean.error("查询失败");
        }
    }

    @GetMapping(value = "/project/selectAllProject")
    @ApiOperation(value = "查询所有项目信息")
    public RespBean selectAll() {
        List<Map> list = new ArrayList<>();
        List<Project> projects = iProjectService.selectAll();
        for (Project project : projects) {
            Map<String, Object> projectMap = new HashMap<>();
            projectMap.put("project", project);
            List<String> memberNameList = new ArrayList<>();
            List<Integer> memberIdList = iProjectPersonnelService.selectById(project.getId());
            for (Integer i : memberIdList) {
                Member member = iMemberService.select(i);
                memberNameList.add(member.getMemberName());
                logger.info("成员名" + member.getMemberName());
            }
            projectMap.put("memberName", memberNameList);
            list.add(projectMap);
        }
        if (projects.size() != 0) {
            logger.info("查询成功");
            return RespBean.ok("查询成功", list);
        } else {
            logger.info("查询失败");
            return RespBean.error("查询失败");
        }
    }

    @GetMapping(value = "/project/selectProjectByPage")
    @ApiOperation(value = "分页查询项目信息")
    public RespBean selectProjectByPage(Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageSize != null) {
            IPage<Project> iPage = iProjectService.selectProjectByPage(pageNumber, pageSize);
            if (iPage == null) {
                logger.info("分页对象为空");
                RespBean.error("分页查询失败");
                return null;
            } else {
                List<Project> projectList = iPage.getRecords();
                if (projectList.size() == 0) {
                    logger.info("查询无数据");
                    return RespBean.error("查询无数据");
                } else {
                    logger.info("查询成功");
                    return RespBean.ok("查询成功", projectList);
                }
            }
        } else {
            logger.info("传入的数据为空");
            return RespBean.error("传入的页码数和页码尺寸为空");
        }
    }
}
