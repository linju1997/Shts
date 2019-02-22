package cn.fves24.shts.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Token Redis
 * 保存登录Token 到Redis
 * @author fves
 */
@Component
public class TokenRedis {
    private final RedisTemplate<String, String> template;

    @Autowired
    public TokenRedis(RedisTemplate<String, String> template) {
        this.template = template;
    }

    /**
     * 保存用户登陆的Token，并设置用户四小时过期
     *
     * @param email email
     * @param token token
     */
    public void addToken(String email, String token) {
        ValueOperations<String, String> operations = template.opsForValue();
        operations.set(email, token);
        template.expire(email, 4, TimeUnit.HOURS);
    }

    /**
     * 检测用户登陆态是否存在，存在则返回openId，不存在返回null
     *
     * @param email 用户邮箱
     * @return openId
     */
    public String getToken(String email) {
        String openId = template.opsForValue().get(email);
        if (openId == null) {
            return null;
        }
        return openId;
    }

    /**
     * 删除token,用户主动退出登录
     * @param email email
     */
    public void deleteToken(String email) {
        template.delete(email);
    }
}
