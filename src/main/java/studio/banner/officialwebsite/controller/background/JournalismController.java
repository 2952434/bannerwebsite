package studio.banner.officialwebsite.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.Journalism;
import studio.banner.officialwebsite.entity.JournalismPhoto;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IJournalismParagraphService;
import studio.banner.officialwebsite.service.IJournalismPhotoService;
import studio.banner.officialwebsite.service.IJournalismService;
import studio.banner.officialwebsite.service.ITencentYunService;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author: Re
 * @Date: 2021/5/8 20:35
 * @role: 新闻控制层
 */
@RestController
@Api(tags = "新闻接口", value = "JournalismController")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class JournalismController {
    protected static Logger logger = LoggerFactory.getLogger(JournalismController.class);
    @Autowired
    protected IJournalismService iJournalismService;
    @Autowired
    protected IJournalismParagraphService iJournalismParagraphService;
    @Autowired
    protected IJournalismPhotoService iJournalismPhotoService;
    @Autowired
    protected ITencentYunService iTencentYunService;

    @ApiOperation(value = "增加新闻", notes = "新闻对象不能为空", httpMethod = "POST")
    @PostMapping(value = "/journalism/insertJournalism")
    public RespBean insert(Journalism journalism) {
        if (iJournalismService.insert(journalism)) {
            logger.info("新闻" + journalism.getJournalismTitle() + "插入成功");
            return RespBean.ok("新闻" + journalism.getJournalismTitle() + "插入成功");
        } else {
            logger.info("新闻" + journalism.getJournalismTitle() + "插入失败");
            return RespBean.error("新闻" + journalism.getJournalismTitle() + "插入失败");
        }
    }

    @DeleteMapping(value = "/journalism/deleteJournalism")
    @ApiOperation(value = "新闻删除", notes = "用户id不能为空", httpMethod = "DELETE")
    public RespBean delete(@RequestParam Integer id) {
        if (id != null) {
            if (iJournalismService.delete(id)) {
                logger.info("新闻删除成功");
                if (iJournalismParagraphService.deleteAll(id)) {
                    logger.info("新闻段落删除成功");
                } else {
                    logger.info("新闻段落删除失败");
                }
                List<JournalismPhoto> journalismPhotos = iJournalismPhotoService.selectAll(id);
                for (JournalismPhoto journalismPhoto : journalismPhotos) {
                    if (journalismPhoto.getPhotoAddress() != null && !"".equals(journalismPhoto.getPhotoAddress())) {
                        iTencentYunService.delete(journalismPhoto.getPhotoAddress().split("/")[4]);
                    }
                }
                if (iJournalismPhotoService.deleteAll(id)) {
                    logger.info("新闻图片删除成功");
                } else {
                    logger.info("新闻图片删除是失败");
                }
                return RespBean.ok("新闻删除成功");
            } else {
                logger.info("新闻删除失败");
                return RespBean.error("新闻删除失败");
            }
        } else {
            logger.error("id为空,删除失败");
            return RespBean.error("传入id为空，删除失败");
        }
    }

    @PutMapping(value = "/journalism/updateJournalism")
    @ApiOperation(value = "更改新闻", notes = "新闻对象不能为空", httpMethod = "PUT")
    public RespBean update(@Valid Journalism journalism) {
        if (iJournalismService.update(journalism)) {
            logger.info("新闻更改成功");
            return RespBean.ok("新闻更改成功");
        } else {
            logger.info("新闻更改失败");
            return RespBean.error("新闻更改失败");
        }
    }

    @GetMapping(value = "/journalism/selectById")
    @ApiOperation(value = "查看新闻", notes = "传入的id不能为空", httpMethod = "GET")
    public RespBean select(@RequestParam Integer id) {
        Journalism journalism = iJournalismService.select(id);
        if (journalism == null) {
            logger.info("新闻不存在");
            return RespBean.error("新闻不存在");
        } else {
            logger.info("新闻" + journalism.getJournalismTitle() + "，创建时间" + journalism.getJournalismTimeYear() + "-" + journalism.getJournalismTimeMonth() + "-" + journalism.getJournalismTimeDay());
            return RespBean.ok("查询成功", journalism);
        }
    }

    @GetMapping(value = "/journalism/selectAll")
    @ApiOperation(value = "查看所有新闻", httpMethod = "GET")
    public RespBean selectAll() {
        List<Journalism> journalismList = iJournalismService.selectAllJournalism();
        if (journalismList.size() != 0) {
            for (Journalism journalism : journalismList) {
                logger.info(journalism.toString());
            }
            return RespBean.ok("查询成功", journalismList);
        } else {
            logger.info("暂无新闻存在");
            return RespBean.error("暂无新闻存在");
        }
    }

    @GetMapping(value = "/journalism/IPageSelect")
    @ApiOperation(value = "分页查询", notes = "传入合适的当前页数和页面大小", httpMethod = "GET")
    public RespBean IPageSelect(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        IPage<Journalism> iPage = iJournalismService.selectJournalismByPage(pageNumber, pageSize);
        List<Journalism> journalismList = iPage.getRecords();
        if (journalismList.size() != 0) {
            for (Journalism journalism : journalismList) {
                logger.info("新闻名称为" + journalism);
            }
            return RespBean.ok("查询成功", journalismList);
        } else {
            logger.info("该页码数不存在数据");
            return RespBean.error("该页码数不存在数据");
        }
    }
}
