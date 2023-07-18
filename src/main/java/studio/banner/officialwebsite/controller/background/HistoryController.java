package studio.banner.officialwebsite.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.History;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IHistoryService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/27 20:28
 */
@RestController
@Slf4j
@Api(tags = "工作室发展历史接口", value = "HistoryController")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class HistoryController {
    @Resource
    private IHistoryService iHistoryService;

    @PostMapping("/history/insertHistory")
    @ApiOperation(value = "增加工作室发展历史信息")
    public RespBean insert(@Valid History history) {
        if (iHistoryService.insert(history)) {
            log.info("增加工作室发展历史成功");
            return RespBean.ok("增加工作室发展历史成功");
        } else {
            log.info("增加工作室发展历史失败");
            return RespBean.error("增加工作室发展历史失败");
        }
    }

    @DeleteMapping("/history/deleteHistory")
    @ApiOperation(value = "删除工作室发展历史信息")
    public RespBean delete(Integer id) {
        if (iHistoryService.delete(id)) {
            log.info("删除工作室发展历史成功");
            return RespBean.ok("删除工作室发展历史成功");
        } else {
            log.info("删除工作室发展历史失败");
            return RespBean.error("删除工作室发展历史失败");
        }
    }

    @PutMapping("/history/updateHistory")
    @ApiOperation(value = "更改工作室发展历史信息")
    public RespBean update(History history) {
        if (iHistoryService.update(history)) {
            log.info("更改工作室发展历史成功");
            return RespBean.ok("更改工作室发展历史成功");
        } else {
            log.info("更改工作室发展历史失败");
            return RespBean.error("更改工作室发展历史失败");
        }
    }

    @GetMapping("/history/select")
    @ApiOperation(value = "根据id查询工作室发展历史信息")
    public RespBean select(Integer id) {
        History history = iHistoryService.select(id);
        if (history != null) {
            log.info("查询工作室发展历史成功");
            return RespBean.ok("查询工作室发展历史成功", history);
        } else {
            log.info("查询工作室发展历史失败");
            return RespBean.error("查询工作室发展历史失败");
        }
    }

    @GetMapping("/history/selectAll")
    @ApiOperation(value = "查询工作室发展历史信息")
    public RespBean selectAll() {
        List<History> historyList = iHistoryService.selectAll();

        return RespBean.ok("查询所有工作室发展历史成功", historyList);
    }

    @GetMapping("/history/selectByPage")
    public RespBean selectByPage(Integer pageNumber, Integer pageSize) {
        IPage<History> iPage = iHistoryService.selectByPage(pageNumber, pageSize);
        List<History> historyList = iPage.getRecords();
        if (pageNumber != null && pageSize != null) {
            if (historyList.size() != 0) {
                log.info("查询成功");
                return RespBean.ok("查询成功", historyList);
            } else {
                log.info("工作室发展历史信息为零");
                return RespBean.error("工作室发展历史信息为零");
            }
        } else {
            log.info("传入的页码数或页的尺寸为空");
            return RespBean.error("传入的页码数或页的尺寸为空");
        }
    }
}
