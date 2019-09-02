package com.example.morning.demo.exception;


import com.example.morning.demo.base.ExceptionType;

/**
 * @author sunx
 * @date 2019-08-14
 * @description 业务运行时异常
 */
public class BizRuntimeException extends RuntimeException {

    private Integer code;
    private String description;

    public BizRuntimeException() {
        super();
    }

    public BizRuntimeException(ExceptionType exceptionType) {
        super(exceptionType.getDescription());
        this.code = exceptionType.getCode();
        this.description = exceptionType.getDescription();
    }

    public BizRuntimeException(ExceptionType exceptionType, String message) {
        super(message);
        this.code = exceptionType.getCode();
        this.description = exceptionType.getDescription();
    }

    public BizRuntimeException(Integer code, String description) {
        super(description);
        this.code = code;
        this.description = description;
    }

    public BizRuntimeException(String message) {
        super(message);
    }

    public BizRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizRuntimeException(Throwable cause) {
        super(cause);
    }

    public BizRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
