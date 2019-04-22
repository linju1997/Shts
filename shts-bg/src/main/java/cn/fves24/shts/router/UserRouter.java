package cn.fves24.shts.router;

import cn.fves24.shts.common.*;
import cn.fves24.shts.exception.CommonException;
import cn.fves24.shts.model.User;
import cn.fves24.shts.mysql.mapper.UserMapper;
import cn.fves24.shts.mysql.service.UserServiceImpl;
import cn.fves24.shts.common.RandomCodeUtils;
import cn.fves24.shts.validation.Validation;
import cn.fves24.shts.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户相关API接口
 *
 * @author fves
 */
@Slf4j
@RestController

public class UserRouter {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final RandomCodeUtils randomCodeUtils;
    private final EmailTools emailTools;
    private final HttpServletRequest request;

    @Autowired
    public UserRouter(UserServiceImpl userService, UserMapper userMapper, RandomCodeUtils randomCodeUtils, EmailTools emailTools, HttpServletRequest request) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.randomCodeUtils = randomCodeUtils;
        this.emailTools = emailTools;
        this.request = request;
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
            return RespVO.getFail(validationResult.getErrMsg());
        }
        if (userService.existByUsername(username)) {
            return RespVO.getFail(ComMsg.USERNAME_EXIST);
        }
        return RespVO.getSuccess(ComMsg.USERNAME_AVAILABLE);
    }

    @RequestMapping("/check/email")
    public RespVO checkEmail(String email) {
        ValidationResult validationResult = Validation.validateEmail(email);
        if (validationResult.isHasErrors()) {
            return RespVO.getFail(validationResult.getErrMsg());
        }
        if (userService.existByEmail(email)) {
            return RespVO.getFail(ComMsg.EMAIL_EXIST);
        }
        return RespVO.getSuccess(ComMsg.EMAIL_AVAILABLE);
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
            return RespVO.getFail(validationResult.getErrMsg());
        }

        // 对比验证码
        ComMsg comMsg = randomCodeUtils.checkCode(user.getEmail(), code);
        if (comMsg != ComMsg.CODE_SUCCESS) {
            return RespVO.getFail(comMsg);
        }

        // 注册
        ComMsg register = userService.register(user.getUsername(), user.getEmail());
        if (register == ComMsg.REGISTER_SUCCESS) {
            return RespVO.getSuccess(register);
        }
        // 注册成功
        HttpSession session = request.getSession(true);
        session.setAttribute(Constants.LOGIN_KEY, Constants.LOGIN_YES);
        return RespVO.getFail(register);
    }

    /**
     * 登陆
     *
     * @param user 用户
     * @param code 验证码
     * @return 登陆结果
     */
    @RequestMapping("/login")
    public RespVO userLogin(User user, String code) {
        log.info("登录参数：" + user);
        log.info("验证码：" + code);
        // 邮箱验证码登录
        ValidationResult validationResult = Validation.validateLoginParams(user, code);
        if (validationResult.isHasErrors()) {
            log.debug("登录失败: 参数校验失败");
            return RespVO.getFail(validationResult.getErrMsg());
        }
        // 用户还未注册
        if (!userService.existByEmail(user.getEmail())) {
            log.debug("登录失败: 用户还未注册");
            return RespVO.getFail(ComMsg.UNREGISTERED);
        }
        // 校验验证码
        ComMsg comMsg = randomCodeUtils.checkCode(user.getEmail(), code);
        // 登陆失败
        if (comMsg != ComMsg.CODE_SUCCESS) {
            log.debug("登录失败: 验证码校验失败");
            return RespVO.getFail(comMsg);
        }
        log.debug("登录成功");
        // 登录成功，创建一个Session，并将用户信息放入session
        HttpSession session = request.getSession(true);
        user = userMapper.selectUserInfo(user.getEmail());
        session.setAttribute(Constants.LOGIN_KEY, Constants.LOGIN_YES);
        session.setAttribute("user", user);
        return RespVO.getSuccess(ComMsg.LOGIN_SUCCESS);
    }

    /**
     * 用户退出
     *
     * @return 退出结果
     */
    @PostMapping("/exit")
    public RespVO userExit() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(Constants.LOGIN_KEY, Constants.LOGIN_NO);
            return RespVO.getSuccess("退出成功");
        }
        return RespVO.getFail(ComMsg.getFail("退出失败"));
    }

    /**
     * 发送验证码
     *
     * @return 响应Body
     */
    @PostMapping("/code")
    public RespVO sendCode(String email) throws CommonException {
        ValidationResult validationResult = Validation.validateEmail(email);
        if (validationResult.isHasErrors()) {
            return RespVO.getFail(validationResult.getErrMsg());
        }
        // 获取验证码
        String code = CommonUtil.getRandomCode();
        // 发送验证码 TODO 暂时关闭QQ邮箱发送验证码
        /* CommonUtil.threadPolls.submit(() ->emailTools.sendEmailCode(email, code)); */
        // 将验证码放入到redis数据库中
        randomCodeUtils.saveCode(email, code);
        log.debug("验证码已发送:" + code);
        // TODO 测试
        return RespVO.getSuccess(ComMsg.getSuccess(code));
        // return RespVO.newRespVO(ComMsg.CODE_SEND_SUCCESS);
    }

    @PostMapping("/user/list")
    public RespVO getUsers() {
        List<User> users = userMapper.getUsers();
        return RespVO.getSuccess(users);
    }
}

