package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.JournalismParagraph;
import studio.banner.officialwebsite.mapper.JournalismParagraphMapper;
import studio.banner.officialwebsite.service.IJournalismParagraphService;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/24 20:36
 */
@Service
public class JournalismParagraphServiceImpl implements IJournalismParagraphService {
    @Autowired
    protected JournalismParagraphMapper journalismParagraphMapper;
    @Override
    public boolean insert(JournalismParagraph journalismParagraph) {
        List<JournalismParagraph> list = new LambdaQueryChainWrapper<JournalismParagraph>(journalismParagraphMapper)
                .eq(JournalismParagraph::getContent,journalismParagraph.getContent()).list();
        if (list.size() == 0) {
            return journalismParagraphMapper.insert(journalismParagraph) != 0;
        } else {
            return false;
        }

    }

    @Override
    public boolean update(JournalismParagraph journalismParagraph) {
        return journalismParagraphMapper.updateById(journalismParagraph) != 0;
    }

    @Override
    public boolean delete(Integer id) {
        return journalismParagraphMapper.deleteById(id) != 0;
    }

    @Override
    public boolean deleteAll(Integer journalismId) {
        LambdaQueryWrapper<JournalismParagraph> queryWrapper = Wrappers.lambdaQuery();
        return journalismParagraphMapper.delete(queryWrapper.eq(JournalismParagraph::getJournalismId,journalismId)) != 0;
    }

    @Override
    public JournalismParagraph select(Integer id) {
        return journalismParagraphMapper.selectById(id);
    }

    @Override
    public List<JournalismParagraph> selectAll(Integer id) {
        List<JournalismParagraph> journalismParagraphs = new LambdaQueryChainWrapper<JournalismParagraph>(journalismParagraphMapper)
                .eq(JournalismParagraph::getJournalismId,id)
                .orderByAsc(JournalismParagraph::getJournalismId)
                .list();
        return journalismParagraphs;
    }
}
