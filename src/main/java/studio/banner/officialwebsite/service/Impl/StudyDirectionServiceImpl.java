package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.StudyDirection;
import studio.banner.officialwebsite.mapper.StudyDirectionMapper;
import studio.banner.officialwebsite.service.IStudyDirectionService;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/5/27 22:21
 */
@Service
public class StudyDirectionServiceImpl implements IStudyDirectionService {
    @Autowired
    protected StudyDirectionMapper studyDirectionMapper;
    @Override
    public boolean insert(StudyDirection studyDirection) {
        List<StudyDirection> studyDirectionList = new LambdaQueryChainWrapper<StudyDirection>(studyDirectionMapper)
                .eq(StudyDirection::getParagraph,studyDirection.getDirection())
                .eq(StudyDirection::getDirection,studyDirection.getDirection()).list();
        if (studyDirectionList.size() == 0) {
            return studyDirectionMapper.insert(studyDirection) != 0;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return studyDirectionMapper.deleteById(id) == 1;
    }

    @Override
    public boolean update(StudyDirection studyDirection) {
        return studyDirectionMapper.updateById(studyDirection) != 0;
    }

    @Override
    public StudyDirection select(Integer id) {
        return studyDirectionMapper.selectById(id);
    }

    @Override
    public List<StudyDirection> selectDirectionAll(String direction) {
        List<StudyDirection> studyDirectionList = new LambdaQueryChainWrapper<StudyDirection>(studyDirectionMapper)
                .eq(StudyDirection::getDirection,direction).list();
        return studyDirectionList;
    }

    @Override
    public List<StudyDirection> selectAll() {
        return studyDirectionMapper.selectList(null);
    }

    @Override
    public IPage<StudyDirection> selectByPage(Integer pageNumber, Integer pageSize) {
        Page<StudyDirection> page = new Page<>(pageNumber,pageSize);
        return studyDirectionMapper.selectPage(page,null);
    }
}
