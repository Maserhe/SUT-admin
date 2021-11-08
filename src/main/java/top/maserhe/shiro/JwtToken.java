package top.maserhe.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Description:
 *
 * @author maserhe
 * @date 2021/11/1 1:56 下午
 **/
public class JwtToken implements AuthenticationToken {

    private String token;
    public JwtToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}