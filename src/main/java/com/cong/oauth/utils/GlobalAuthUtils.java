package com.cong.oauth.utils;


import com.cong.oauth.exception.AuthException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局的工具类
 *
 * @author cong
 * @date 2024/04/19
 */
public class GlobalAuthUtils {

    private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

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

    /**
     * string字符串转map，str格式为 {@code xxx=xxx&xxx=xxx}
     *
     * @param accessTokenStr 待转换的字符串
     * @return map
     */
    public static Map<String, String> parseStringToMap(String accessTokenStr) {
        Map<String, String> res = null;
        if (accessTokenStr.contains("&")) {
            String[] fields = accessTokenStr.split("&");
            res = new HashMap<>((int) (fields.length / 0.75 + 1));
            for (String field : fields) {
                if (field.contains("=")) {
                    String[] keyValue = field.split("=");
                    res.put(GlobalAuthUtils.urlDecode(keyValue[0]), keyValue.length == 2 ? GlobalAuthUtils.urlDecode(keyValue[1]) : null);
                }
            }
        } else {
            res = new HashMap<>(0);
        }
        return res;
    }

    /**
     * 解码
     *
     * @param value str
     * @return decode str
     */
    public static String urlDecode(String value) {
        if (value == null) {
            return "";
        }
        try {
            return URLDecoder.decode(value, GlobalAuthUtils.DEFAULT_ENCODING.displayName());
        } catch (UnsupportedEncodingException e) {
            throw new AuthException("Failed To Decode Uri", e);
        }
    }
}
