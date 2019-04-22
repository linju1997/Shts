package cn.fves24.shts.auth;

import cn.fves24.shts.common.ComMsg;
import cn.fves24.shts.common.Constants;
import cn.fves24.shts.exception.CommonException;
import cn.fves24.shts.model.User;
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
public class AdminIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws CommonException {
        log.debug("需要权限验证");
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.debug("权限验证失败: 用户未登录");
            throw new CommonException(ComMsg.UNLOGIN.getMsg());
        }
        if (Constants.LOGIN_YES.equals(session.getAttribute(Constants.LOGIN_KEY))) {
            User user = (User) session.getAttribute("user");
            if ("admin".equals(user.getUsername())) {
                log.debug("权限验证成功: 管理员用户");
                return true;
            }
            log.debug("权限验证失败: 非管理员用户");
            throw new CommonException("非管理员用户");
        }
        log.debug("权限验证失败: 用户未登录");
        throw new CommonException(ComMsg.UNLOGIN.getMsg());
    }
}
