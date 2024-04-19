package com.cong.oauth.request.code;

import com.cong.oauth.config.AuthConfig;
import com.cong.oauth.config.AuthSource;
import com.cong.oauth.request.core.AuthDefaultRequest;

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
}
