package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.Member;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IMemberService;

import java.util.*;

/**
 * @Author: Re
 * @Date: 2021/5/26 0:47
 */
@RestController
@Slf4j
@Api(tags = "成员前台接口", value = "MemberFrontDeskController")
public class MemberFrontDeskController {
    @Autowired
    protected IMemberService iMemberService;

    @GetMapping("/memberFrontDesk/selectMemberFrontDesk")
    @ApiOperation(value = "根据id查询成员", httpMethod = "GET")
    public RespBean select(Integer id) {
        Member member = iMemberService.select(id);
        return RespBean.ok("根据id查询用户成功", member);
    }

    @GetMapping("/memberFrontDesk/selectAll")
    @ApiOperation(value = "查询所有成员", httpMethod = "GET")
    public RespBean selectAll() {
        Map<String, Object> map = new HashMap<>();
        List<Member> memberList = iMemberService.selectAll();
        List<Integer> grades = new ArrayList<>();
        List<String> directions = new ArrayList<>();
        List<String> responsibilities = new ArrayList<>();
        for (Member member : memberList) {
            boolean gradeFlag = true;
            for (Integer i : grades) {
                if (member.getMemberGrade().equals(i)) {
                    gradeFlag = false;
                    break;
                }
            }
            if (gradeFlag) {
                grades.add(member.getMemberGrade());
            }
            boolean directionsFlag = true;
            for (String s : directions) {
                if (member.getMemberDirection().equals(s)) {
                    directionsFlag = false;
                }
            }
            if (directionsFlag) {
                directions.add(member.getMemberDirection());
            }
            boolean responsibilityFlag = true;
            for (String s : responsibilities) {
                if (member.getMemberResponsibility().equals(s)) {
                    responsibilityFlag = false;
                }
            }
            if (responsibilityFlag) {
                responsibilities.add(member.getMemberResponsibility());
            }
        }
        Collections.sort(grades);
        Collections.reverse(grades);
        map.put("grades", grades);
        map.put("directions", directions);
        map.put("responsibilities", responsibilities);
        map.put("bannerMembers", memberList);
        log.info("前台查询所有成员");
        return RespBean.ok("查询所有成员成功", map);
    }

    @GetMapping("/memberFrontDesk/selectResponsibility")
    @ApiOperation(value = "根据职务查询成员", httpMethod = "GET")
    public RespBean selectResponsibility(String responsibility) {
        List<Member> memberList = iMemberService.selectResponsibility(responsibility);
        return RespBean.ok("根据职务查询成员成功", memberList);
    }

    @GetMapping("/memberFrontDesk/selectGrade")
    @ApiOperation(value = "根据年级查询成员", httpMethod = "GET")
    public RespBean selectGrade(Integer grade) {
        Map<String, Object> map = new HashMap<>();
        List<Member> memberList = iMemberService.selectGrade(grade);
        List<Integer> grades = new ArrayList<>();
        List<String> directions = new ArrayList<>();
        List<String> responsibilities = new ArrayList<>();
        for (Member member : memberList) {
            boolean gradeFlag = true;
            for (Integer i : grades) {
                if (member.getMemberGrade().equals(i)) {
                    gradeFlag = false;
                    break;
                }
            }
            if (gradeFlag) {
                grades.add(member.getMemberGrade());
            }
            boolean directionsFlag = true;
            for (String s : directions) {
                if (member.getMemberDirection().equals(s)) {
                    directionsFlag = false;
                }
            }
            if (directionsFlag) {
                directions.add(member.getMemberDirection());
            }
            boolean responsibilityFlag = true;
            for (String s : responsibilities) {
                if (member.getMemberResponsibility().equals(s)) {
                    responsibilityFlag = false;
                }
            }
            if (responsibilityFlag) {
                responsibilities.add(member.getMemberResponsibility());
            }
        }
        Collections.sort(grades);
        Collections.reverse(grades);
        map.put("grades", grades);
        map.put("directions", directions);
        map.put("responsibilities", responsibilities);
        map.put("bannerMembers", memberList);
        log.info("前台查询所有成员");
        return RespBean.ok("查询所有成员成功", map);
    }

    @GetMapping("/memberFrontDesk/selectDirection")
    @ApiOperation(value = "根据方向查询成员", httpMethod = "GET")
    public RespBean selectDirection(String direction) {
        List<Member> members = iMemberService.selectDirection(direction);
        return RespBean.ok("根据方向查询用户", members);
    }

    @GetMapping("/memberFrontDesk/selectDirectionAddGrade")
    @ApiOperation(value = "根据年级和方向查询成员", httpMethod = "GET")
    public RespBean selectDirectionAddGrade(String direction, Integer grade) {
        List<Member> memberList = iMemberService.selectDirectionAddGrade(direction, grade);
        return RespBean.ok("根据方向和年级查询用户", memberList);
    }

    @GetMapping("/memberFrontDesk/selectResponsibilityAddGrade")
    @ApiOperation(value = "根据根据年级和职责查询成员", httpMethod = "GET")
    public RespBean selectResponsibilityAddGrade(String responsibility, Integer grade) {
        List<Member> memberList = iMemberService.selectResponsibilityAddGrade(responsibility, grade);
        return RespBean.ok("根据职责和年级查询用户", memberList);
    }

    @GetMapping("/memberFrontDesk/selectAllGrade")
    @ApiOperation(value = "查询含有成员的所有年级", httpMethod = "GET")
    public RespBean selectAllGrade() {
        List<Member> memberList = iMemberService.selectAll();
        List<Integer> grades = new ArrayList<>();
        for (Member member : memberList) {
            boolean flag = true;
            for (Integer i : grades) {
                if (member.getMemberGrade().equals(i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                grades.add(member.getMemberGrade());
            }
        }
        Collections.sort(grades);
        Collections.reverse(grades);
        return RespBean.ok("查询年级成功", grades);
    }
}
