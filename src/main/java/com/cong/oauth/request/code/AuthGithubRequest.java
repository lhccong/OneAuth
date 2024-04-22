package com.cong.oauth.request.code;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cong.oauth.cache.AuthStateCache;
import com.cong.oauth.config.AuthConfig;
import com.cong.oauth.config.AuthSource;
import com.cong.oauth.enums.AuthUserGender;
import com.cong.oauth.enums.scope.AuthGithubScope;
import com.cong.oauth.exception.AuthException;
import com.cong.oauth.model.AuthCallback;
import com.cong.oauth.model.AuthToken;
import com.cong.oauth.model.AuthUser;
import com.cong.oauth.request.core.AuthDefaultRequest;
import com.cong.oauth.utils.AuthScopeUtils;
import com.cong.oauth.utils.GlobalAuthUtils;
import com.cong.oauth.utils.HttpUtils;
import com.cong.oauth.utils.UrlBuilder;
import com.xkcoding.http.support.HttpHeader;

import java.util.Map;

/**
 * auth GitHub 请求
 *
 * @author cong
 * @date 2024/04/19
 */
public class AuthGithubRequest extends AuthDefaultRequest {

    public AuthGithubRequest(AuthConfig config, AuthSource source) {
        super(config, source);
    }

    public AuthGithubRequest(AuthConfig config, AuthSource source, AuthStateCache authStateCache) {
        super(config, source, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String response = doPostAuthorizationCode(authCallback.getCode());
        Map<String, String> res = GlobalAuthUtils.parseStringToMap(response);

        this.checkResponse(res.containsKey("error"), res.get("error_description"));

        return AuthToken.builder()
                .accessToken(res.get("access_token"))
                .scope(res.get("scope"))
                .tokenType(res.get("token_type"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpHeader header = new HttpHeader();
        header.add("Authorization", "token " + authToken.getAccessToken());
        String response = new HttpUtils(config.getHttpConfig()).get(UrlBuilder.fromBaseUrl(source.userInfo()).build(), null, header, false).getBody();
        JSONObject object = JSON.parseObject(response);

        this.checkResponse(object.containsKey("error"), object.getString("error_description"));

        return AuthUser.builder()
                .rawUserInfo(object)
                .uuid(object.getString("id"))
                .username(object.getString("login"))
                .avatar(object.getString("avatar_url"))
                .blog(object.getString("blog"))
                .nickname(object.getString("name"))
                .company(object.getString("company"))
                .location(object.getString("location"))
                .email(object.getString("email"))
                .remark(object.getString("bio"))
                .gender(AuthUserGender.UNKNOWN)
                .token(authToken)
                .source(source.toString())
                .build();
    }

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     */
    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(super.authorize(state))
                .queryParam("scope", this.getScopes(" ", true, AuthScopeUtils.getDefaultScopes(AuthGithubScope.values())))
                .build();
    }

    /**
     * 通用的 authorizationCode 协议
     *
     * @param code code码
     * @return Response
     */
    protected String doPostAuthorizationCode(String code) {
        return new HttpUtils(config.getHttpConfig()).post(accessTokenUrl(code)).getBody();
    }

    private void checkResponse(boolean error, String errorDescription) {
        if (error) {
            throw new AuthException(errorDescription);
        }
    }
}
