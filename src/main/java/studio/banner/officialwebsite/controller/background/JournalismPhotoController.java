package studio.banner.officialwebsite.controller.background;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.JournalismPhoto;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IJournalismPhotoService;
import studio.banner.officialwebsite.service.ITencentYunService;
import studio.banner.officialwebsite.service.ITencentYunService;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/18 18:39
 */
@RestController
@Api(tags = "新闻图片接口", value = "JournalismPhotoController")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class JournalismPhotoController {
    protected static Logger logger = LoggerFactory.getLogger(JournalismPhotoController.class);
    @Autowired
    protected IJournalismPhotoService iJournalismPhotoService;
    @Autowired
    protected ITencentYunService iTencentYunService;

    @PostMapping(value = "/journalismPhoto/insertJournalismPhoto")
    @ApiOperation(value = "增加新闻图片")
    public RespBean insert(Integer journalismId, Integer paragraphNumber, String path, Integer isCarousel) {
        JournalismPhoto journalismPhoto = new JournalismPhoto();
        journalismPhoto.setJournalismId(journalismId);
        journalismPhoto.setIsCarousel(isCarousel);
        journalismPhoto.setJournalismParagraphNumber(paragraphNumber);
        journalismPhoto.setPhotoAddress(path);
        if (iJournalismPhotoService.insert(journalismPhoto)) {
            logger.info("插入图片信息成功");
            return RespBean.ok("插入图片信息成功");
        } else {
            logger.info("插入图片信息失败");
            return RespBean.error("插入图片信息失败");
        }
    }

    @PutMapping(value = "/journalismPhoto/updateJournalismPhoto")
    @ApiOperation(value = "更改新闻图片信息")
    @ApiImplicitParam(type = "update", name = "id", value = "用户名", required = true, dataTypeClass = Long.class)
    public RespBean update(Integer id, Integer paragraphNumber, String path, Integer isCarousel) {
        JournalismPhoto journalismPhoto = new JournalismPhoto();
        journalismPhoto.setJournalismId(id);
        journalismPhoto.setIsCarousel(isCarousel);
        journalismPhoto.setJournalismParagraphNumber(paragraphNumber);
        journalismPhoto.setPhotoAddress(path);
        iTencentYunService.delete(iJournalismPhotoService.select(id).getPhotoAddress().split("/")[4]);
        if (iJournalismPhotoService.update(journalismPhoto)) {
            logger.info("新闻照片上传成功");
            return RespBean.ok("新闻照片上传成功");
        }
        logger.info("新闻照片上传失败");
        return RespBean.error("新闻照片上传失败");
    }

    @GetMapping("/journalismPhoto/selectJournalismPhoto")
    @ApiOperation(value = "查询单个新闻照片", notes = "上传的id不为空", httpMethod = "GET")
    public RespBean select(Integer id) {
        JournalismPhoto journalismPhoto = iJournalismPhotoService.select(id);
        return RespBean.ok("新闻照片查询成功", journalismPhoto);
    }

    @GetMapping("/journalismPhoto/selectAll")
    @ApiOperation(value = "根据新闻id查询新闻所有图片")
    public RespBean selectAll(Integer id) {
        List<JournalismPhoto> journalismPhotos = iJournalismPhotoService.selectAll(id);
        return RespBean.ok("新闻照片查询成功", journalismPhotos);
    }

    @PutMapping("/journalismPhoto/updateIsCarousel")
    @ApiOperation(value = "更改图片轮播状态")
    public RespBean updateIsCarousel(Integer id, Integer isCarousel) {
        JournalismPhoto journalismPhoto = new JournalismPhoto();
        journalismPhoto.setId(id);
        journalismPhoto.setIsCarousel(isCarousel);
        if (iJournalismPhotoService.update(journalismPhoto)) {
            logger.info("轮播状态更改成功");
            return RespBean.ok("轮播状态更改成功");
        } else {
            logger.info("轮播状态更改失败");
            return RespBean.error("轮播状态更改失败");
        }
    }

    @GetMapping("/journalismPhoto/selectIsCarousel")
    @ApiOperation(value = "查询轮播图片")
    public RespBean selectIsCarousel() {
        List<JournalismPhoto> journalismPhotos = iJournalismPhotoService.selectIsCarousel();
        return RespBean.ok("轮播图片查询成功", journalismPhotos);
    }
}
