package cn.fves24.shts.common;

import cn.fves24.shts.model.User;

import java.util.Random;


/**
 * 工具类
 *
 * @author fves
 */
public class CommonUtil {

    /**
     * 获取随机验证码
     *
     * @return 验证码
     */
    public static String getRandomCode() {
        Random random = new Random(System.currentTimeMillis());
        return String.valueOf(random.nextInt(900000) + 100000);
    }
}
