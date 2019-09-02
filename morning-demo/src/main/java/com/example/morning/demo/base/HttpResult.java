package com.example.morning.demo.base;

import lombok.Data;

/**
 * @author sunx
 * @date 2019-08-14
 * @description 统一接口返回格式
 */
@Data
public class HttpResult<T> {
    private String code = "0";
    private String message;
    private T data;

    public HttpResult() {
        this.code = GlobalErrorEnum.SUCCESS_OPTION.getCode();
        this.message = GlobalErrorEnum.SUCCESS_OPTION.getMsg();
    }

    public HttpResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }

    public HttpResult(T data) {
        this.code = GlobalErrorEnum.SUCCESS_OPTION.getCode();
        this.message = GlobalErrorEnum.SUCCESS_OPTION.getMsg();
        this.data = data;
    }

    public HttpResult(ResultCode resultCode, String message) {
        this.code = resultCode.getCode();
        this.message = message;
    }

    public HttpResult(ResultCode resultCode, String message, T data) {
        this.code = resultCode.getCode();
        this.message = message;
        this.data = data;
    }

    public static HttpResult success() {
        return new HttpResult();
    }

    public static <T> HttpResult<T> successGenerics(T data) {
        return new HttpResult(data);
    }

    public static HttpResult failure(ResultCode resultCode) {
        return new HttpResult(resultCode);
    }

    public static <T> HttpResult<T> failureGenerics(ResultCode resultCode, T data) {
        HttpResult result = new HttpResult();
        result.code = resultCode.getCode();
        result.message = resultCode.getMsg();
        result.data = data;
        return result;
    }

    @Override
    public String toString() {
        return "result {" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
