package studio.banner.officialwebsite.controller.background;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.banner.officialwebsite.entity.Admin;
import studio.banner.officialwebsite.entity.RespBean;
import studio.banner.officialwebsite.mapper.AdministratorMapper;
import studio.banner.officialwebsite.service.IAdminService;
import studio.banner.officialwebsite.service.Impl.ManagementSystemServiceImpl;

import javax.annotation.Resource;

/**
 * @author hyy
 * @date 2021/5/23 9:42
 * @role
 */
@Api(tags = "管理员接口", value = "AdminController")
@RestController
public class AdminController  {
    protected static final Logger logger = LoggerFactory.getLogger(ManagementSystemServiceImpl.class);
    @Resource
    private IAdminService iAdminService;

//    @PostMapping("/admin")
//    @ApiOperation("管理员登录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name", value = "用户名",dataTypeClass = String.class),
//            @ApiImplicitParam(name = "password", value = "密码",dataTypeClass = String.class)
//    })
//    public RespBean login(@RequestParam String name, @RequestParam String password) {
//        if (name == null || password == null) {
//            logger.error("用户名或者密码为空");
//            return RespBean.error("用户名或者密码为空");
//        }
//        if (iAdminService.check(name, password)) {
//            logger.info("登录成功");
//            return RespBean.ok("登录成功",true);
//        } else {
//            logger.error("用户名或密码不存在，登录失败");
//            return RespBean.error("用户名或密码不存在，登录失败",false);
//        }
//    }

    @Autowired
    private AdministratorMapper administratorMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public RespBean registerUser(String username, String password){
        Admin user = new Admin();
        user.setName(username);
        //对密码进行编码
        user.setPassword(bCryptPasswordEncoder.encode(password));
        //不对密码进行编码，存储明文
        //user.setPassword(password);
        user.setRole("ROLE_ADMIN");
        int insert = administratorMapper.insert(user);
        if(insert>0){
            return RespBean.ok(user);
        }else{
            return RespBean.error("注册失败");
        }
    }

    @PostMapping("/welcome")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RespBean welcome(){
        return RespBean.ok("欢迎~~~");
    }

    @PostMapping("/index")

    public String index(){
        return "index";
    }

}
