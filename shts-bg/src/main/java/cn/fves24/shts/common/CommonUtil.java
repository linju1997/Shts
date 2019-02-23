package cn.fves24.shts.common;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.Random;
import java.util.concurrent.*;


/**
 * 工具类
 *
 * @author fves
 */
public class CommonUtil {

    /**
     * 线程池
     */
    public static ExecutorService threadPolls = new ThreadPoolExecutor(10, 15, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

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
