package cn.fves24.shts.redis;

import cn.fves24.shts.common.ComMsg;
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
    private String getCode(String email) {
        return template.opsForValue().get(email);
    }

    /**
     * 删除验证码
     * @param email 用户邮箱
     */
    private  void deleteCode(String email) {
        template.delete(email);
    }

    /**
     * 对比验证验证码
     *
     * @param email 邮箱
     * @param code  验证码
     * @return 验证码相等返回 ComMsg.SUCCESS，否则返回错误信息
     */
    public ComMsg checkCodeWithRedisCode(String email, String code) {
        String redisCode = this.getCode(email);
        // Redis数据库中没有数据，验证码失效，获取没有获取验证码
        if (redisCode == null) {
            return ComMsg.CODE_INVALID;
        }
        // 验证码错误
        if (!redisCode.equals(code)) {
            return ComMsg.CODE_ERROR;
        }
        // 验证成功，删除验证码
        this.deleteCode(email);
        return ComMsg.CODE_SUCCESS;
    }
}
