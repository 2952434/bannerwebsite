package studio.banner.officialwebsite.controller.frontdesk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.ManagementSystem;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IManagementSystemService;

import java.util.List;
import java.util.Set;

/**
 * @Author: Re
 * @Date: 2021/5/25 17:52
 */
@RestController
@Slf4j
@Api(tags = "前台管理制度接口", value = "ManagementSystemFrontDeskController")
public class ManagementSystemFrontDeskController {
    @Autowired
    private IManagementSystemService iManagementSystemService;

    @GetMapping("/managementSystemFrontDesk/selectSystemByName")
    @ApiOperation(value = "根据管理制度名称查询管理制度信息", httpMethod = "GET")
    public RespBean selectSystemByName(@RequestParam String managementSystemName) {
        if (managementSystemName == null) {
            return RespBean.error("查询失败，managementSystemName不可以为空");
        }
        List<ManagementSystem> list = iManagementSystemService.selectSystemByName(managementSystemName);
        if (list != null) {
            log.info("查询成功！");
            return RespBean.ok("查询成功", list);
        }
        log.info("查询失败！未找到该管理制度！");
        return RespBean.error(String.format("查询失败，制度中未找到[%s]", managementSystemName));
    }

    @GetMapping("/managementSystemFrontDesk/selectSystemBySerialNumber")
    @ApiOperation(value = "根据制度名称和制度内容序号查询指定项制度的指定内容", httpMethod = "GET")
    public RespBean selectSystemBySerialNumber(@RequestParam String managementSystemName, @RequestParam Integer serialNumber) {
        ManagementSystem managementSystem = iManagementSystemService.selectSystemBySerialNumber(managementSystemName, serialNumber);
        if (managementSystem != null) {
            log.info("查询成功！");
            return RespBean.ok("查询成功", managementSystem);
        }
        log.info("查询失败！未找到该管理制度的该条内容！");
        return RespBean.error(String.format("查询失败，制度[%s]中未找到第[%d]条", managementSystemName, serialNumber));
    }

    @GetMapping("/managementSystemFrontDesk/selectAllSystem")
    @ApiOperation(value = "查询所有管理制度", httpMethod = "GET")
    public RespBean selectAllSystem() {
        List<ManagementSystem> list = iManagementSystemService.selectAllSystem();
        if (list == null) {
            return RespBean.error("查询无数据");
        } else {
            return RespBean.ok("查询成功", list);
        }
    }

    @GetMapping("/managementSystemFrontDesk/selectAllSystemName")
    @ApiOperation(value = "查询所有制度名称", httpMethod = "GET")
    public RespBean selectAllSystemName() {
        Set<String> set = iManagementSystemService.selectAllSystemName();
        if (set == null) {
            return RespBean.error("查询无数据");
        }
        return RespBean.ok("查询成功", set);
    }
}
