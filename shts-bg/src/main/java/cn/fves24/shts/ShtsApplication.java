package cn.fves24.shts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fves
 */
@MapperScan(basePackages = {"cn.fves24.shts.mysql.mapper"})
@SpringBootApplication
public class ShtsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShtsApplication.class, args);
    }

}

