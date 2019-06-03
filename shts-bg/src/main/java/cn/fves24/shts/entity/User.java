package cn.fves24.shts.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
     * 锁定状态
     */
    private static final int LOCKED = 0;
    /**
     * 未激活状态
     */
    private static final int INACTIVE = 1;
    /**
     * 激活状态
     */
    private static final int ACTIVE = 2;

    /**
     * User id
     */
    private int id;

    /**
     * 用户名
     */
    private String username;

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
     * 状态,0锁定状态,1,未激活状态,2,激活状态，默认未激活状态
     */
    private int status;
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modify;
}
