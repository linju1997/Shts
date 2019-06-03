package cn.fves24.shts.auth;

import cn.fves24.shts.common.Constants;
import cn.fves24.shts.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 拦截器 ，用于拦截需要认证的请求
 * 认证通过 放行，
 * 认证失败 返回错误信息
 *
 * @author fves
 */
@Slf4j
@Component
public class AuthenticationIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws CommonException {
        log.info("需要权限验证");
        HttpSession session = request.getSession(false);
        if (session != null && Constants.LOGIN_YES.equals(session.getAttribute(Constants.LOGIN_KEY))) {
            log.info("权限验证成功: 用户已经登录");
            return true;
        }
        log.info("权限验证失败: 用户未登录");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "http://fves.cn:8080");
        return false;
    }
}
