package studio.banner.officialwebsite.service;

import studio.banner.officialwebsite.entity.JournalismPhoto;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/17 22:25
 */
public interface IJournalismPhotoService {
    /**
     * 增加新闻图片
     * @param journalismPhoto
     * @return
     */
    boolean insert(JournalismPhoto journalismPhoto);

    /**
     * 根据 id 删除新闻图片
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据新闻id删除所有新闻图片
     * @param journalismId
     * @return
     */
    boolean deleteAll(Integer journalismId);

    /**
     * 根据 id 更改照片
     * @param journalismPhoto
     * @return
     */
    boolean update(JournalismPhoto journalismPhoto);

    /**
     * 根据 id 查找新闻
     * @param id
     * @return
     */
    JournalismPhoto select(Integer id);

    /**
     * 根据新闻id查询所有图片
     * @return
     * @param journalismId
     */
    List<JournalismPhoto> selectAll(Integer journalismId);

    /**
     * 查询为轮播图的所有图片
     * @return
     */
    List<JournalismPhoto> selectIsCarousel();

}
