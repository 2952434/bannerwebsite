package studio.banner.officialwebsite.controller.background;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.Member;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/7 13:20
 */
@RestController
@Api(tags = "成员接口", value = "MemberController")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MemberController {
    private static Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    protected IMemberService iMemberService;
    @Autowired
    protected IAwardPersonnelService iAwardPersonnelService;
    @Autowired
    protected IProjectPersonnelService iProjectPersonnelService;
    @Autowired
    ISoftwareCopyrightPersonnelService iSoftwareCopyrightPersonnelService;
    @Autowired
    protected ITencentYunService iTencentYunService;

    @ApiOperation(value = "增加用户", notes = "用户对象不能为空", httpMethod = "POST")
    @PostMapping(value = "/member/insertMember")
    public RespBean insert(String memberName, String memberBlog, String memberDirection, String memberResponsibility, Integer memberGrade, String memberIntroductory, String path) {
        Member member = new Member(memberName, memberBlog, memberDirection, memberResponsibility, memberGrade, path, memberIntroductory);
        if (iMemberService.insertMember(member)) {
            logger.info("成员" + member.getMemberName() + "添加成功");
            return RespBean.ok("用户添加成功", member);
        } else {
            logger.info("成员" + member.getMemberName() + "添加失败");
            return RespBean.error("用户添加失败");
        }
    }

    @DeleteMapping(value = "/member/deleteMember")
    @ApiOperation(value = "删除用户", httpMethod = "DELETE")
    public RespBean delete(Integer id) {
        Member member = iMemberService.select(id);
        if (member.getMemberPhoto() != null && !"".equals(member.getMemberPhoto())) {
            if (iTencentYunService.delete(member.getMemberPhoto().split("/")[4])) {
                logger.info("删除成员头像成功");
            } else {
                logger.info("删除成员头像失败");
            }
        }
        if (iMemberService.delete(id)) {
            if (iAwardPersonnelService.deleteAllByMemberId(id)) {
                logger.info("删除获奖信息关联成员");
            }
            if (iProjectPersonnelService.deleteAllByMemberId(id)) {
                logger.info("删除项目信息关联成员");
            }
            if (iSoftwareCopyrightPersonnelService.deleteAllByMemberId(id)) {
                logger.info("删除软著信息关联成员");
            }
            logger.info("删除成员成功");
            return RespBean.ok("删除成员成功");
        } else {
            logger.info("删除成员失败");
            return RespBean.error("删除成员失败");
        }
    }

    @PutMapping(value = "/member/updateMember")
    @ApiOperation(value = "更改成员信息", httpMethod = "PUT")
    public RespBean update(Integer id, String memberName, String memberBlog, String memberDirection, String memberResponsibility, Integer memberGrade, String memberIntroductory, String path) {
        Member member = iMemberService.select(id);
        if (member == null) {
            logger.info("成员不存在，更改失败");
            return RespBean.error("成员不存在，更改失败");
        } else {
            if (member.getMemberPhoto() != null && "".equals(member.getMemberPhoto())) {
                iTencentYunService.delete(member.getMemberPhoto().split("")[4]);
            }
            if (iMemberService.update(new Member(id, memberName, memberBlog, memberDirection, memberResponsibility, memberGrade, path, memberIntroductory))) {
                logger.info("成员信息更改成功");
                return RespBean.ok("成员信息更改成功");
            }
            logger.info("成员信息更改失败");
            return RespBean.error("成员信息更改失败");
        }
    }
//    @PutMapping(value = "/member/updatePhoto")
//    @ApiOperation(value = "更改成员头像",httpMethod = "PUT")
//    public RespBean updatePhoto(Integer id,@RequestPart MultipartFile file) {
//        Member member = iMemberService.select(id);
//        if (member == null) {
//            logger.info("成员不存在，更改失败");
//            return RespBean.error("成员不存在，更改失败");
//        } else {
//            String fileName = file.getOriginalFilename();
//            String imgName = UUID.randomUUID() + "-" + fileName;
//            String path = null;
//            if (!file.isEmpty()) {
//                FileInputStream inputStream = null;
//                try {
//                    inputStream = (FileInputStream) file.getInputStream();
//                    qiNiuYunService.deletePhoto(member.getMemberPhoto().substring(36));
//                    path = qiNiuYunService.uploadPhoto(imgName, inputStream);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (iMemberService.update(new Member(id,path))) {
//                logger.info("更改成员头像成功");
//                return RespBean.ok("更改成员头像成功");
//            } else {
//                logger.info("更改成员头像失败");
//                return RespBean.error("更改成员头像失败");
//            }
//        }
//    }

    @GetMapping("/member/selectMemberFrontDesk")
    @ApiOperation(value = "查询单个用户", httpMethod = "GET")
    public RespBean select(Integer id) {
        Member member = iMemberService.select(id);
        return RespBean.ok("根据id查询用户成功", member);
    }

    @GetMapping("/member/selectAll")
    @ApiOperation(value = "查询所有用户", httpMethod = "GET")
    public RespBean selectAll() {
        List<Member> memberList = iMemberService.selectAll();
        if (memberList.size() == 0) {
            logger.info("查询所有成员失败");
            return RespBean.error("成员信息为空");
        } else {
            logger.info("查询所有成员信息成功");
            return RespBean.ok("查询所有成员信息成功", memberList);
        }
    }

    @GetMapping("/member/selectGrade")
    @ApiOperation(value = "查询成员年级区间", httpMethod = "GET")
    public RespBean selectGrade() {
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
