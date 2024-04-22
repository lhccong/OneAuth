package com.cong.oauth.config;


import com.cong.oauth.request.code.AuthGithubRequest;
import com.cong.oauth.request.core.AuthDefaultRequest;

/**
 * OneAuth 内置的各 api 需要的 url， 用枚举类分平台类型管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.0
 */
public enum AuthDefaultSource implements AuthSource {
    /**
     * Github
     */
    GITHUB {
        @Override
        public String authorize() {
            return "https://github.com/login/oauth/authorize";
        }

        @Override
        public String accessToken() {
            return "https://github.com/login/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.github.com/user";
        }

        @Override
        public Class<? extends AuthDefaultRequest> getTargetClass() {
            return AuthGithubRequest.class;
        }
    },

    /**
     * Stack Overflow
     */
    STACK_OVERFLOW {
        @Override
        public String authorize() {
            return "https://stackoverflow.com/oauth";
        }

        @Override
        public String accessToken() {
            return "https://stackoverflow.com/oauth/access_token/json";
        }

        @Override
        public String userInfo() {
            return "https://api.stackexchange.com/2.2/me";
        }

        @Override
        public Class<? extends AuthDefaultRequest> getTargetClass() {
            return AuthDefaultRequest.class;
        }
    },
    /**
     * Twitter
     *
     * @since 1.13.0
     */
    TWITTER {
        @Override
        public String authorize() {
            return "https://api.twitter.com/oauth/authenticate";
        }

        @Override
        public String accessToken() {
            return "https://api.twitter.com/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.twitter.com/1.1/account/verify_credentials.json";
        }

        @Override
        public Class<? extends AuthDefaultRequest> getTargetClass() {
            return AuthDefaultRequest.class;
        }
    },

}
