package com.cong.oauth.enums.scope;

/**
 * 各个平台 scope 类的统一接口
 *
 * @author cong
 * @date 2024/04/22
 */
public interface AuthScope {

    /**
     * 获取字符串 {@code scope}，对应为各平台实际使用的 {@code scope}
     *
     * @return String
     */
    String getScope();

    /**
     * 判断当前 {@code scope} 是否为各平台默认启用的
     *
     * @return boolean
     */
    boolean isDefault();
}
