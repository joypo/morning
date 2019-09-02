package com.example.morning.demo.exception;

import com.example.morning.demo.base.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sunx
 * @date 2019-08-21
 * @description
 */
@Getter
@AllArgsConstructor
public enum BizExceptionEnum implements ExceptionType {

    ;
    private int code;
    private String description;
}
