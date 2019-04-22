package cn.fves24.shts.router;

import cn.fves24.shts.common.Constants;
import cn.fves24.shts.common.RespVO;
import cn.fves24.shts.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class TestRouter {

    private HttpServletRequest request;

    @Autowired
    public TestRouter(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping("/login/test")
    public RespVO loginTest() {
        HttpSession session = request.getSession(true);
        System.out.println(session);
        User user = new User();

        user.setId(1000);
        user.setUsername("user01");
        user.setEmail("");

        session.setAttribute("user", user);
        session.setAttribute(Constants.LOGIN_KEY, Constants.LOGIN_YES);
        return RespVO.getSuccess("login success");
    }
}
