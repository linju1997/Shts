package cn.fves24.shts.mysql.mapper;

import cn.fves24.shts.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * UserMapper
 *
 * @author fves
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 用户注册，用户只能通过邮箱注册
     *
     * @param username 用户名
     * @param email    用户邮箱
     * @return 保存结果
     */
    @Insert("insert ignore into user(`username`,`email`) values (#{username},#{email})")
    Integer insertUser(@Param("username") String username, @Param("email") String email);

    /**
     * 通过用户名查询用户密码
     *
     * @param username 用户名
     * @return 查询成功返回密码，失败返回null
     */
    @Select("select `password` from user where `username`=#{username}")
    String selectPasswordByUsername(String username);

    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return 更新结果
     */
    Integer updateUserInfo(User user);

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @return 不存在返回null，存在返回id
     */
    @Select("select `id` from user where `username`=#{username}")
    Integer existByUsername(String username);

    /**
     * 查询邮箱是否存在
     *
     * @param email 邮箱地址
     * @return 不存在返回null，存在返回id
     */
    @Select("select id from user where `email` = #{email}")
    Integer existByEmail(String email);

    /**
     * 修改用户名
     *
     * @param email    用户邮箱
     * @param username 新的用户名
     * @return 修改成功返回1，失败返回null
     */
    @Update("update user set `username`=#{username},`modify`=current_timestamp where `email`=#{email}")
    Integer modifyUsername(@Param("email") String email, @Param("username") String username);

    /**
     * 修改邮箱
     *
     * @param email    用户邮箱
     * @param newEmail 用户新邮箱
     * @return 修改成功返回1，失败返回null
     */
    @Update("update user set `email`=#{newEmail},`modify`=current_timestamp where `email` = #{email}")
    Integer modifyEmail(@Param("email") String email, @Param("newEmail") String newEmail);
}
