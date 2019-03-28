package cn.fves24.shts.router;

import cn.fves24.shts.auth.Authentication;
import cn.fves24.shts.common.ComMsg;
import cn.fves24.shts.common.RespVO;
import cn.fves24.shts.model.User;
import cn.fves24.shts.mysql.mapper.UserMapper;
import cn.fves24.shts.redis.CodeRedis;
import cn.fves24.shts.validation.Validation;
import cn.fves24.shts.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.smartcardio.CommandAPDU;

/**
 * 用户修改信息
 *
 * @author fves
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserInfoRouter {
    private CodeRedis codeRedis;
    private UserMapper userMapper;
    private HttpSession session;

    @Autowired
    public UserInfoRouter(CodeRedis codeRedis, UserMapper userMapper, HttpSession session) {
        this.codeRedis = codeRedis;
        this.userMapper = userMapper;
        this.session = session;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @PostMapping("/userinfo")
    @Authentication
    public RespVO userInfo(@SessionAttribute("email") String email) {
        log.info("查询用户信息,用户邮箱:" + email);
        ValidationResult validationResult = Validation.validateEmail(email);
        if (validationResult.isHasErrors()) {
            log.info("查询失败，邮箱校验失败");
            return RespVO.getFail(validationResult.getErrMsg());
        }
        User user = userMapper.selectUserInfo(email);
        if (user == null) {
            log.info("查询失败,用户不存在");
            return RespVO.getFail(ComMsg.getFail("未查询到该用户"));
        }
        log.info("查询成功:" + user);
        return RespVO.getSuccess(user);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return 修改结果
     */
    @PostMapping("/userinfo/update")
    @Authentication
    public RespVO updateUserInfo(User user, @SessionAttribute("email") String email) {
        user.setEmail(email);
        Integer updateUserInfo = userMapper.updateUserInfo(user);
        if (updateUserInfo != null && updateUserInfo == 1) {
            return RespVO.getSuccess(ComMsg.getSuccess("数据更新成功"));
        }
        return RespVO.getFail(ComMsg.getFail("数据更新失败"));
    }

    /**
     * 修改用户名
     *
     * @param username 新用户名
     * @return 修改结果
     */
    @PostMapping("/modify/username")
    @Authentication
    public RespVO modifyUsername(String username, @SessionAttribute("email") String email) {
        log.info("修改用户名，新用户名:" + username + ",用户邮箱:" + email);
        ValidationResult validationResult = Validation.validateUsername(username);
        /* 校验参数 */
        if (validationResult.isHasErrors()) {
            log.info("参数校验失败，修改用户名失败");
            return RespVO.getFail(validationResult.getErrMsg());
        }
        Integer integer = userMapper.modifyUsername(email, username);
        if (integer != null && integer == 1) {
            log.info("修改用户名成功");
            /* 修改session中的用户名 */
            session.setAttribute("username", username);
            return RespVO.getSuccess(ComMsg.getSuccess("修改成功"));
        }
        log.info("修改用户名失败");
        return RespVO.getFail(ComMsg.getFail("修改失败"));
    }

    /**
     * 用户修改邮箱
     *
     * @param email 用户新邮箱
     * @param code  验证码
     * @return 修改结果
     */
    @PostMapping("/modify/email")
    @Authentication
    public RespVO modifyEmail(String newEmail, String code, @SessionAttribute("email") String email) {
        log.info("修改邮箱，新邮箱:" + newEmail + ",原邮箱:" + email + ",验证码:" + code);
        ValidationResult validationResult = Validation.validateModifyEmailParams(newEmail, code);
        /* 校验参数 */
        if (validationResult.isHasErrors()) {
            log.info("参数校验失败，修改邮箱失败");
            return RespVO.getFail(validationResult.getErrMsg());
        }
        /* 校验验证码是否正确 */
        ComMsg checkCodeResult = codeRedis.checkCodeWithRedisCode(newEmail, code);
        if (checkCodeResult != ComMsg.CODE_SUCCESS) {
            log.info("验证码错误，邮箱修改失败");
            return RespVO.getFail(checkCodeResult);
        }
        log.debug("验证码正确");
        Integer integer = userMapper.modifyEmail(email, newEmail);
        if (integer != null && integer == 1) {
            log.info("邮箱修改成功");
            /* 修改session中的email为新的邮箱 */
            session.setAttribute("email", newEmail);
            return RespVO.getSuccess(ComMsg.getSuccess("邮箱修改成功"));
        }
        log.info("邮箱修改失败");
        return RespVO.getFail(ComMsg.getFail("邮箱修改失败"));
    }

    /**
     * 修改地址
     *
     * @param address 地址
     * @param email   用户邮箱，在session中
     * @return 返回结果
     */
    @PostMapping("/modify/address")
    @Authentication
    public RespVO modifyAddress(String address, @SessionAttribute("email") String email) {
        log.info("修改地址：" + address);
        ValidationResult validationResult = Validation.validateAddress(address);
        if (validationResult.isHasErrors()) {
            return RespVO.getFail(validationResult.getErrMsg());
        }
        Integer modifyEmail = userMapper.modifyAddress(email, address);
        if (modifyEmail != null && modifyEmail == 1) {
            return RespVO.getSuccess(ComMsg.getSuccess("地址修改成功"));
        }
        return RespVO.getFail(ComMsg.getFail("地址修改失败"));
    }
}
