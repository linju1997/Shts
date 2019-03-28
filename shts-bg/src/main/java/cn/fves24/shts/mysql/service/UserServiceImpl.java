package cn.fves24.shts.mysql.service;

import cn.fves24.shts.common.ComMsg;
import cn.fves24.shts.mysql.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User service
 *
 * @author fves
 */
@Service
@Transactional(rollbackFor = {Exception.class}, isolation = Isolation.REPEATABLE_READ)
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ComMsg
    register(String username, String email) {
        if (existByUsername(username)) {
            return ComMsg.USERNAME_EXIST;
        }
        if (existByEmail(email)) {
            return ComMsg.EMAIL_EXIST;
        }
        if (userMapper.insertUser(username, email) != 1) {
            return ComMsg.REGISTER_FAIL;
        }
        return ComMsg.REGISTER_SUCCESS;
    }

    @Override
    public boolean existByUsername(String username) {
        return userMapper.existByUsername(username) != null;
    }

    @Override
    public boolean existByEmail(String email) {
        return userMapper.existByEmail(email) != null;
    }
}
