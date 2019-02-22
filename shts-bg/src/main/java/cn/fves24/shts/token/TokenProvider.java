package cn.fves24.shts.token;


import org.springframework.stereotype.Component;

/**
 * TODO
 * 生成和校验Token
 * @author fves
 */
@Component
public class TokenProvider implements Token {

    @Override
    public String makeToken(String email) {
        return email;
    }

    @Override
    public boolean verify(String token, String uToken) {
        return false;
    }
}
