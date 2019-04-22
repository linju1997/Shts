package cn.fves24.shts.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Random Code
 * 保存验证码，到redis数据库
 * @author fves
 */
@Component
public class RandomCodeUtils {
    private final Map<String, String> hashMap = new HashMap<>();

    /**
     * 保存验证码，并设置5分钟过期
     *
     * @param email 用户邮箱
     * @param code  验证码
     */
    public void saveCode(String email, String code) {
        hashMap.put(email, code);
    }


    /**
     * 获取验证码
     *
     * @param email 用户邮箱
     * @return 验证码
     */
    private String getCode(String email) {
        return hashMap.get(email);
    }

    /**
     * 删除验证码
     * @param email 用户邮箱
     */
    private  void deleteCode(String email) {
        hashMap.remove(email);
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
