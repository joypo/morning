package com.example.morning.demo.controller.handler;

import com.example.morning.demo.base.GlobalErrorEnum;
import com.example.morning.demo.base.HttpResult;
import com.example.morning.demo.exception.BizRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.sql.SQLException;

/**
 * @author sunx
 * @date 2019-08-14
 * @description
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求参数类型错误异常的捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public HttpResult<String> badRequest(BindException e) {
        logger.error("BAD_REQUEST,message {}", e.getMessage());
        return new HttpResult<>(GlobalErrorEnum.BAD_REQUEST);
    }

    /**
     * 404错误异常的捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public HttpResult<String> badRequestNotFound(BindException e) {
        logger.error("NOT_FOUND,message {}", e.getMessage());
        return new HttpResult<>(GlobalErrorEnum.NOT_FOUND);
    }

    /**
     * mybatis未绑定异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindingException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResult<String> mybatis(Exception e) {
        logger.error("INTERNAL_SERVER_ERROR,message {}", e.getMessage());
        return new HttpResult<>(GlobalErrorEnum.BOUND_STATEMENT_NOT_FOUNT);
    }

    /**
     * 自定义异常的捕获
     * 自定义抛出异常。统一的在这里捕获返回JSON格式的友好提示。
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = {BizRuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> HttpResult<T> sendError(BizRuntimeException exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        logger.error("INTERNAL_SERVER_ERROR url ={} ,message {}", requestURI, exception.getDescription());
        return new HttpResult<>(GlobalErrorEnum.SYSTEM_ERROR);
    }

    /**
     * 数据库操作出现异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {SQLException.class, DataAccessException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResult<String> systemError(Exception e) {
        logger.error("INTERNAL_SERVER_ERROR,message {}", e.getMessage());
        return new HttpResult<>(GlobalErrorEnum.DATABASE_ERROR);
    }

    /**
     * 网络连接失败！
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ConnectException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResult<String> connect(Exception e) {
        logger.error("connect ,message {}", e.getMessage());
        return new HttpResult<>(GlobalErrorEnum.CONNECTION_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public HttpResult<String> notAllowed(Exception e) {
        logger.error("METHOD_NOT_ALLOWED,message {}", e.getMessage());
        return new HttpResult<>(GlobalErrorEnum.METHOD_NOT_ALLOWED);
    }
}
