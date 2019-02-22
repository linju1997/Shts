package cn.fves24.shts.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Code Redis
 * 保存验证码，到redis数据库
 * @author fves
 */
@Component
public class CodeRedis {
    private final StringRedisTemplate template;

    @Autowired
    public CodeRedis(StringRedisTemplate template) {
        this.template = template;
    }

    /**
     * 保存验证码，并设置5分钟过期
     *
     * @param email 用户邮箱
     * @param code  验证码
     */
    public void saveCode(String email, String code) {
        int expireTime = 5;
        template.opsForValue().set(email, code, expireTime, TimeUnit.MINUTES);
    }


    /**
     * 获取验证码
     *
     * @param email 用户邮箱
     * @return 验证码
     */
    public String getCode(String email) {
        return template.opsForValue().get(email);
    }

    /**
     * 删除验证码
     * @param email 用户邮箱
     */
    public  void deleteCode(String email) {
        template.delete(email);
    }
}
