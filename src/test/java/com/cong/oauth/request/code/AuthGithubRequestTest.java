package com.cong.oauth.request.code;


import com.cong.oauth.config.AuthConfig;
import com.cong.oauth.config.AuthDefaultSource;
import com.cong.oauth.request.AuthRequest;
import org.junit.Test;

/**
 * auth GitHub 请求测试
 *
 * @author cong
 * @date 2024/04/22
 */
public class AuthGithubRequestTest {

    @Test
    public void authorize() {
        // 创建授权request
        AuthRequest authRequest = new AuthGithubRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("http://127.0.0.1:8080/callback")
                .build(), AuthDefaultSource.GITHUB);
        // 生成授权页面
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的参数
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
//        authRequest.login(callback);
    }

}