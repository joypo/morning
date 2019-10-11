package com.example.rate.limiter.exception;

import lombok.Getter;

/**
 * @author sunx
 * @date 2019-10-10
 * @description 限流异常
 */
@Getter
public class LimiterException extends RuntimeException {

    private String msg;

    public LimiterException() {
        super();
    }

    public LimiterException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
