package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.QrCode;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IQrCodeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/25 18:04
 */
@RestController
@Slf4j
@Api(tags = "二维码前台接口", value = "QrCodeFrontDeskController")
public class QrCodeFrontDeskController {
    @Resource
    private IQrCodeService iQrCodeService;

    @GetMapping("/qrCodeFrontDesk/selectQrCodeFrontDesk")
    @ApiOperation(value = "根据需要二维码名称查询二维码图片", httpMethod = "GET")
    public RespBean select(@RequestParam String photoName) {
        QrCode qrCode = iQrCodeService.select(photoName);
        if (qrCode != null) {
            log.info("单个二维码查询成功");
            return RespBean.ok("查询成功！", qrCode);
        }
        log.info("查询单个二维码失败");
        return RespBean.error("查询失败,未找到该图片！");
    }

    @GetMapping("/qrCodeFrontDesk/selectAll")
    @ApiOperation(value = "查询所有二维码", httpMethod = "GET")
    public RespBean selectAll() {
        List<QrCode> list = iQrCodeService.selectAll();
        if (list.size() != 0) {
            return RespBean.ok("查询成功", list);
        }
        log.info("查询所有二维码失败");
        return RespBean.error("查询无数据");
    }
}
