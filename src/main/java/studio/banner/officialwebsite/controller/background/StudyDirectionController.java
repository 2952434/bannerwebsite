package studio.banner.officialwebsite.controller.background;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.entity.StudyDirection;
import studio.banner.officialwebsite.service.IStudyDirectionService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

/**
 * @Author: Re
 * @Date: 2021/5/27 22:28
 */
@RestController
@Slf4j
@Api(tags = "学习方向接口", value = "StudyDirectionController")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class StudyDirectionController {
    @Resource
    protected IStudyDirectionService iStudyDirectionService;

    @PostMapping("/studyDirection/insertStudyDirection")
    @ApiOperation(value = "增加学习方向介绍段落")
    public RespBean insert(@Valid StudyDirection studyDirection) {
        if (iStudyDirectionService.insert(studyDirection)) {
            log.info("增加学习方向介绍段落成功");
            return RespBean.ok("增加学习方向介绍段落成功");
        } else {
            log.info("增加学习方向介绍段落失败");
            return RespBean.error("增加学习方向介绍段落失败");
        }
    }

    @DeleteMapping("/studyDirection/deleteStudyDirection")
    @ApiOperation(value = "删除学习方向介绍段落")
    public RespBean delete(Integer id) {
        if (iStudyDirectionService.delete(id)) {
            log.info("删除学习方向介绍段落成功");
            return RespBean.ok("删除学习方向介绍段落成功");
        } else {
            log.info("删除学习方向介绍段落失败");
            return RespBean.error("删除学习方向介绍段落失败");
        }
    }

    @PutMapping("/studyDirection/updateStudyDirection")
    @ApiOperation(value = "更改学习方向介绍段落")
    public RespBean update(@Valid StudyDirection studyDirection) {
        if (iStudyDirectionService.update(studyDirection)) {
            log.info("更改学习方向介绍段落成功");
            return RespBean.ok("更改学习方向介绍段落成功");
        } else {
            log.info("更改学习方向介绍段落失败");
            return RespBean.error("更改学习方向介绍段落失败");
        }
    }

    @GetMapping("/studyDirection/select")
    @ApiOperation(value = "根据id查询学习方向介绍段落")
    public RespBean select(Integer id) {
        StudyDirection studyDirection = iStudyDirectionService.select(id);
        log.info("根据id查询学习方向介绍段落成功");
        return RespBean.ok("根据id查询学习方向介绍段落成功", studyDirection);
    }

    @GetMapping("/studyDirection/selectAll")
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
