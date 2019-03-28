package cn.fves24.shts.common;

import cn.fves24.shts.auth.AuthenticationIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Web Mvc Config
 *
 * @author fves
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final AuthenticationIntercept authenticationIntercept;

    @Autowired
    public MvcConfig(AuthenticationIntercept authenticationIntercept) {
        this.authenticationIntercept = authenticationIntercept;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加认证认证拦截，默认拦截所有请求
        registry.addInterceptor(authenticationIntercept).addPathPatterns("/**");
    }

    /**
     * 跨域
     *
     * @param registry registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
}
