package studio.banner.officialwebsite.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.Member;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.entity.SoftwareCopyright;
import studio.banner.officialwebsite.service.IMemberService;
import studio.banner.officialwebsite.service.ISoftwareCopyrightPersonnelService;
import studio.banner.officialwebsite.service.ISoftwareCopyrightService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Re
 * @Date: 2021/5/19 16:09
 */
@RestController
@Api(tags = "软著信息接口", value = "SoftwareCopyrightController")
@Slf4j
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SoftwareCopyrightController {
    @Autowired
    protected ISoftwareCopyrightService iSoftwareCopyrightService;
    @Autowired
    protected ISoftwareCopyrightPersonnelService iSoftwareCopyrightPersonnelService;
    @Autowired
    protected IMemberService iMemberService;

    @PostMapping(value = "/softwareCopyright/insertSoftwareCopyright")
    @ApiOperation(value = "添加软著信息接口", notes = "软著信息不能为空", httpMethod = "POST")
    public RespBean insert(SoftwareCopyright softwareCopyright) {
        if (iSoftwareCopyrightService.insert(softwareCopyright)) {
            Integer id = iSoftwareCopyrightService.selectId(softwareCopyright);
            log.info("软著信息插入成功");
            return RespBean.ok("软著信息插入成功", id);
        } else {
            log.info("软件信息插入失败");
            return RespBean.error("软件信息插入失败");
        }
    }

    @PostMapping("/softwareCopyright/insertMember")
    @ApiOperation(value = "添加软著信息关联成员", httpMethod = "POST")
    public RespBean insertMember(Integer memberId, Integer softwareCopyrightId) {
        if (iSoftwareCopyrightPersonnelService.insert(memberId, softwareCopyrightId)) {
            log.info("增加软著信息关联成员成功");
            return RespBean.ok("增加软著信息关联成员成功");
        } else {
            log.info("增加软著信息关联成员失败");
            return RespBean.error("增加软著信息关联成员失败");
        }
    }

    @DeleteMapping("/softwareCopyright/deleteMember")
    @ApiOperation(value = "删除关联成员", httpMethod = "DELETE")
    public RespBean deleteMember(Integer memberId, Integer softwareCopyrightId) {
        if (iSoftwareCopyrightPersonnelService.delete(memberId, softwareCopyrightId)) {
            log.info("删除关联成员成功");
            return RespBean.ok("删除关联成员成功");
        } else {
            log.info("删除关联成员失败");
            return RespBean.error("删除关联成员失败");
        }
    }

    @DeleteMapping(value = "/softwareCopyright/deleteSoftwareCopyright")
    @ApiOperation(value = "删除软著信息接口", notes = "请输入已存在的软著的id", httpMethod = "DELETE")
    public RespBean delete(Integer id) {
        if (iSoftwareCopyrightService.delete(id)) {
            log.info("软件信息删除成功");
            return RespBean.ok("软件信息删除成功");
        } else {
            log.info("软件信息删除失败");
            return RespBean.error("软件信息删除失败");
        }
    }

    @DeleteMapping(value = "/softwareCopyright/deleteAllMember")
    @ApiOperation(value = "根据软著id删除所有关联成员", httpMethod = "DELETE")
    public RespBean deleteAllMember(Integer id) {
        if (iSoftwareCopyrightPersonnelService.deleteAllBySoftwareCopyrightId(id)) {
            log.info("删除该软著关联的所有成员成功");
            return RespBean.ok("删除该软著关联的所有成员成功");
        } else {
            log.info("删除该软著关联的所有成员失败");
            return RespBean.error("删除该软著关联的所有成员失败");
        }
    }

    @PutMapping(value = "/softwareCopyright/updateSoftwareCopyright")
    @ApiOperation(value = "更改软著信息接口", notes = "id为更改待软著的id", httpMethod = "PUT")
    public RespBean update(SoftwareCopyright softwareCopyright) {
        if (iSoftwareCopyrightService.update(softwareCopyright)) {
            log.info("软著信息更改成功");
            return RespBean.ok("软著信息更改成功");
        } else {
            log.info("软著信息更改失败");
            return RespBean.error("软著信息更改失败");
        }
    }

    @GetMapping(value = "/softwareCopyright/selectSoftwareCopyright")
    @ApiOperation(value = "根据id查询软著信息接口", notes = "id不能为空", httpMethod = "GET")
    public RespBean select(Integer id) {
        SoftwareCopyright softwareCopyright = iSoftwareCopyrightService.select(id);
        if (softwareCopyright != null) {
            log.info("查询软著信息：" + softwareCopyright);
            return RespBean.ok("查询成功", softwareCopyright);
        } else {
            log.info("软组信息查询失败");
            return RespBean.error("查询失败");
        }
    }

    @GetMapping(value = "/softwareCopyright/selectAllSoftwareCopyright")
    @ApiOperation(value = "查询所有软著信息接口", notes = "id不能为空", httpMethod = "GET")
    public RespBean selectAll() {
        List<Map> list = new ArrayList<>();
        List<SoftwareCopyright> softwareCopyrightList = iSoftwareCopyrightService.selectAll();
        for (SoftwareCopyright softwareCopyright : softwareCopyrightList) {
            Map<String, Object> projectMap = new HashMap<>();
            projectMap.put("softwareCopyright", softwareCopyright);
            List<String> memberNameList = new ArrayList<>();
            List<Integer> memberIdList = iSoftwareCopyrightPersonnelService.select(softwareCopyright.getId());
            for (Integer i : memberIdList) {
                Member member = iMemberService.select(i);
                memberNameList.add(member.getMemberName());
                log.info("成员名" + member.getMemberName());
            }
            projectMap.put("memberName", memberNameList);
            list.add(projectMap);
        }
        if (softwareCopyrightList.size() != 0) {
            log.info("查询成功");
            return RespBean.ok("查询成功", list);
        } else {
            log.info("查询失败");
            return RespBean.error("查询失败");
        }
    }

    @GetMapping(value = "/softwareCopyright/selectMember")
    @ApiOperation(value = "根据软著id查询关联成员接口", httpMethod = "GET")
    public RespBean selectMember(Integer softwareCopyrightId) {
        List<Integer> integers = iSoftwareCopyrightPersonnelService.select(softwareCopyrightId);
        if (integers.size() != 0) {
            log.info("查询软著关联成员成功");
            return RespBean.ok("查询软著关联成员成功", integers);
        } else {
            log.info("该软著无关联成员");
            return RespBean.error("该软著无关联成员");
        }
    }

    @GetMapping(value = "/softwareCopyright/selectByPage")
    @ApiOperation(value = "分页查询软著信息接口", notes = "页码数和页码大小不能为空", httpMethod = "GET")
    public RespBean selectByPage(Integer pageNumber, Integer pageSize) {
        IPage<SoftwareCopyright> softwareCopyrightIPage = iSoftwareCopyrightService.selectByPage(pageNumber, pageSize);
        if (softwareCopyrightIPage.getCurrent() == 0) {
            return RespBean.error("该页面无信息");
        }
        return RespBean.ok("分页查询成功", softwareCopyrightIPage);
    }
}
