package studio.banner.officialwebsite.service;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/26 16:57
 */
public interface IProjectPersonnelService {
    /**
     * 增加项目关联成员
     * @param memberId
     * @param projectId
     * @return
     */
    boolean insert(Integer memberId,Integer projectId);

    /**
     * 删除项目关联成员
     * @param memberId
     * @param projectId
     * @return
     */
    boolean delete(Integer memberId,Integer projectId);

    /**
     * 根据成员id删除关联表中成员信息
     * @param memberId
     * @return
     */
    boolean deleteAllByMemberId(Integer memberId);

    /**
     * 根据项目id删除所有关联成员
     * @param projectId
     * @return
     */
    boolean deleteAllByProjectId(Integer projectId);
    /**
     * 查询一个项目关联的所有成员
     * @param projectId
     * @return
     */
     List<Integer> selectById(Integer projectId);
}
