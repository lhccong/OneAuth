package com.cong.oauth.utils;


/**
 * 全局的工具类
 *
 * @author cong
 * @date 2024/04/19
 */
public class GlobalAuthUtils {

  private GlobalAuthUtils() {
      // 工具类，不需要实例化
  }

    /**
     * 是否为https协议
     *
     * @param url 待验证的url
     * @return true: https协议, false: 非https协议
     */
    public static boolean isHttpsProtocol(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return url.startsWith("https://") || url.startsWith("https%3A%2F%2F");
    }

    /**
     * 是否为http协议
     *
     * @param url 待验证的url
     * @return true: http协议, false: 非http协议
     */
    public static boolean isHttpProtocol(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return url.startsWith("http://") || url.startsWith("http%3A%2F%2F");
    }
}
