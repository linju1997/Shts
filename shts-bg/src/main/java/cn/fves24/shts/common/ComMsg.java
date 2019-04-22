package cn.fves24.shts.common;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * ComMsg
 *
 * @author fves
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ComMsg {
    // 成功
    SUCCESS("成功"),
    // 失败
    FAIL("失败"),

    // 验证码
    CODE_SEND_SUCCESS("验证码发送成功"),
    CODE_EMPTY("验证码不能为空"),
    CODE_SEND_FAIL("验证码发送失败"),
    CODE_ERROR("验证码错误"),
    CODE_INVALID("验证码失效"),
    CODE_SUCCESS("验证码正确"),


    // 用户名
    USERNAME_AVAILABLE("用户名可以使用"),
    USERNAME_EMPTY("用户名不能为空"),
    USERNAME_TOO_SHORT("用户名太短(推荐4~20个字符)"),
    USERNAME_TOO_LONG("用户名太长(推荐4~20个字符)"),
    USERNAME_EXIST("用户名已存在"),

    // 用户注册
    REGISTER_SUCCESS("注册成功"),
    REGISTERED("用户已经注册"),
    UNREGISTERED("用户未注册,请先注册"),
    REGISTER_FAIL("注册失败,请重新注册"),


    // 用户登陆
    LOGIN_SUCCESS("登陆成功"),
    LOGIN_FAIL("登陆失败,用户名或密码错误"),
    UNLOGIN("用户未登录"),

    // 邮箱
    EMAIL_AVAILABLE("邮箱可以使用"),
    EMAIL_EMPTY("邮箱地址不能为空"),
    EMAIL_INVALID("无效的邮箱地址(推荐QQ邮箱)"),
    EMAIL_EXIST("邮箱已存在"),

    // 手机
    PHONE_EMPTY("手机不能为空"),
    PHONE_INVALID("手机号码无效"),

    // 反馈
    FEEDBACK_SUCCESS("反馈成功"),
    FEEDBACK_FAIL("反馈失败，请重试"),
    FEEDBACK_PROCESSED("反馈已处理"),

    // 收藏
    COLLECTION_SUCCESS("收藏成功"),
    COLLECTION_FAIL("收藏失败"),
    COLLECTION_CANCEL_SUCCESS("取消收藏成功"),
    COLLECTION_CANCEL_FAIL("取消收藏失败"),

    // 参数错误
    PARAMETER_ERROR("参数错误"),

    // 系统异常
    SYSTEM_EXCEPTION("系统异常");



    private String msg;

    ComMsg(String msg) {
        this.msg = msg;
    }


    public static ComMsg getFail(String msg) {
        return ComMsg.customComMsg(ComMsg.FAIL, msg);
    }

    public static ComMsg getSuccess(String msg) {
        return ComMsg.customComMsg(ComMsg.SUCCESS, msg);
    }

    private static ComMsg customComMsg(ComMsg comMsg, String msg) {
        comMsg.setMsg(msg);
        return comMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
