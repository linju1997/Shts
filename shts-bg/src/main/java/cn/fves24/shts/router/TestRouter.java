package cn.fves24.shts.router;

import cn.fves24.shts.common.RespVO;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestRouter {


    @RequestMapping("/time")
    public RespVO time() {
        return RespVO.getSuccess(LocalDateTime.now());
    }

    @RequestMapping("/cookie")
    public RespVO cookie(@CookieValue("JSESSIONID") String cookie){
        return RespVO.getSuccess(cookie);
    }
}
