package studio.banner.officialwebsite.controller.background;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.entity.ThemePhoto;
import studio.banner.officialwebsite.service.ITencentYunService;
import studio.banner.officialwebsite.service.IThemePhotoService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Re
 * @Date: 2021/6/8 18:27
 */
@RestController
@Slf4j
@Api(tags = "主题图片接口", value = "ThemePhotoController")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ThemePhotoController {
    @Resource
    protected IThemePhotoService iThemePhotoService;
    @Resource
    protected ITencentYunService iTencentYunService;

    @PostMapping("/ThemePhotoController/insert")
    @ApiOperation("增加主题图片")
    public RespBean insert(@Valid ThemePhoto themePhoto) {
        if (iThemePhotoService.insert(themePhoto)) {
            log.info("添加成功");
            return RespBean.ok("增加成功");
        } else {
            log.info("已有该图片添加失败");
            return RespBean.error("已有该图片添加失败");
        }
    }

    @DeleteMapping("/ThemePhotoController/delete")
    @ApiOperation("根据月份删除关联的主题图片")
    public RespBean delete(@RequestParam Integer month) {
        List<ThemePhoto> themePhotoList = iThemePhotoService.selectByMonth(month);
        for (ThemePhoto themePhoto : themePhotoList) {
            if (themePhoto.getUrl() == null) {
                continue;
            }
            iTencentYunService.delete(themePhoto.getUrl().split("/")[4]);
        }
        if (iThemePhotoService.delete(month)) {
            log.info("删除成功");
            return RespBean.ok("删除成功");
        } else {
            log.info("删除失败");
            return RespBean.error("删除失败");
        }
    }

    @DeleteMapping("/ThemePhotoController/deleteById")
    @ApiOperation("根据id删除主题图片")
    public RespBean deleteById(@RequestParam Integer id) {
        ThemePhoto themePhoto = iThemePhotoService.selectById(id);
        if (themePhoto.getUrl() != null) {
            iTencentYunService.delete(themePhoto.getUrl().split("/")[4]);
            log.info("腾讯云主题图片删除成功");
        }
        if (iThemePhotoService.deleteById(id)) {
            log.info("删除成功");
            return RespBean.ok("删除成功");
        } else {
            log.info("删除失败");
            return RespBean.error("删除失败");
        }
    }

    @PutMapping("/ThemePhotoController/update")
    @ApiOperation("更改月份主题图片")
    public RespBean update(@RequestParam Integer month, String url1, String url2, String url3, String url4) {
        if (iThemePhotoService.update(month, url1, url2, url3, url4)) {
            log.info("更改月份主题图片成功");
            return RespBean.ok("更改月份主题图片成功");
        } else {
            log.info("更改月份主题图片失败");
            return RespBean.error("更改月份主题图片失败");
        }
    }

    @PutMapping("/ThemePhotoController/updateById")
    @ApiOperation("根据主题图片id更改主题图片")
    public RespBean updateById(@RequestParam Integer id, String url, String css) {
        if (iThemePhotoService.updateById(id, url, css)) {
            log.info("根据id更改主题图片成功");
            return RespBean.ok("根据id更改主题图片成功");
        } else {
            log.info("根据id更改主题图片失败");
            return RespBean.error("根据id更改主题图片失败");
        }
    }

    @GetMapping("/themePhotoController/select")
    @ApiOperation("根据月份查询主题图片")
    public RespBean select(@RequestParam Integer photoMonth) {
        List<ThemePhoto> themePhotoList = iThemePhotoService.selectByMonth(photoMonth);
        if (themePhotoList.size() == 0) {
            log.info("查询失败,查询月份主题图片为空");
            return RespBean.error("查询月份主题图片为空");
        } else {
            Map<String, String> urlMap = new HashMap<>();
            for (int i = 0; i < themePhotoList.size(); i++) {
                urlMap.put((i + 1) + "", themePhotoList.get(i).getUrl());
            }
            log.info("查询月份主题图片为成功");
            return RespBean.ok("查询月份主题图片为成功", urlMap);
        }
    }

    @GetMapping("/ThemePhotoController/selectAll")
    @ApiOperation("查询所有主题图片信息")
    public RespBean selectAll() {
        List<ThemePhoto> themePhotoList = iThemePhotoService.selectAll();
        if (themePhotoList.size() == 0) {
            log.info("主题图片为空，查询失败");
            return RespBean.error("主题图片为空，查询失败");
        } else {
            log.info("查询成功");
            return RespBean.ok("主题图片为空，查询成功", themePhotoList);
        }
    }
}
