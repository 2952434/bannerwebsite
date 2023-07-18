package studio.banner.officialwebsite.service;

import studio.banner.officialwebsite.entity.JournalismParagraph;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/24 17:45
 */
public interface IJournalismParagraphService {
    /**
     * 增加新闻段落
     * @param journalismParagraph
     * @return
     */
    boolean insert(JournalismParagraph journalismParagraph);

    /**
     * 更改新闻段落
     * @param journalismParagraph
     * @return
     */
    boolean update(JournalismParagraph journalismParagraph);

    /**
     * 删除新闻段落
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据新闻id删除新闻段落
     * @param journalismId
     * @return
     */
    boolean deleteAll(Integer journalismId);

    /**
     * 根据id查询新闻段落
     * @param id
     * @return
     */
    JournalismParagraph select(Integer id);

    /**
     * 根据新闻id查询新闻所有段落
     * @param id
     * @return
     */
    List<JournalismParagraph> selectAll(Integer id);


}
