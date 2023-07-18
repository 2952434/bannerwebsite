package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.Journalism;
import studio.banner.officialwebsite.entity.JournalismParagraph;
import studio.banner.officialwebsite.entity.JournalismPhoto;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IJournalismParagraphService;
import studio.banner.officialwebsite.service.IJournalismPhotoService;
import studio.banner.officialwebsite.service.IJournalismService;

import java.util.*;

/**
 * @Author: Re
 * @Date: 2021/5/25 17:31
 */
@RestController
@Api(tags = "新闻前台接口", value = "JournalismFrontDeskController")
public class JournalismFrontDeskController {
    @Autowired
    protected IJournalismService iJournalismService;
    @Autowired
    protected IJournalismPhotoService iJournalismPhotoService;
    @Autowired
    protected IJournalismParagraphService iJournalismParagraphService;

    @GetMapping("/journalismFrontDesk/selectJournalismFrontDesk")
    @ApiOperation(value = "根据id查询新闻", httpMethod = "GET")
    public RespBean select(Integer id) {
        Map<String, Object> returnMap = new HashMap<>();
        Journalism journalism = iJournalismService.select(id);
        returnMap.put("journalism", journalism);
        List<JournalismParagraph> journalismParagraphs = iJournalismParagraphService.selectAll(id);
        returnMap.put("journalismParagraphs", journalismParagraphs);
        List<JournalismPhoto> journalismPhotos = iJournalismPhotoService.selectAll(id);
        returnMap.put("journalismPhotos", journalismPhotos);
        return RespBean.ok("新闻信息查询成功", returnMap);
    }

    @GetMapping("/journalismFrontDesk/selectAll")
    @ApiOperation(value = "查询所有新闻", httpMethod = "GET")
    public RespBean selectAll() {
        List<Map<String, Object>> returnList = new ArrayList<>();
        List<Journalism> journalismList = iJournalismService.selectAllJournalism();
        for (Journalism journalism : journalismList) {
            Integer id = journalism.getId();
            Map<String, Object> map = new HashMap<>();
            map.put("journalism", journalism);
            List<JournalismPhoto> journalismPhotos = iJournalismPhotoService.selectAll(id);
            map.put("journalismPhotos", journalismPhotos);
            returnList.add(map);
        }
        return RespBean.ok("所有新闻信息查询成功", returnList);
    }

    @GetMapping("/journalismFrontDesk/selectIsCarousel")
    @ApiOperation(value = "查询轮播图片")
    public RespBean selectIsCarousel() {
        List<JournalismPhoto> journalismPhotos = iJournalismPhotoService.selectIsCarousel();
        Collections.sort(journalismPhotos, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                JournalismPhoto journalismPhoto1 = (JournalismPhoto) o1;
                JournalismPhoto journalismPhoto2 = (JournalismPhoto) o2;
                if (journalismPhoto1.getId() > journalismPhoto2.getId()) {
                    return -1;
                } else if (journalismPhoto1.getId().equals(journalismPhoto2.getId())) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return RespBean.ok("轮播图片查询成功", journalismPhotos);
    }
}
