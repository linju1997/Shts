package cn.fves24.shts.mysql.mapper;

import cn.fves24.shts.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserMapper
 *
 * @author fves
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 查询用户信息
     * @param email 用户邮箱
     * @return 用户信息
     */
    @Select("select `id`,`username`,`email`,`phone`,`address`,`remark`,`create_time`,`modify` " +
            "from user " +
            "where `email` = #{email} ")
    User selectUserByEmail(String email);

    /**
     * 查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @Select("select `id`,`username`,`email`,`phone`,`address`,`remark`,`create_time`,`modify` " +
            "from user " +
            "where `id` = #{id} ")
    User selectUserById(int id);

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


    /**
     * 修改用户地址
     * @param email 邮箱
     * @param address 地址
     * @return 修改成功返回1，失败返回null
     */
    @Update("update user set `address`=#{address},`modify`=current_timestamp where `email`=#{email} ")
    Integer modifyAddress(@Param("email") String email, @Param("address") String address);


    @Select("select id, username, email, phone, address, remark, create_time, modify from user")
    List<User> getUsers();
}
