package studio.banner.officialwebsite.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import studio.banner.officialwebsite.entity.StudyDirection;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/27 22:14
 */
public interface IStudyDirectionService {
    /**
     * 增加学习方向介绍段落
     * @param studyDirection
     * @return
     */
    boolean insert(StudyDirection studyDirection);

    /**
     * 删除学习方向介绍段落
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 更改学习方向介绍段落
     * @param studyDirection
     * @return
     */
    boolean update(StudyDirection studyDirection);

    /**
     * 根据id查询学习方向介绍段落
     * @param id
     * @return
     */
    StudyDirection select(Integer id);

    /**
     * 查询方向所有的介绍段落
     * @param direction
     * @return
     */
    List<StudyDirection> selectDirectionAll(String direction);

    /**
     * 查询所有介绍段落
     * @return
     */
    List<StudyDirection> selectAll();

    IPage<StudyDirection> selectByPage(Integer pageNumber,Integer pageSize);
}
