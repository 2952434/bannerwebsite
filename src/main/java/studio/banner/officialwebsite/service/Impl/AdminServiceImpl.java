package studio.banner.officialwebsite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import studio.banner.officialwebsite.entity.Admin;
import studio.banner.officialwebsite.entity.JwtAdmin;
import studio.banner.officialwebsite.mapper.AdministratorMapper;
import studio.banner.officialwebsite.service.IAdminService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hyy
 * @date 2021/5/23 9:35
 * @role
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdministratorMapper,Admin> implements IAdminService, UserDetailsService {
    protected static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Resource
    private AdministratorMapper administratorMapper;

//    @Override
//    public boolean check(String name, String password) {
//        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
//        queryWrapper = queryWrapper.eq("name",name)
//                .eq("password", password);
//        List<Admin> list = administratorMapper.selectList(queryWrapper);
//        if (list.size() != 0) {
//            return true ;
//        }
//        return false;
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<Admin> wrapper=new QueryWrapper<>();
        wrapper.eq("name",s);
        Admin user = administratorMapper.selectOne(wrapper);
        if(user==null){
            System.out.println("----------用户名报错--------------");
            throw new UsernameNotFoundException("用户名错误！！");
        }
        return new JwtAdmin(user);
    }
}