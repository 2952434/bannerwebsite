package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.ManagementSystem;
import studio.banner.officialwebsite.mapper.ManagementSystemMapper;
import studio.banner.officialwebsite.service.IManagementSystemService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hyy
 * @date 2021/5/11 16:56
 * @role: 管理制度Service层实现
 */
@Service
public class ManagementSystemServiceImpl implements IManagementSystemService {
    protected static final Logger logger = LoggerFactory.getLogger(ManagementSystemServiceImpl.class);
    @Autowired
    private ManagementSystemMapper systemMapper;

    @Override
    public boolean insertSystem(ManagementSystem managementSystem) {
        QueryWrapper<ManagementSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper
                .eq("management_system_name", managementSystem.getManagementSystemName())
                .eq("management_system_content", managementSystem.getManagementSystemContent());
        List<ManagementSystem> managementSystems = systemMapper.selectList(queryWrapper);
        if (managementSystems.size() == 0) {
            logger.info("插入成功！---service");
            return systemMapper.insert(managementSystem) != 0;
        }
        logger.info("插入失败！---service");
        return false;
    }

    @Override
    public boolean delete(Integer managementSystemId) {
        QueryWrapper<ManagementSystem> queryWrapper = new QueryWrapper<>();
        return systemMapper.deleteById(managementSystemId) == 1;
    }

    @Override
    public boolean deleteSystemByName(String managementSystemName) {
        QueryWrapper<ManagementSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("management_system_name", managementSystemName);
        List<ManagementSystem> managementSystems = systemMapper.selectList(queryWrapper);
        if (managementSystems.size() != 0) {
            logger.info("删除成功！---service");
            systemMapper.delete(queryWrapper);
            return true;
        }
        logger.info("管理制度为空，删除失败！---service");
        return false;
    }

    @Override
    public boolean deleteSystemBySerialNumber(String systemName, Integer serialNumber) {
        QueryWrapper<ManagementSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper
                .eq("management_system_name", systemName)
                .eq("serial_number", serialNumber);
        List<ManagementSystem> list = systemMapper.selectList(queryWrapper);
        if (list.size() != 0) {
            logger.info("删除成功！---service");
            return systemMapper.delete(queryWrapper) != 0;
        }
        logger.info("删除失败！---service");
        return false;
    }

    @Override
    public boolean updateSystem(ManagementSystem managementSystem) {
        return systemMapper.updateById(managementSystem) == 1;
    }

    @Override
    public List<ManagementSystem> selectSystemByName(String managementSystemName) {
        QueryWrapper<ManagementSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("management_system_name", managementSystemName);
        List<ManagementSystem> managementSystems = systemMapper.selectList(queryWrapper);
        if (managementSystems.size() != 0) {
            logger.info("查询成功！");
            return managementSystems;
        }
        logger.info("查询失败！未找到该管理制度！---service");
        return null;
    }

    @Override
    public ManagementSystem selectSystemBySerialNumber(String managementSystemName, Integer serialNumber) {
        QueryWrapper<ManagementSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper
                .eq("management_system_name", managementSystemName)
                .eq("serial_number", serialNumber);
        List<ManagementSystem> list = systemMapper.selectList(queryWrapper);
        if (list.size() != 0) {
            logger.info("查询成功！---service");
            return list.get(0);
        }
        logger.info("修改失败！---service");
        return null;
    }

    @Override
    public List<ManagementSystem> selectAllSystem() {
        return systemMapper.selectList(null);
    }

    @Override
    public IPage<ManagementSystem> selectSystemByPage(Integer pageNumber, Integer pageSize) {
        if (pageNumber!=null&&pageSize!=null){
            Page<ManagementSystem> page = new Page<>(pageNumber, pageSize);
            return systemMapper.selectPage(page, null);
        }
        return null;
    }

    @Override
    public Set<String> selectAllSystemName() {
        List<ManagementSystem> systems = systemMapper.selectList(null);
        Set<String> strings = new HashSet<>();
        if (systems.size()!=0){
            for (ManagementSystem managementSystem : systems) {
                strings.add(managementSystem.getManagementSystemName());
            }
            return strings;
        }
        return null;
    }
}
