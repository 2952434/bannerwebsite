package studio.banner.officialwebsite.service;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/27 8:17
 */
public interface ISoftwareCopyrightPersonnelService {
    /**
     * 增加软著信息关联成员
     * @param memberId
     * @param softwareCopyrightId
     * @return
     */
    boolean insert(Integer memberId,Integer softwareCopyrightId);

    /**
     * 删除软著信息关联成员
     * @param memberId
     * @param softwareCopyrightId
     * @return
     */
    boolean delete(Integer memberId,Integer softwareCopyrightId);

    /**
     * 根据成员id查询删除关联成员信息
     * @param memberId
     * @return
     */
    boolean deleteAllByMemberId(Integer memberId);

    /**
     * 根据软著id删除所有关联成员信息
     * @param softwareCopyrightId
     * @return
     */
    boolean deleteAllBySoftwareCopyrightId(Integer softwareCopyrightId);
    /**
     * 根据软著信息id查询关联成员
     * @param softwareCopyrightId
     * @return
     */
    List<Integer> select(Integer softwareCopyrightId);
}
