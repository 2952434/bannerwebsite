package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
 * @Date: 2021/5/25 18:08
 */
@RestController
@Slf4j
@Api(tags = "软著信息前台接口", value = "SoftwareCopyrightFrontDeskController")
public class SoftwareCopyrightFrontDeskController {
    @Autowired
    protected ISoftwareCopyrightService iSoftwareCopyrightService;
    @Autowired
    protected IMemberService iMemberService;
    @Autowired
    protected ISoftwareCopyrightPersonnelService iSoftwareCopyrightPersonnelService;

    @GetMapping("/softwareCopyrightFrontDesk/selectSoftwareCopyrightFrontDesk")
    @ApiOperation(value = "根据id查询软著信息", httpMethod = "GET")
    public RespBean select(Integer id) {
        SoftwareCopyright softwareCopyright = iSoftwareCopyrightService.select(id);
        if (softwareCopyright != null) {
            log.info("查询软著信息：" + softwareCopyright);
            return RespBean.ok("查询成功", softwareCopyright);
        } else {
            log.info("软著信息查询失败");
            return RespBean.error("查询失败");
        }
    }

    @GetMapping("/softwareCopyrightFrontDesk/selectAll")
    @ApiOperation(value = "查询所有软著信息", httpMethod = "GET")
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
                if (member == null) {
                    continue;
                }
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
}
