package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.History;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IHistoryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/27 21:48
 */
@RestController
@Slf4j
@Api(tags = "发展历程前台接口", value = "HistoryFrontDeskController")
public class HistoryFrontDeskController {
    @Resource
    protected IHistoryService iHistoryService;

    @GetMapping("/historyFrontDesk/select")
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

    @GetMapping("/historyFrontDesk/selectAll")
    @ApiOperation(value = "查询工作室发展历史信息")
    public RespBean selectAll() {
        List<History> historyList = iHistoryService.selectAll();
        return RespBean.ok("查询所有工作室发展历史成功", historyList);
    }
}
