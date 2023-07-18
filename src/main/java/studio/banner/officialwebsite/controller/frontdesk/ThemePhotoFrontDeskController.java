package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.entity.ThemePhoto;
import studio.banner.officialwebsite.service.IThemePhotoService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Re
 * @Date: 2021/6/8 18:16
 */
@RestController
@Slf4j
@Api(tags = "主题图片前台接口", value = "ThemePhotoFrontDeskController")
public class ThemePhotoFrontDeskController {
    @Resource
    protected IThemePhotoService iThemePhotoService;

    @GetMapping("/themePhotoFrontDeskController/select")
    @ApiOperation("根据月份查询主题图片")
    public RespBean select(@RequestParam Integer photoMonth) {
        List<ThemePhoto> themePhotoList = iThemePhotoService.selectByMonth(photoMonth);
        if (themePhotoList.size() == 0) {
            log.info("查询失败,查询月份主题图片为空");
            return RespBean.error("查询月份主题图片为空");
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("PhotoColor", themePhotoList.get(0).getCss());
            for (int i = 0; i < themePhotoList.size(); i++) {
                map.put((i + 1) + "", themePhotoList.get(i).getUrl());
            }
            log.info("查询月份主题图片为成功");
            return RespBean.ok("查询月份主题图片为成功", map);
        }
    }
}
