package cn.fves24.shts.mysql.service;

import cn.fves24.shts.common.ComMsg;

/**
 * @author fves
 */
public interface UserService {
    /**
     * 通过邮箱注册
     * @param username 用户名
     * @param email 用户邮箱
     * @return 注册成结果
     */
    ComMsg register(String username, String email);


    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    ComMsg login(String username, String password);

    /**
     * 用户名是否存在
     * @param username 用户名
     * @return 存在返回true，否则false
     */
    boolean existByUsername(String username);

    /**
     * 邮箱是否存在
     * @param email 邮箱
     * @return 存在返回true，否则false
     */
    boolean existByEmail(String email);
}
