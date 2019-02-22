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

    // 登录方式
    LOGIN_TYPE_1(2222, "账号密码登录"),
    LOGIN_TYPE_2(2223, "邮箱验证码登录"),
    LOGIN_TYPE_INVALID(33333, "登录方式非法"),

    // 验证码
    CODE_EMPTY(202, "验证码不能为空"),
    CODE_SEND_SUCCESS(200, "验证码发送成功"),
    CODE_SEND_FAIL(201, "验证码发送失败"),
    CODE_ERROR(20001, "验证码错误"),
    CODE_INVALID(20002, "验证码失效"),

    // 用户名
    USERNAME_AVAILABLE(200, "用户名可以使用"),
    USERNAME_EMPTY(40003, "用户名不能为空"),
    USERNAME_TOO_SHORT(40003, "用户名太短(推荐4~20个字符)"),
    USERNAME_TOO_LONG(40003, "用户名太长(推荐4~20个字符)"),
    USERNAME_EXIST(40001, "用户名已存在"),

    // 密码
    PASSWORD_UNSET(2933, "密码未设置"),

    // 用户注册
    REGISTER_SUCCESS(200, "注册成功"),
    REGISTERED(40002, "用户已经注册"),
    UNREGISTERED(4202, "用户未注册"),
    REGISTER_FAIL(40004, "注册失败,请重新注册"),


    // 用户登陆
    LOGIN_SUCCESS(200, "登陆成功"),
    LOGIN_FAIL(202, "登陆失败,用户名或密码错误"),

    // 邮箱
    EMAIL_AVAILABLE(200, "邮箱可以使用"),
    EMAIL_EMPTY(3000, "邮箱地址不能为空"),
    EMAIL_INVALID(30001, "无效的邮箱地址(推荐QQ邮箱)"),
    EMAIL_EXIST(30002, "邮箱已存在"),

    // 手机
    PHONE_EMPTY(4000, "手机不能为空"),
    PHONE_INVALID(4001, "手机号码无效"),

    // 系统异常
    SYSTEM_EXCEPTION(505, "系统异常");


    private int code;
    private String msg;

    ComMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ComMsg getSuccess(String msg) {
        return ComMsg.customComMsg(ComMsg.SUCCESS, msg);
    }

    public static ComMsg getFail(String msg) {
        return ComMsg.customComMsg(ComMsg.FAIL, msg);
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
    }


}
