package cn.fves24.shts.mysql.mapper;

import cn.fves24.shts.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void userInfoUpdate(){
        User user = new User();
        user.setEmail("2218890669@qq.com");
        user.setUsername("fves");

        userMapper.updateUserInfo(user);
    }

}