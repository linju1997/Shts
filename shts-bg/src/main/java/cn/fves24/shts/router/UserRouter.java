package cn.fves24.shts.router;

import cn.fves24.shts.common.*;
import cn.fves24.shts.exception.CommonException;
import cn.fves24.shts.model.User;
import cn.fves24.shts.mysql.service.UserServiceImpl;
import cn.fves24.shts.redis.CodeRedis;
import cn.fves24.shts.token.TokenProvider;
import cn.fves24.shts.validation.Validation;
import cn.fves24.shts.validation.ValidationResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


/**
 * 用户相关API接口
 *
 * @author fves
 */
@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin
@Log4j2
public class UserRouter {

    private final UserServiceImpl userService;
    private final CodeRedis codeRedis;
    private final EmailTools emailTools;
    private final TokenProvider tokenProvider;

    @Autowired
    public UserRouter(UserServiceImpl userService, CodeRedis codeRedis, EmailTools emailTools, TokenProvider tokenProvider) {
        this.userService = userService;
        this.codeRedis = codeRedis;
        this.emailTools = emailTools;
        this.tokenProvider = tokenProvider;
    }

    /**
     * 检测用户是否存在
     *
     * @param username 用户名
     * @return 响应Body
     */
    @RequestMapping("/check/username")
    public RespVO checkUsername(String username) {
        ValidationResult validationResult = Validation.validateUsername(username);
        if (validationResult.isHasErrors()) {
            return RespVO.newFailRespVO(validationResult.getErrMsg());
        }
        if (userService.existByUsername(username)) {
            return RespVO.newFailRespVO(ComMsg.USERNAME_EXIST);
        }
        return RespVO.newSuccessRespVO(ComMsg.USERNAME_AVAILABLE);
    }

    @RequestMapping("/check/email")
    public RespVO checkEmail(String email) {
        ValidationResult validationResult = Validation.validateEmail(email);
        if (validationResult.isHasErrors()) {
            return RespVO.newFailRespVO(validationResult.getErrMsg());
        }
        if (userService.existByEmail(email)) {
            return RespVO.newFailRespVO(ComMsg.EMAIL_EXIST);
        }
        return RespVO.newSuccessRespVO(ComMsg.EMAIL_AVAILABLE);
    }

    /**
     * 用户注册
     *
     * @param user 用户
     * @param code 验证码
     * @return 响应Body
     */
    @PostMapping(value = "/register")
    public RespVO userRegister(User user, String code) {
        // 入参校验
        ValidationResult validationResult = Validation.validateRegisterParams(user, code);

        if (validationResult.isHasErrors()) {
            return RespVO.newFailRespVO(validationResult.getErrMsg());
        }

        // 对比验证码
        ComMsg comMsg = checkCodeWithRedisCode(user.getEmail(), code);
        if (comMsg != ComMsg.CODE_SUCCESS) {
            return RespVO.newFailRespVO(comMsg);
        }

        // 注册
        ComMsg register = userService.register(user.getUsername(), user.getEmail());
        if (register == ComMsg.REGISTER_SUCCESS) {
            return RespVO.newSuccessRespVO(register);
        }
        return RespVO.newFailRespVO(register);
    }

    /**
     * 登陆
     *
     * @param response response
     * @param user     用户
     * @param type     登录类型
     * @param code     验证码
     * @return 登陆结果
     */
    @PostMapping("/login")
    public RespVO userLogin(HttpServletResponse response, User user, Integer type, String code) {
        log.debug("登录方式：" + type);
        log.debug("登录参数：" + user);
        log.debug("验证码：" + code);
        // 未提交登录方式，默认采用账号密码登录
        if (type == null || ComMsg.LOGIN_TYPE_1.getCode() == type) {
            // 账号密码登录
            ValidationResult validationResult = Validation.validateLoginParams(user);
            if (validationResult.isHasErrors()) {
                return RespVO.newFailRespVO(validationResult.getErrMsg());
            }
            // TODO  加密密码
            ComMsg comMsg = userService.login(user.getUsername(), user.getPassword());
            // 登录 失败
            if (comMsg != ComMsg.LOGIN_SUCCESS) {
                return RespVO.newFailRespVO(comMsg);
            }
        } else if (ComMsg.LOGIN_TYPE_2.getCode() == type) {
            // 邮箱验证码登录
            ValidationResult validationResult = Validation.validateLoginParams(user, code);
            if (validationResult.isHasErrors()) {
                return RespVO.newFailRespVO(validationResult.getErrMsg());
            }
            // 校验验证码
            ComMsg comMsg = checkCodeWithRedisCode(user.getEmail(), code);
            // 登陆失败
            if (comMsg != ComMsg.CODE_SUCCESS) {
                return RespVO.newFailRespVO(comMsg);
            }
        } else {
            return RespVO.newFailRespVO(ComMsg.LOGIN_TYPE_INVALID);
        }
        // 登录成功，生成Token Cookie
        String token = tokenProvider.makeToken(user.getEmail());
        // TODO token
        Cookie tokenCookie = new Cookie("token", token);
        response.addCookie(tokenCookie);
        return RespVO.newSuccessRespVO(ComMsg.LOGIN_SUCCESS);
    }

    /**
     * 发送验证码
     *
     * @return 响应Body
     */
    @PostMapping("/code")
    public RespVO sendRegisterCode(String email) throws CommonException {
        ValidationResult validationResult = Validation.validateEmail(email);
        if (validationResult.isHasErrors()) {
            return RespVO.newFailRespVO(validationResult.getErrMsg());
        }
        // 获取验证码
        String code = CommonUtil.getRandomCode();
        // 发送验证码 TODO 暂时关闭QQ邮箱发送验证码
        /* CommonUtil.threadPolls.submit(() ->emailTools.sendEmailCode(email, code)); */
        // 将验证码放入到redis数据库中
        codeRedis.saveCode(email, code);
        log.debug("验证码已发送:" + code);
        return RespVO.newSuccessRespVO(ComMsg.CODE_SEND_SUCCESS);
    }

    /**
     * 对比验证验证码
     *
     * @param email 邮箱
     * @param code  验证码
     * @return 验证码相等返回 ComMsg.SUCCESS，否则返回错误信息
     */
    private ComMsg checkCodeWithRedisCode(String email, String code) {
        String redisCode = codeRedis.getCode(email);
        // Redis数据库中没有数据，验证码失效，获取没有获取验证码
        if (redisCode == null) {
            return ComMsg.CODE_INVALID;
        }
        // 验证码错误
        if (!redisCode.equals(code)) {
            return ComMsg.CODE_ERROR;
        }
        // 验证成功，删除验证码
        codeRedis.deleteCode(email);
        return ComMsg.CODE_SUCCESS;
    }
}
