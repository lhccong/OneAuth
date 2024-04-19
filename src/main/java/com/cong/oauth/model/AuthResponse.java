package com.cong.oauth.model;

import com.cong.oauth.enums.AuthResponseStatus;
import lombok.*;

import java.io.Serializable;

/**
 * OneAuth统一授权响应类
 *
 * @author cong
 * @date 2024/04/19
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse<T> implements Serializable {
    /**
     * 授权响应状态码
     */
    private int code;

    /**
     * 授权响应信息
     */
    private String msg;

    /**
     * 授权响应数据，当且仅当 code = 2000 时返回
     */
    private transient T data;

    /**
     * 是否请求成功
     *
     * @return true or false
     */
    public boolean ok() {
        return this.code == AuthResponseStatus.SUCCESS.getCode();
    }
}
