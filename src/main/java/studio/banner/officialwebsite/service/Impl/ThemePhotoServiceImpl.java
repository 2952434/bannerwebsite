package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.ThemePhoto;
import studio.banner.officialwebsite.mapper.ThemePhotoMapper;
import studio.banner.officialwebsite.service.IThemePhotoService;

import java.util.List;

/**
 * @Author: Re
 * @Date: 2021/6/8 17:50
 */
@Service
public class ThemePhotoServiceImpl implements IThemePhotoService {
    @Autowired
    protected ThemePhotoMapper themePhotoMapper;
    @Override
    public boolean insert(ThemePhoto themePhoto) {
        List<ThemePhoto> themePhotoList = new LambdaQueryChainWrapper<ThemePhoto>(themePhotoMapper)
                .eq(ThemePhoto::getPhotoMonth,themePhoto.getPhotoMonth())
                .eq(ThemePhoto::getUrl,themePhoto.getUrl())
                .list();
        if (themePhotoList.size() == 0) {
            return themePhotoMapper.insert(themePhoto) == 1;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer month) {
        LambdaQueryWrapper<ThemePhoto> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ThemePhoto::getPhotoMonth,month);
        return themePhotoMapper.delete(queryWrapper) >= 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return themePhotoMapper.deleteById(id) == 1;
    }

    @Override
    public boolean update(Integer month, String url1, String url2, String url3, String url4) {
        List<ThemePhoto> themePhotoList = new LambdaQueryChainWrapper<ThemePhoto>(themePhotoMapper)
                .eq(ThemePhoto::getPhotoMonth,month)
                .orderByAsc(ThemePhoto::getPhotoSite)
                .list();
        if (themePhotoList.size() != 4) {
            return false;
        } else {
            if (url1 != null) {
                ThemePhoto themePhoto1 = themePhotoList.get(0);
                themePhoto1.setUrl(url1);
                themePhotoMapper.updateById(themePhoto1);
            }
            if (url2 != null) {
                ThemePhoto themePhoto2 = themePhotoList.get(1);
                themePhoto2.setUrl(url2);
                themePhotoMapper.updateById(themePhoto2);
            }
            if (url3 != null) {
                ThemePhoto themePhoto3 = themePhotoList.get(2);
                themePhoto3.setUrl(url3);
                themePhotoMapper.updateById(themePhoto3);
            }
            if (url4 != null) {
                ThemePhoto themePhoto4 = themePhotoList.get(3);
                themePhoto4.setUrl(url4);
                themePhotoMapper.updateById(themePhoto4);
            }
        }
        return true;
    }

    @Override
    public boolean updateById(Integer id, String url, String css) {
        ThemePhoto themePhoto = new ThemePhoto(id);
        ThemePhoto select = themePhotoMapper.selectById(id);
        if (url != null) {
            themePhoto.setUrl(url);
        } else {
            themePhoto.setUrl(select.getUrl());
        }
        if (css != null) {
            themePhoto.setCss(css);
        } else {
            themePhoto.setCss(select.getCss());
        }
        themePhoto.setPhotoMonth(select.getPhotoMonth());
        themePhoto.setPhotoSite(select.getPhotoSite());
        return themePhotoMapper.updateById(themePhoto) == 1;
    }

    @Override
    public List<ThemePhoto> selectByMonth(Integer month) {
        return new LambdaQueryChainWrapper<ThemePhoto>(themePhotoMapper)
                .eq(ThemePhoto::getPhotoMonth,month)
                .orderByAsc(ThemePhoto::getPhotoSite)
                .list();
    }

    @Override
    public ThemePhoto selectById(Integer id) {
        return themePhotoMapper.selectById(id);
    }

    @Override
    public List<ThemePhoto> selectAll() {
        return themePhotoMapper.selectList(null);
    }
}
