package com.lin.authoritycontrol.common.exception;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常
 *
 * @author 林维家
 * @since 2024/12/25 下午8:02
 */
@Slf4j
@NoArgsConstructor
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
    }
}
