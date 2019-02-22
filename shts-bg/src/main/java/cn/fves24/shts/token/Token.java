package cn.fves24.shts.token;


/**
 * 生成和校验Token
 *
 * @author token
 */
public interface Token {

    /**
     * 根据用户生成Token
     *
     * @param email 用户邮箱
     * @return token
     */
    String makeToken(String email);

    /**
     * 校验Token
     *
     * @param token  数据库中的有效Token
     * @param uToken 用户Token
     * @return 校验结果
     */
    boolean verify(String token, String uToken);
}
