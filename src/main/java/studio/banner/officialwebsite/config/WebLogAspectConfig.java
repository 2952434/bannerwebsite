package studio.banner.officialwebsite.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author: Re
 * @Date: 2021/4/24 9:07
 * @role: 切面配置类
 */
@Aspect
@Component
public class WebLogAspectConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspectConfig.class);
    @Pointcut("execution(public * studio.banner.officialwebsite.controller..*.*(..))")
    public void webLog() {

    }
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("---------------request----------------");
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("---------------请求头信息----------------");
        Enumeration<String> enumerationHeader=request.getHeaderNames();
        while (enumerationHeader.hasMoreElements()){
            String headerName=enumerationHeader.nextElement();
            logger.info(headerName+":"+request.getHeader(headerName));
        }
        logger.info("---------------请求头信息结束----------------");
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            logger.info("name:" + name + " - value:" + request.getParameter(name));
        }
    }
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("---------------response----------------");
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }
}
