package cn.fves24.shts.redis;

import cn.fves24.shts.common.ComMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Random Code
 * 保存验证码，到 redis数据库
 *
 * @author fves
 */
@Component
public class CodeRedis {

    private final RedisTemplate<String, String> codeRedis;

    @Autowired
    public CodeRedis(RedisTemplate<String, String> codeRedis) {
        this.codeRedis = codeRedis;
    }

    /**
     * 保存验证码，并设置5分钟过期
     *
     * @param email 用户邮箱
     * @param code  验证码
     */
    public void saveCode(String email, String code) {
        codeRedis.opsForValue().set(email, code);
    }


    /**
     * 获取验证码
     *
     * @param email 用户邮箱
     * @return 验证码
     */
    private String getCode(String email) {
        return codeRedis.opsForValue().get(email);
    }

    /**
     * 删除验证码
     *
     * @param email 用户邮箱
     */
    private void deleteCode(String email) {
        codeRedis.delete(email);
    }

    /**
     * 对比验证验证码
     *
     * @param email 邮箱
     * @param code  验证码
     * @return 验证码相等返回 ComMsg.SUCCESS，否则返回错误信息
     */
    public ComMsg checkCode(String email, String code) {
        String realCode = this.getCode(email);
        // 没有获取验证码
        if (realCode == null) {
            return ComMsg.CODE_INVALID;
        }
        // 验证码错误
        if (!realCode.equals(code)) {
            return ComMsg.CODE_ERROR;
        }
        // 验证成功，删除验证码
        this.deleteCode(email);
        return ComMsg.CODE_SUCCESS;
    }
}
