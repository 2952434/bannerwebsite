package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IJournalismPhotoService;

/**
 * @Author: Re
 * @Date: 2021/5/26 0:05
 */
@RestController
@Api(tags = "获取轮播图接口", value = "IsCarouselController")
public class IsCarouselController {
    @Autowired
    protected IJournalismPhotoService iJournalismPhotoService;

    @GetMapping("/isCarousel/selectIsCarousel")
    @ApiOperation(value = "查询轮播图", httpMethod = "GET")
    public RespBean selectIsCarousel() {
        return RespBean.ok("查询成功", iJournalismPhotoService.selectIsCarousel());
    }
}
