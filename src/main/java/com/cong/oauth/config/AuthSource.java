package com.cong.oauth.config;

import com.cong.oauth.enums.AuthResponseStatus;
import com.cong.oauth.exception.AuthException;
import com.cong.oauth.request.core.AuthDefaultRequest;

/**
 * OneAuth平台的 API 地址的统一接口
 *
 * @author cong
 * @date 2024/04/19
 */
public interface AuthSource {
    /**
     * 授权的api
     *
     * @return url
     */
    String authorize();

    /**
     * 获取accessToken的api
     *
     * @return url
     */
    String accessToken();

    /**
     * 获取用户信息的api
     *
     * @return url
     */
    String userInfo();

    /**
     * 取消授权的api
     *
     * @return url
     */
    default String revoke() {
        throw new AuthException(AuthResponseStatus.UNSUPPORTED);
    }

    /**
     * 刷新授权的api
     *
     * @return url
     */
    default String refresh() {
        throw new AuthException(AuthResponseStatus.UNSUPPORTED);
    }

    /**
     * 获取Source的字符串名字
     *
     * @return name
     */
    default String getName() {
        if (this instanceof Enum) {
            return String.valueOf(this);
        }
        return this.getClass().getSimpleName();
    }

    /**
     * 平台对应的 AuthRequest 实现类，必须继承自 {@link AuthDefaultRequest}
     *
     * @return class
     */
    Class<? extends AuthDefaultRequest> getTargetClass();
}
