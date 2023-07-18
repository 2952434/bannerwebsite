package studio.banner.officialwebsite.service;

import studio.banner.officialwebsite.entity.ThemePhoto;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/6/8 17:24
 */
public interface IThemePhotoService {
    /**
     * 增加主题图片
     * @param themePhoto
     * @return
     */
    boolean insert(ThemePhoto themePhoto);

    /**
     * 根据月份删除主题图片
     * @param month
     * @return
     */
    boolean delete(Integer month);

    /**
     * 根据id删除主题图片
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * 更改对应月份图片
     * @param month
     * @param url1
     * @param url2
     * @param url3
     * @param url4
     * @return
     */
    boolean update(Integer month,String url1,String url2,String url3,String url4);

    /**
     * 根据id更改主题图片
     * @param id
     * @param url
     * @param css
     * @return
     */
    boolean updateById(Integer id,String url,String css);

    /**
     * 根据月份查询对应图片
     * @param month
     * @return
     */
    List<ThemePhoto> selectByMonth(Integer month);

    /**
     * 根据id查询对应图片
     * @param id
     * @return
     */
    ThemePhoto selectById(Integer id);

    /**
     * 查询所有主题图片
     * @return
     */
    List<ThemePhoto> selectAll();
}
