package studio.banner.officialwebsite.handler;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import studio.banner.officialwebsite.entity.JwtAdmin;
import studio.banner.officialwebsite.entity.LoginAdmin;
import studio.banner.officialwebsite.util.JwtTokenUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Wyf
 * @Date: 2021/4/27 19:38
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ThreadLocal<Integer> rememberMe = new ThreadLocal<>();

    private final AuthenticationManager authenticationManager;

    private StringRedisTemplate stringRedisTemplate;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate) {
        this.authenticationManager = authenticationManager;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        // 前端传过来的是json数据
        /*try {
            LoginAdmin loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginAdmin.class);
            rememberMe.set(loginUser.getRememberMe() == null ? 0 : loginUser.getRememberMe());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
        // 前端传过来的是key-value数据
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        LoginAdmin loginUser = new LoginAdmin(username, password);
        rememberMe.set(loginUser.getRememberMe() == null ? 0 : loginUser.getRememberMe());
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getName(), loginUser.getPassword(), new ArrayList<>()));
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        JwtAdmin jwtUser = (JwtAdmin) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());
        boolean isRemember = rememberMe.get() == 1;

        String role = "";
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), role, isRemember);
        //String token = JwtTokenUtils.createToken(jwtUser.getUsername(), false);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的时候应该是 `Bearer token`
        logger.info("------------------->" + request.getParameter("name"));
        stringRedisTemplate.opsForValue().set(JwtTokenUtils.TOKEN_PREFIX + token, request.getParameter("name"), 60 * 10, TimeUnit.SECONDS);
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
