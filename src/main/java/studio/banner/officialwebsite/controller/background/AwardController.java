package studio.banner.officialwebsite.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.Award;
import studio.banner.officialwebsite.entity.Member;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IAwardPersonnelService;
import studio.banner.officialwebsite.service.IAwardService;
import studio.banner.officialwebsite.service.IMemberService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Re
 * @Date: 2021/5/10 22:06
 * @role: 获奖信息控制层
 */
@RestController
@Api(tags = "获奖信息接口", value = "AwardController")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AwardController {
    protected static Logger logger = LoggerFactory.getLogger(AwardController.class);
    @Autowired
    protected IAwardService iAwardService;
    @Autowired
    protected IAwardPersonnelService iAwardPersonnelService;
    @Autowired
    protected IMemberService iMemberService;

    @PostMapping(value = "/award/insertAward")
    @ApiOperation(value = "增加获奖信息", notes = "获奖信息不能为空")
    public RespBean insert(@Valid Award award) {
        if (iAwardService.insert(award)) {
            logger.info("插入获奖信息成功");
            Integer id = iAwardService.selectId(award.getYear(), award.getMonth(), award.getDay(), award.getAwardClassify(), award.getAwardName(), award.getRaceOrProjectName());
            return RespBean.ok("插入获奖信息成功", id);
        } else {
            logger.info("插入获奖信息失败");
            return RespBean.error("插入获奖信息失败");
        }
    }

    @DeleteMapping(value = "/award/delete")
    @ApiOperation(value = "删除获奖信息", httpMethod = "DELETE")
    public RespBean delete(Integer id) {
        if (iAwardService.delete(id)) {
            logger.info("删除获奖信息成功");
            if (iAwardPersonnelService.deleteAll(id)) {
                logger.info("删除获奖信息关联成员成功");
            } else {
                logger.info("删除获奖信息关联成员失败");
            }
            return RespBean.ok("删除获奖信息成功");
        } else {
            logger.info("删除获奖信息失败");
            return RespBean.error("删除获奖信息失败");
        }
    }

    @PostMapping("/award/insertMember")
    @ApiOperation(value = "增加获奖信息关联成员", httpMethod = "POST")
    public RespBean insertMember(Integer memberId, Integer awardId) {
        if (iAwardPersonnelService.insert(memberId, awardId)) {
            logger.info("增加关联成员成功");
            return RespBean.ok("增加关联成员成功");
        } else {
            logger.info("增加关联成员失败");
            return RespBean.error("增加关联成员失败");
        }
    }

    @DeleteMapping("/award/deleteMember")
    @ApiOperation(value = "删除获奖信息关联成员", httpMethod = "DELETE")
    public RespBean deleteMember(Integer memberId, Integer awardId) {
        if (iAwardPersonnelService.delete(memberId, awardId)) {
            logger.info("删除关联成员成功");
            return RespBean.ok("删除关联成员成功");
        } else {
            logger.info("删除关联成员失败");
            return RespBean.error("删除关联成员失败");
        }
    }

    @DeleteMapping("/award/deleteAllMember")
    @ApiOperation(value = "根据获奖信息id删除关联的所有成员")
    public RespBean deleteAllMember(Integer awardId) {
        if (iAwardPersonnelService.select(awardId).size() != 0) {
            if (iAwardPersonnelService.deleteAll(awardId)) {
                logger.info("根据获奖信息id删除关联的所有成员成功");
                return RespBean.ok("根据获奖信息id删除关联的所有成员成功");
            } else {
                logger.info("根据获奖信息id删除关联的所有成员失败");
                return RespBean.error("根据获奖信息id删除关联的所有成员失败");
            }
        }
        return RespBean.ok("现无关联成员");
    }

    @PutMapping(value = "/award/updateAward")
    @ApiOperation(value = "更新获奖信息", notes = "获奖信息不能为空")
    public RespBean update(@Valid Award award) {
        if (iAwardService.update(award)) {
            logger.info("更改获奖信息成功");
            return RespBean.ok("更改获奖信息成功");
        } else {
            logger.info("更改获奖信息失败");
            return RespBean.error("更改获奖信息失败");
        }
    }

    @GetMapping(value = "/award/selectAward")
    @ApiOperation(value = "根据id查询获奖信息", notes = "id不能为空")
    public RespBean select(Integer id) {
        Award award = iAwardService.select(id);
        if (award != null) {
            logger.info("获奖信息查询成功");
            return RespBean.ok("获奖信息查询成功", award);
        } else {
            logger.info("无获奖信息");
            return RespBean.error("无获奖信息");
        }
    }

    @GetMapping(value = "/award/selectAllAward")
    @ApiOperation(value = "查询所有获奖信息")
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
                logger.info("成员名" + member.getMemberName());
            }
            awardMap.put("memberName", memberNameList);
            list.add(awardMap);
        }
        if (awardList.size() != 0) {
            logger.info("查询成功");
            return RespBean.ok("查询成功", list);
        } else {
            logger.info("暂无获奖信息");
            return RespBean.ok("暂无获奖信息");
        }
    }

    @GetMapping(value = "/award/selectProjectAll")
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
                memberNameList.add(member.getMemberName());
                logger.info("成员名" + member.getMemberName());
            }
            awardMap.put("memberName", memberNameList);
            list.add(awardMap);
        }
        if (awardList.size() != 0) {
            logger.info("查询成功");
            return RespBean.ok("查询成功", list);
        } else {
            logger.info("暂无获奖信息");
            return RespBean.error("暂无获奖信息");
        }
    }

    @GetMapping(value = "/award/selectCompetitionAll")
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
                logger.info("成员名" + member.getMemberName());
            }
            awardMap.put("memberName", memberNameList);
            list.add(awardMap);
        }
        if (awardList.size() != 0) {
            logger.info("查询成功");
            return RespBean.ok("查询成功", list);
        } else {
            logger.info("查询失败");
            return RespBean.error("查询失败");
        }
    }

    @GetMapping("/award/selectAllMemberId")
    @ApiOperation(value = "根据获奖信息id查询关联所有成员id")
    public RespBean selectAllMemberId(Integer awardId) {
        List<Integer> memberIdList = iAwardPersonnelService.select(awardId);
        return RespBean.ok("根据获奖信息id查询关联所有成员id成功", memberIdList);
    }

    @GetMapping(value = "/award/selectAwardByPage")
    @ApiOperation(value = "分页查询获奖信息", notes = "当前页数和页面尺寸不能为空")
    public RespBean selectAwardByPage(Integer pageNumber, Integer pageSize) {
        IPage<Award> iPage = iAwardService.selectAwardByPage(pageNumber, pageSize);
        List<Award> awardList = iPage.getRecords();
        ;
        if (pageNumber != null && pageSize != null) {
            if (awardList.size() != 0) {
                logger.info("查询成功");
                return RespBean.ok("查询成功", awardList);
            } else {
                logger.info("用户信息为零");
                return RespBean.error("用户信息为零");
            }
        } else {
            logger.info("传入的页码数或页的尺寸为空");
            return RespBean.error("传入的页码数或页的尺寸为空");
        }
    }

    public RespBean selectMemberId(Integer awardId) {
        List<Integer> memberIdList = iAwardPersonnelService.select(awardId);
        return RespBean.ok("查询获奖信息关联成员id成功", memberIdList);
    }
}
