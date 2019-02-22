package cn.fves24.shts.router;

import cn.fves24.shts.auth.TokenAuth;
import cn.fves24.shts.common.ComMsg;
import cn.fves24.shts.common.RespVO;
import org.springframework.web.bind.annotation.*;

/**
 * 用户修改信息
 *
 * @author fves
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ModifyInfoRouter {

    @PostMapping("/modify/username")
    @TokenAuth
    public RespVO modifyUsername(String username, @CookieValue String token) {
        return RespVO.newSuccessRespVO(ComMsg.SUCCESS);
    }

    @PostMapping("/modify/email")
    @TokenAuth
    public RespVO modifyEmail(String email, @CookieValue String token) {
        return RespVO.newSuccessRespVO(ComMsg.SUCCESS);
    }
}
