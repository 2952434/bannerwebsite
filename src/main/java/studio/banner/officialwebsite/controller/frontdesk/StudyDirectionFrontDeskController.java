package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.entity.StudyDirection;
import studio.banner.officialwebsite.service.IStudyDirectionService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: Re
 * @Date: 2021/5/28 0:01
 */
@RestController
@Slf4j
@Api(tags = "学习方向前台接口", value = "StudyDirectionFrontDeskController")
public class StudyDirectionFrontDeskController {
    @Resource
    protected IStudyDirectionService iStudyDirectionService;

    @GetMapping("/studyDirectionFrontDesk/select")
    @ApiOperation(value = "根据id查询学习方向介绍段落")
    public RespBean select(Integer id) {
        StudyDirection studyDirection = iStudyDirectionService.select(id);
        log.info("根据id查询学习方向介绍段落成功");
        return RespBean.ok("根据id查询学习方向介绍段落成功", studyDirection);
    }

    @GetMapping("/studyDirectionFrontDesk/selectAll")
    @ApiOperation(value = "查询所有学习方向介绍段落")
    public RespBean selectAll() {
        Map<String, Object> map = new HashMap<>();
        List<StudyDirection> studyDirectionList = iStudyDirectionService.selectAll();
        Set<String> directionName = new HashSet<>();
        for (StudyDirection studyDirection : studyDirectionList) {
            directionName.add(studyDirection.getDirection());
        }
        map.put("directionName", directionName);
        map.put("studyDirections", studyDirectionList);
        return RespBean.ok("查询成功", map);
    }
}
