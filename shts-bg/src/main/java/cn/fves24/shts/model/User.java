package cn.fves24.shts.model;

import lombok.Data;

import java.util.Date;

/**
 * User Model
 *
 * @author user
 */
@Data
public class User {

    /**
     * User id
     */
    private int id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户手机
     */
    private String phone;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date modify;
}
