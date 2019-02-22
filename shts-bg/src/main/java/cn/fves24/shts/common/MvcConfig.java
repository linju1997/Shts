package cn.fves24.shts.common;

import cn.fves24.shts.auth.TokenAuthIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Web Mvc Config
 *
 * @author fves
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final TokenAuthIntercept tokenAuthIntercept;

    @Autowired
    public MvcConfig(TokenAuthIntercept tokenAuthIntercept) {
        this.tokenAuthIntercept = tokenAuthIntercept;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加token认证拦截，默认拦截所有请求
        registry.addInterceptor(tokenAuthIntercept).addPathPatterns("/**");
    }
}
