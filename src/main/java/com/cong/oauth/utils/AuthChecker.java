package com.cong.oauth.utils;

import com.cong.oauth.config.AuthConfig;
import com.cong.oauth.config.AuthDefaultSource;
import com.cong.oauth.config.AuthSource;
import com.cong.oauth.enums.AuthResponseStatus;
import com.cong.oauth.exception.AuthException;

/**
 * 授权配置类的校验器
 *
 * @author cong
 * @date 2024/04/19
 */
public class AuthChecker {
    private AuthChecker() {

    }

    /**
     * 是否支持第三方登录
     *
     * @param config config
     * @param source source
     * @return true or false
     * @since 1.6.1-beta
     */
    public static boolean isSupportedAuth(AuthConfig config, AuthSource source) {
        boolean isSupported = StringUtils.isNotEmpty(config.getClientId())
                && StringUtils.isNotEmpty(config.getClientSecret());
        if (isSupported && AuthDefaultSource.STACK_OVERFLOW == source) {
            isSupported = StringUtils.isNotEmpty(config.getStackOverflowKey());
        }

        return isSupported;
    }

    /**
     * 检查配置合法性。针对部分平台， 对redirect uri有特定要求。一般来说redirect uri都是http://，而对于facebook平台， redirect uri 必须是https的链接
     *
     * @param config config
     * @param source source
     * @since 1.6.1-beta
     */
    public static void checkConfig(AuthConfig config, AuthSource source) {
        String redirectUri = config.getRedirectUri();
        if (config.isIgnoreCheckRedirectUri()) {
            return;
        }
        if (StringUtils.isEmpty(redirectUri)) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_REDIRECT_URI, source);
        }
        if (!GlobalAuthUtils.isHttpProtocol(redirectUri) && !GlobalAuthUtils.isHttpsProtocol(redirectUri)) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_REDIRECT_URI, source);
        }

    }
}
