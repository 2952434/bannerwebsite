package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.Member;
import studio.banner.officialwebsite.entity.Project;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IMemberService;
import studio.banner.officialwebsite.service.IProjectPersonnelService;
import studio.banner.officialwebsite.service.IProjectService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Re
 * @Date: 2021/5/25 17:59
 */
@RestController
@Slf4j
@Api(tags = "项目前台接口", value = "ProjectFrontDeskController")
public class ProjectFrontDeskController {
    @Resource
    protected IProjectService iProjectService;
    @Resource
    protected IProjectPersonnelService iProjectPersonnelService;
    @Resource
    protected IMemberService iMemberService;

    @GetMapping("/projectFrontDesk/selectProjectFrontDesk")
    @ApiOperation(value = "根据id查询项目信息", httpMethod = "GET")
    public RespBean select(Integer id) {
        if (id != null) {
            List<String> memberNameList = new ArrayList<>();
            List<Integer> memberIdList = iProjectPersonnelService.selectById(id);
            for (Integer i : memberIdList) {
                memberNameList.add(iMemberService.select(i).getMemberName());
            }
            Project project = iProjectService.select(id);
            List list = new ArrayList();
            list.add(project);
            list.add(memberNameList);
            if (project != null) {
                log.info("查找成功");
                return RespBean.ok("查找成功", list);
            } else {
                log.info("未查找到该项目");
                return RespBean.error("未查找到该项目");
            }
        } else {
            log.info("id为空无法查找");
            return RespBean.error("id为空无法查找");
        }
    }

    @GetMapping("/projectFrontDesk/selectAll")
    @ApiOperation(value = "查询所有项目信息", httpMethod = "GET")
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
                if (member == null) {
                    continue;
                }
                memberNameList.add(member.getMemberName());
                log.info("成员名" + member.getMemberName());
            }
            projectMap.put("memberName", memberNameList);
            list.add(projectMap);
        }
        if (projects.size() != 0) {
            log.info("查询成功");
            return RespBean.ok("查询成功", list);
        } else {
            log.info("查询失败");
            return RespBean.error("查询失败");
        }
    }
}
