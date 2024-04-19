package com.cong.oauth.exception;


import com.cong.oauth.config.AuthSource;
import com.cong.oauth.enums.AuthResponseStatus;
import lombok.Getter;

/**
 * JustAuth通用异常类
 *
 * @author cong
 * @date 2024/04/19
 */
@Getter
public class AuthException extends RuntimeException {

    private final int errorCode;
    private final String errorMsg;

    public AuthException(String errorMsg) {
        this(AuthResponseStatus.FAILURE.getCode(), errorMsg);
    }

    public AuthException(String errorMsg, AuthSource source) {
        this(AuthResponseStatus.FAILURE.getCode(), errorMsg, source);
    }

    public AuthException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public AuthException(AuthResponseStatus status) {
        this(status.getCode(), status.getMsg());
    }

    public AuthException(int errorCode, String errorMsg, AuthSource source) {
        this(errorCode, String.format("%s [%s]", errorMsg, source.getName()));
    }

    public AuthException(AuthResponseStatus status, AuthSource source) {
        this(status.getCode(), status.getMsg(), source);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = 5000;
        this.errorMsg = cause.getMessage();
    }

    public AuthException(Throwable cause) {
        super(cause);
        this.errorCode = 5000;
        this.errorMsg = cause.getMessage();
    }

}
