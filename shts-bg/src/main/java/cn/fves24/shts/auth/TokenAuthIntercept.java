package cn.fves24.shts.auth;

import cn.fves24.shts.redis.TokenRedis;
import cn.fves24.shts.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器 ，用于拦截需要认证的请求
 * 认证通过 放行，
 * 认证失败 返回错误信息
 *
 * @author fves
 */
@Component
public class TokenAuthIntercept implements HandlerInterceptor {

    private TokenProvider tokenProvider = new TokenProvider();
    private final TokenRedis tokenRedis;

    @Autowired
    public TokenAuthIntercept(TokenRedis tokenRedis) {
        this.tokenRedis = tokenRedis;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;
        /*
          如果 请求方法 使用AuthHeader 则该请求 需要认证
          否则，直接放行
         */
        if (!method.hasMethodAnnotation(TokenAuth.class)) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String tokenCookieName = "token";
                if (tokenCookieName.equals(cookie.getName())) {
                    // 获取到Cookie值 TODO
                    String token = cookie.getValue();
                    return true;
                }
            }
        }
        // 没有认证成功，直接返回到登陆页面
        response.setHeader(HttpHeaders.LOCATION, "/login");
        return false;
    }
}
