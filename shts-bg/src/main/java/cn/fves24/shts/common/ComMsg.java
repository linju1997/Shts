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
    SUCCESS(200, "成功"),
    // 失败
    FAIL(201, "失败"),

    // 验证码
    CODE_SEND_SUCCESS(200, "验证码发送成功"),
    CODE_EMPTY(10001, "验证码不能为空"),
    CODE_SEND_FAIL(10003, "验证码发送失败"),
    CODE_ERROR(10004, "验证码错误"),
    CODE_INVALID(10005, "验证码失效"),
    CODE_SUCCESS(10006, "验证码正确"),


    // 用户名
    USERNAME_AVAILABLE(200, "用户名可以使用"),
    USERNAME_EMPTY(20001, "用户名不能为空"),
    USERNAME_TOO_SHORT(20002, "用户名太短(推荐4~20个字符)"),
    USERNAME_TOO_LONG(20003, "用户名太长(推荐4~20个字符)"),
    USERNAME_EXIST(20004, "用户名已存在"),

    // 用户注册
    REGISTER_SUCCESS(200, "注册成功"),
    REGISTERED(30001, "用户已经注册"),
    UNREGISTERED(30002, "用户未注册,请先注册"),
    REGISTER_FAIL(30003, "注册失败,请重新注册"),


    // 用户登陆
    LOGIN_SUCCESS(200, "登陆成功"),
    LOGIN_FAIL(40001, "登陆失败,用户名或密码错误"),
    UNLOGIN(40002, "用户未登录"),

    // 邮箱
    EMAIL_AVAILABLE(200, "邮箱可以使用"),
    EMAIL_EMPTY(50001, "邮箱地址不能为空"),
    EMAIL_INVALID(50002, "无效的邮箱地址(推荐QQ邮箱)"),
    EMAIL_EXIST(50003, "邮箱已存在"),

    // 手机
    PHONE_EMPTY(60001, "手机不能为空"),
    PHONE_INVALID(60002, "手机号码无效"),

    // 反馈
    FEEDBACK_SUCCESS(200, "反馈成功"),
    FEEDBACK_FAIL(70001, "反馈失败，请重试"),
    FEEDBACK_PROCESSED(70002, "反馈已处理"),

    // 系统异常
    SYSTEM_EXCEPTION(505, "系统异常");


    private int code;

    private String msg;

    ComMsg(int code, String msg) {
        this.code = code;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
