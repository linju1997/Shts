package cn.fves24.shts.mysql.service;

import cn.fves24.shts.common.ComMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    @Transactional   // 默认回滚
    public void register() {
        // 注册成功
        assert userService.register("里斯", "2218890669@qq.com") == ComMsg.REGISTER_SUCCESS;
        // 用户已存在，注册失败
        assert userService.register("里斯", "232@qq.com") == ComMsg.USERNAME_EXIST;
        // 邮箱已存在，注册失败
        assert userService.register("德斯", "2218890669@qq.com") == ComMsg.EMAIL_EXIST;
        // 注册成功
        assert userService.register("麦克", "2658@qq.com") == ComMsg.REGISTER_SUCCESS;
    }
}