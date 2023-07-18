package studio.banner.officialwebsite.service;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/27 9:22
 */
public interface IAwardPersonnelService {
    /**
     * 增加获奖信息关联成员id
     * @param memberId
     * @param awardId
     * @return
     */
    boolean insert(Integer memberId,Integer awardId);

    /**
     * 删除获奖信息关联的单个成员的id
     * @param memberId
     * @param awardId
     * @return
     */
    boolean delete(Integer memberId,Integer awardId);

    /**
     * 删除获奖信息关联的所有成员的id
     * @param awardId
     * @return
     */
    boolean deleteAll(Integer awardId);

    /**
     * 根据用户id删除关联成员信息
     * @param memberId
     * @return
     */
    boolean deleteAllByMemberId(Integer memberId);

    /**
     * 根据项目id查询项目关联人员
     * @param awardId
     * @return
     */
    List<Integer> select(Integer awardId);
}
