package studio.banner.officialwebsite.controller.background;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.QrCode;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IQrCodeService;
import studio.banner.officialwebsite.service.ITencentYunService;
import studio.banner.officialwebsite.service.ITencentYunService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author hyy
 * @date 2021/5/20 16:50
 * @role
 */
@RestController
@Api(tags = "二维码接口", value = "QrCodeController")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class QrCodeController {
    protected static final Logger logger = LoggerFactory.getLogger(QrCodeController.class);
    @Resource
    private IQrCodeService iQrCodeService;
    @Resource
    private ITencentYunService iTencentYunService;

    @PostMapping(value = "qrCode/insert")
    @ApiOperation("添加二维码图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "photoName", value = "图片名称", dataTypeClass = String.class)
    })
    public RespBean insert(@RequestParam String photoName, String path) {
        QrCode qrCode = new QrCode();
        qrCode.setPhotoName(photoName);
        qrCode.setPhotoAddress(path);
        if (iQrCodeService.insert(qrCode)) {
            logger.info("上传成功");
            return RespBean.ok("上传成功");
        }
        logger.info("上传失败！");
        return RespBean.error("上传失败！");
    }

//    @DeleteMapping(value = "qrCode/delete")
//    @ApiOperation("根据照片名删除图片")
//    @ApiImplicitParam(name = "photoName", value = "图片名称",dataTypeClass = String.class)
//    public RespBean delete(@RequestParam String photoName){
//        if (ITencentYunService.deletePhoto(photoName)&&iQrCodeService.delete(photoName)){
//            return RespBean.ok("删除成功");
//        }
//        return RespBean.error("删除失败,未找到该图片！");
//    }

    @DeleteMapping(value = "qrCode/delete")
    @ApiOperation("根据二维码id删除图片")
    public RespBean delete(@RequestParam Integer qrCodeId) {
        QrCode qrCode = iQrCodeService.selectById(qrCodeId);
        if (qrCode.getPhotoAddress() != null && !"".equals(qrCode.getPhotoAddress())) {
            if (iTencentYunService.delete(qrCode.getPhotoAddress().split("/")[4]) && iQrCodeService.deleteById(qrCodeId)) {
                return RespBean.ok("删除成功");
            }
        }
        return RespBean.error("删除失败,未找到该图片！");
    }

    @PutMapping("qrCode/update")
    @ApiOperation("根据id更改二维码信息")
    public RespBean update(QrCode qrCode) {
        QrCode select = iQrCodeService.selectById(qrCode.getId());
        if (!"".equals(qrCode.getPhotoAddress())) {
            if (select.getPhotoAddress() != null) {
                logger.info("删除二维码图片地址成功");
                iTencentYunService.delete(select.getPhotoAddress().split("/")[4]);
            }
        } else {
            qrCode.setPhotoAddress(select.getPhotoAddress());
        }
        if (iQrCodeService.update(qrCode)) {
            logger.info("更改二维码信息成功");
            return RespBean.ok("更改二维码信息成功");
        } else {
            logger.info("更改二维码信息失败");
            return RespBean.error("更改二维码信息失败");
        }
    }

    @GetMapping(value = "qrCode/select")
    @ApiOperation("根据图片名查询图片地址")
    @ApiImplicitParam(name = "photoName", value = "图片名称", dataTypeClass = String.class)
    public RespBean select(@RequestParam String photoName) {
        QrCode qrCode = iQrCodeService.select(photoName);
        if (qrCode != null) {
            return RespBean.ok("查询成功！", qrCode);
        }
        return RespBean.error("查询失败,未找到该图片！");
    }

    @GetMapping(value = "qrCode/selectAll")
    @ApiOperation("查询所有图片")
    public RespBean selectAll() {
        List<QrCode> list = iQrCodeService.selectAll();
        if (list.size() != 0) {
            return RespBean.ok("查询成功", list);
        }
        return RespBean.error("查询无数据");
    }
}
