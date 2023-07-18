package studio.banner.officialwebsite.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import studio.banner.officialwebsite.entity.Award;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/10 21:39
 * @role: 获奖信息Service层接口
 */
public interface IAwardService {
    /**
     * 增加获奖信息
     * @param award
     * @return
     */
    boolean insert(Award award);

    /**
     * 删除获奖信息
     * @param id
     * @return
     */
    boolean delete(Integer id);
    /**
     * 更改获奖信息
     * @param award
     * @return
     */
    boolean update(Award award);

    /**
     * 根据id查看获奖信息
     * @param id
     * @return
     */
    Award select(Integer id);

    /**
     * 查看所有的获奖信息
     * @return
     */
    List<Award> selectAll();

    /**
     * 查看所有项目获奖名单
     * @return
     */
    List<Award> selectProjectAll();

    /**
     * 查看所有竞赛获奖名单
     * @return
     */
    List<Award> selectCompetitionAll();

    /**
     * 分页查询获奖信息
     * @param pageNumber
     * @param pageSize
     * @return
     */
    IPage<Award> selectAwardByPage(Integer pageNumber,Integer pageSize);

    /**
     * 根据获奖信息的信息查询获奖信息的id
     * @param year
     * @param month
     * @param day
     * @param classify
     * @param awardName
     * @param raceOrProjectName
     * @return
     */
    Integer selectId(Integer year,Integer month,Integer day,String classify,String awardName,String raceOrProjectName);
}
