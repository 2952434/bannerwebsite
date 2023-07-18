package studio.banner.officialwebsite.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studio.banner.officialwebsite.entity.ManagementSystem;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.service.IManagementSystemService;

import java.util.List;
import java.util.Set;

/**
 * @author hyy
 * @date 2021/5/11 17:18
 * @role 管理制度控制层
 */
@RestController
@Api(tags = "管理制度接口", value = "ManagementSystemController")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ManagementSystemController {
    protected static final Logger logger = LoggerFactory.getLogger(ManagementSystemController.class);
    @Autowired
    private IManagementSystemService iManagementSystemService;

    @PostMapping(value = "/managementSystem/insert")
    @ApiOperation("插入一条管理制度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "managementSystemName", value = "制度名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "managementSystemContent", value = "制度内容", dataTypeClass = String.class),
    })
    public RespBean insertSystem(ManagementSystem managementSystem) {
        if (iManagementSystemService.insertSystem(managementSystem)) {
            logger.info("插入成功!");
            return RespBean.ok("插入成功！");
        }
        logger.info("插入失败，该条制度已经存在！");
        return RespBean.error("插入失败，该条制度已经存在！");
    }

    @DeleteMapping(value = "/managementSystem/delete")
    @ApiOperation("根据制度id删除制度")
    public RespBean delete(@RequestParam Integer managementSystemId) {
        if (iManagementSystemService.delete(managementSystemId)) {
            logger.info("根据制度id删除制度成功");
            return RespBean.ok("删除成功");
        } else {
            logger.info("根据制度id删除制度失败");
            return RespBean.error("删除失败");
        }
    }

    @DeleteMapping(value = "/managementSystem/deleteAll")
    @ApiOperation("根据制度名称删除指定项学习制度的所有内容")
    @ApiImplicitParam(name = "managementSystemName", value = "管理制度名称", dataTypeClass = String.class)
    public RespBean deleteSystem(@RequestParam String managementSystemName) {
        if (managementSystemName == null) {
            return RespBean.error("删除失败，managementSystemName不可以为空");
        }
        if (iManagementSystemService.deleteSystemByName(managementSystemName)) {
            logger.info("删除成功！");
            return RespBean.ok("删除成功！");
        }
        logger.info("删除失败！");
        return RespBean.error(String.format("删除失败，制度中未找到[%s]", managementSystemName));
    }
//    @DeleteMapping(value = "/managementSystem/delete")
//    @ApiOperation("根据制度名称 和 制度内容序号删除指定项制度的指定条内容")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "managementSystemName", value = "管理制度名称", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "serialNumber", value = "制度内容序号", dataTypeClass = Long.class)
//    })
//    public RespBean deleteSystemBySerialNumber(String managementSystemName, Integer serialNumber) {
//        if (iManagementSystemService.deleteSystemBySerialNumber(managementSystemName, serialNumber)) {
//            logger.info("删除成功！");
//            return RespBean.ok("删除成功!");
//        }
//        logger.info("删除失败!");
//        return RespBean.error(String.format("删除失败，制度[%s]中未找到第[%d]条", managementSystemName, serialNumber));
//    }


    @PutMapping(value = "/managementSystem/update")
    @ApiOperation("根据管理制度名称和制度内容序号更新管理制度内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "managementSystemName", value = "制度名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "managementSystemContent", value = "制度内容", dataTypeClass = String.class),
    })
    public RespBean updateSystem(ManagementSystem managementSystem) {
        if (iManagementSystemService.updateSystem(managementSystem)) {
            logger.info("更新成功!");
            return RespBean.ok("更新成功！");
        }
        logger.info("更新失败！");
        return RespBean.error(String.format("更新失败，制度[%s]中未找到[%s]", managementSystem.getManagementSystemName(), managementSystem.getManagementSystemContent()));
    }

    @GetMapping(value = "/managementSystem/selectByName")
    @ApiOperation("根据制度名称查询该制度所有内容")
    @ApiImplicitParam(name = "managementSystemName", value = "管理制度名称", dataTypeClass = String.class)
    public RespBean selectSystemByName(@RequestParam String managementSystemName) {
        if (managementSystemName == null) {
            return RespBean.error("查询失败，managementSystemName不可以为空");
        }
        List<ManagementSystem> list = iManagementSystemService.selectSystemByName(managementSystemName);
        if (list != null) {
            logger.info("查询成功！");
            return RespBean.ok("查询成功", list);
        }
        logger.info("查询失败！未找到该管理制度！");
        return RespBean.error(String.format("查询失败，制度中未找到[%s]", managementSystemName));
    }

//    @GetMapping(value = "/managementSystem/selectBySerialNumber")
//    @ApiOperation("根据制度名称和制度内容序号查询指定制度的指定内容")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "managementSystemName", value = "管理制度名称", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "serialNumber", value = "制度内容序号", dataTypeClass = Long.class)
//    })
//    public RespBean selectSystemBySerialNumber(@RequestParam String managementSystemName, @RequestParam Integer serialNumber) {
//        ManagementSystem managementSystem = iManagementSystemService.selectSystemBySerialNumber(managementSystemName, serialNumber);
//        if (managementSystem != null) {
//            logger.info("查询成功！");
//            return RespBean.ok("查询成功", managementSystem);
//        }
//        logger.info("查询失败！未找到该管理制度的该条内容！");
//        return RespBean.error(String.format("查询失败，制度[%s]中未找到第[%d]条", managementSystemName, serialNumber));
//    }

    @GetMapping(value = "/managementSystem/selectAll")
    @ApiOperation("查询所有管理制度")
    public RespBean selectAllSystem() {
        List<ManagementSystem> list = iManagementSystemService.selectAllSystem();
        if (list == null) {
            return RespBean.error("查询无数据");
        }
        return RespBean.ok("查询成功", list);
    }

    @GetMapping(value = "/managementSystem/selectByPage")
    @ApiOperation("分页查询管理制度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页码数", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "pageSize", value = "页码尺寸", dataTypeClass = Long.class)
    })
    public RespBean selectSystemByPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        IPage<ManagementSystem> iPage = iManagementSystemService.selectSystemByPage(pageNumber, pageSize);
        if (iPage != null) {
            List<ManagementSystem> list = iPage.getRecords();
            logger.info("查询成功");
            return RespBean.ok("查询成功", list);
        } else {
            logger.info("查询失败");
            return RespBean.error("分页查询失败，页码数与页码尺寸不可以为空");
        }
    }

    @GetMapping("/managementSystem/selectAllSystemName")
    @ApiOperation("查询所有制度名称")
    public RespBean selectAllSystemName() {
        Set<String> set = iManagementSystemService.selectAllSystemName();
        if (set == null) {
            return RespBean.error("查询无数据");
        }
        return RespBean.ok("查询成功", set);
    }
}
