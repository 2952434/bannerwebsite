package studio.banner.officialwebsite.controller.background;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.JournalismParagraph;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IJournalismParagraphService;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/24 20:41
 */
@RestController
@Api(tags = "新闻段落接口", value = "JournalismParagraphController")
@ResponseBody
@Slf4j
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class JournalismParagraphController {
    @Autowired
    protected IJournalismParagraphService iJournalismParagraphService;

    @PostMapping("/journalismParagraph/insertJournalismParagraph")
    @ApiOperation(value = "增加新闻段落", notes = "内容和关联的新闻id不能为空", httpMethod = "POST")
    public RespBean insert(@Valid JournalismParagraph journalismParagraph) {
        if (iJournalismParagraphService.insert(journalismParagraph)) {
            log.info("新闻段落增加成功");
            return RespBean.ok("新闻段落增加成功");
        } else {
            log.info("新闻段落增加失败");
            return RespBean.error("新闻段落增加失败");
        }
    }

    @PutMapping("/journalismParagraph/updateJournalismParagraph")
    @ApiOperation(value = "更改新闻段落", notes = "id为准备更改段落的id", httpMethod = "PUT")
    public RespBean update(@Valid JournalismParagraph journalismParagraph) {
        if (iJournalismParagraphService.update(journalismParagraph)) {
            log.info("新闻段落更改成功");
            return RespBean.ok("新闻段落更改成功");
        } else {
            log.info("新闻段落更改失败");
            return RespBean.error("新闻段落更改失败");
        }
    }

    @DeleteMapping("/journalismParagraph/deleteJournalismParagraph")
    @ApiOperation(value = "删除新闻段落", notes = "id为准备删除段落的id", httpMethod = "DELETE")
    public RespBean delete(Integer id) {
        if (iJournalismParagraphService.delete(id)) {
            log.info("新闻段落删除成功");
            return RespBean.ok("新闻段落删除成功");
        } else {
            log.info("新闻段落删除失败");
            return RespBean.error("新闻段落删除失败");
        }
    }

    @GetMapping("/journalismParagraph/selectJournalismParagraph")
    @ApiOperation(value = "查询单个新闻段落", notes = "id为准备查询的段落id", httpMethod = "GET")
    public RespBean select(Integer id) {
        JournalismParagraph journalismParagraph = iJournalismParagraphService.select(id);
        if (journalismParagraph != null) {
            log.info("单个新闻段落查询成功");
            return RespBean.ok("单个新闻段落查询成功");
        } else {
            log.info("单个新闻段落查询失败");
            return RespBean.error("单个新闻段落查询失败");
        }
    }

    @GetMapping("/journalismParagraph/selectAll")
    @ApiOperation(value = "查询新闻所有段落", notes = "id为准备查询新闻的id", httpMethod = "GET")
    public RespBean selectAll(Integer id) {
        List<JournalismParagraph> journalismParagraphList = iJournalismParagraphService.selectAll(id);
        log.info("查询新闻段落的数量" + journalismParagraphList.size());
        return RespBean.ok("查询新闻段落成功", journalismParagraphList);
    }
}
