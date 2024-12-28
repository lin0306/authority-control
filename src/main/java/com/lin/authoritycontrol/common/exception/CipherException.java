package com.lin.authoritycontrol.common.exception;

/**
 * 加密异常
 *
 * @author 林维家
 * @since 2024/12/28 下午3:28
 */
public class CipherException extends RuntimeException {

    public CipherException(String message) {
        super(message);
    }

    public CipherException(String message, Throwable e) {
        super(message, e);
    }
}
