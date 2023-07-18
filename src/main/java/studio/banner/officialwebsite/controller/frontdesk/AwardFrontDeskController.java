package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.Award;
import studio.banner.officialwebsite.entity.Member;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IAwardPersonnelService;
import studio.banner.officialwebsite.service.IAwardService;
import studio.banner.officialwebsite.service.IMemberService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Re
 * @Date: 2021/5/24 21:12
 */
@RestController
@Api(tags = "获奖信息前台接口", value = "AwardFrontDeskController")
@ResponseBody
@Slf4j
public class AwardFrontDeskController {
    @Autowired
    protected IAwardService iAwardService;
    @Autowired
    protected IAwardPersonnelService iAwardPersonnelService;
    @Autowired
    protected IMemberService iMemberService;

    @GetMapping("/awardFrontDeskController/select")
    @ApiOperation(value = "根据id查询获奖信息", httpMethod = "GET")
    public RespBean select(Integer id) {
        Award award = iAwardService.select(id);
        if (award != null) {
            log.info("前台查询获奖信息成功");
            return RespBean.ok("前台查询获奖信息成功", award);
        } else {
            log.info("前台查询获奖信息失败");
            return RespBean.error("前台查询获奖信息失败");
        }
    }

    @GetMapping("/awardFrontDeskController/selectAll")
    @ApiOperation(value = "查询所有获奖信息", httpMethod = "GET")
    public RespBean selectAll() {
        List<Map> list = new ArrayList<>();
        List<Award> awardList = iAwardService.selectAll();
        for (Award award : awardList) {
            Map<String, Object> awardMap = new HashMap<>();
            awardMap.put("award", award);
            List<String> memberNameList = new ArrayList<>();
            List<Integer> memberIdList = iAwardPersonnelService.select(award.getId());
            for (Integer i : memberIdList) {
                Member member = iMemberService.select(i);
                memberNameList.add(member.getMemberName());
                log.info("成员名" + member.getMemberName());
            }
            awardMap.put("memberName", memberNameList);
            list.add(awardMap);
        }
        if (awardList.size() != 0) {
            log.info("查询成功");
            return RespBean.ok("查询成功", list);
        } else {
            log.info("查询失败");
            return RespBean.error("查询失败");
        }
    }

    @GetMapping(value = "/awardFrontDeskController/selectProjectAll")
    @ApiOperation(value = "查询所有项目获奖信息")
    public RespBean selectProjectAll() {
        List<Map> list = new ArrayList<>();
        List<Award> awardList = iAwardService.selectProjectAll();
        for (Award award : awardList) {
            Map<String, Object> awardMap = new HashMap<>();
            awardMap.put("award", award);
            List<String> memberNameList = new ArrayList<>();
            List<Integer> memberIdList = iAwardPersonnelService.select(award.getId());
            for (Integer i : memberIdList) {
                Member member = iMemberService.select(i);
                if (member == null) {
                    continue;
                }
                memberNameList.add(member.getMemberName());
                log.info("成员名" + member.getMemberName());
            }
            awardMap.put("memberName", memberNameList);
            list.add(awardMap);
        }
        if (awardList.size() != 0) {
            log.info("查询成功");
            return RespBean.ok("查询成功", list);
        } else {
            log.info("查询失败");
            return RespBean.error("查询失败");
        }
    }

    @GetMapping(value = "/awardFrontDeskController/selectCompetitionAll")
    @ApiOperation(value = "查询所有竞赛获奖信息")
    public RespBean selectCompetitionAll() {
        List<Map> list = new ArrayList<>();
        List<Award> awardList = iAwardService.selectCompetitionAll();
        for (Award award : awardList) {
            Map<String, Object> awardMap = new HashMap<>();
            awardMap.put("award", award);
            List<String> memberNameList = new ArrayList<>();
            List<Integer> memberIdList = iAwardPersonnelService.select(award.getId());
            for (Integer i : memberIdList) {
                Member member = iMemberService.select(i);
                memberNameList.add(member.getMemberName());
                log.info("成员名" + member.getMemberName());
            }
            awardMap.put("memberName", memberNameList);
            list.add(awardMap);
        }
        if (awardList.size() != 0) {
            log.info("查询成功");
            return RespBean.ok("查询成功", list);
        } else {
            log.info("查询失败");
            return RespBean.error("查询失败");
        }
    }
}
