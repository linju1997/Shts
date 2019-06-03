package cn.fves24.shts.config;

import cn.fves24.shts.auth.AdminIntercept;
import cn.fves24.shts.auth.AuthenticationIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


/**
 * Web Mvc Config
 *
 * @author fves
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final AuthenticationIntercept authenticationIntercept;
    private final AdminIntercept adminIntercept;


    @Autowired
    public MvcConfig(AuthenticationIntercept authenticationIntercept, AdminIntercept adminIntercept) {
        this.authenticationIntercept = authenticationIntercept;
        this.adminIntercept = adminIntercept;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加认证认证拦截，默认拦截所有请求
        // 需要登录
        List<String> loginInterceptor = new ArrayList<>();
        loginInterceptor.add("/exit");

        loginInterceptor.add("/collection");
        loginInterceptor.add("/collection/cancel");
        loginInterceptor.add("/userinfo");
        loginInterceptor.add("/modify/**");
        loginInterceptor.add("/feedback/**");

        // 需要管理员
        List<String> adminInterceptor = new ArrayList<>();
        adminInterceptor.add("/user/list");
        adminInterceptor.add("/order/list");
        adminInterceptor.add("/collection/list");

        registry.addInterceptor(authenticationIntercept)
                .addPathPatterns(loginInterceptor);
        registry.addInterceptor(adminIntercept)
                .addPathPatterns(adminInterceptor);
    }

    /**
     * 跨域
     *
     * @param registry registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("http://fves24.cn:8080");
    }
}
