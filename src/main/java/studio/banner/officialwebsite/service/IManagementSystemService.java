package studio.banner.officialwebsite.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import studio.banner.officialwebsite.entity.ManagementSystem;

import java.util.List;
import java.util.Set;

/**
 * @author hyy
 * @date 2021/5/11 16:56
 * @role: 管理制度Service层接口
 */
public interface IManagementSystemService {
    /**
     * 插入一条管理制度
     * @param managementSystem
     * @return
     */
    boolean insertSystem(ManagementSystem managementSystem);

    /**
     * 根据id删除管理制度
     * @param managementSystemId
     * @return
     */
    boolean delete(Integer managementSystemId);

    /**
     * 根据制度名称删除指定项制度的所有内容
     * @param managementSystemName
     * @return
     */
    boolean deleteSystemByName(String managementSystemName);

    /**
     * 根据 制度名称 和 制度内容序号 删除指定项制度的指定条内容
     * @param systemName
     * @param serialNumber
     * @return
     */
    boolean deleteSystemBySerialNumber(String systemName,Integer serialNumber);

    /**
     * 更新制度内容
     * @param managementSystem
     * @return
     */
    boolean updateSystem(ManagementSystem managementSystem);

    /**
     * 根据制度名称查询该制度所有内容
     * @param managementSystemName
     * @return
     */
    List<ManagementSystem> selectSystemByName(String managementSystemName);

    /**
     * 根据制度名称和制度内容序号查询指定项制度的指定内容
     * @param managementSystemName
     * @param serialNumber
     * @return
     */
    ManagementSystem selectSystemBySerialNumber(String managementSystemName,Integer serialNumber);

    /**
     * 查询所有管理制度
     * @return
     */
    List<ManagementSystem> selectAllSystem();

    /**
     * 通过分页查询管理制度
     * @param pageNumber
     * @param pageSize
     * @return
     */
    IPage<ManagementSystem> selectSystemByPage(Integer pageNumber,Integer pageSize);

    /**
     * 查询所有
     * @return
     */
    Set<String> selectAllSystemName();
}
