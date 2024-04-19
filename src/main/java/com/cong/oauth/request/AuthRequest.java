package com.cong.oauth.request;

import com.cong.oauth.enums.AuthResponseStatus;
import com.cong.oauth.exception.AuthException;
import com.cong.oauth.model.AuthCallback;
import com.cong.oauth.model.AuthResponse;
import com.cong.oauth.model.AuthToken;
import com.cong.oauth.request.core.AuthDefaultRequest;

/**
 * {@code Request}公共接口，所有平台的{@code Request}都需要实现该接口
 *
 * @author cong
 * @date 2024/04/19
 */
public interface AuthRequest {

    /**
     * 返回授权url，可自行跳转页面
     * <p>
     * 不建议使用该方式获取授权地址，不带{@code state}的授权地址，容易受到csrf攻击。
     * 建议使用{@link AuthDefaultRequest#authorize(String)}方法生成授权地址，在回调方法中对{@code state}进行校验
     *
     * @return 返回授权地址
     */
    @Deprecated
    default String authorize() {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     */
    default String authorize(String state) {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }

    /**
     * 第三方登录
     *
     * @param authCallback 用于接收回调参数的实体
     * @return 返回登录成功后的用户信息
     */
    default AuthResponse login(AuthCallback authCallback) {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }

    /**
     * 撤销授权
     *
     * @param authToken 登录成功后返回的Token信息
     * @return AuthResponse
     */
    default AuthResponse revoke(AuthToken authToken) {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }

    /**
     * 刷新access token （续期）
     *
     * @param authToken 登录成功后返回的Token信息
     * @return AuthResponse
     */
    default AuthResponse refresh(AuthToken authToken) {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }
}
