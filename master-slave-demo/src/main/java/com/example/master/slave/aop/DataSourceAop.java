package com.example.master.slave.aop;

import com.example.master.slave.config.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author sunx
 * @date 2019-12-20
 * @description
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("@annotation(com.example.master.slave.annotation.Slave)")
    public void readPointcut() {
    }

    @Pointcut("@annotation(com.example.master.slave.annotation.Master)")
    public void writePointcut() {
    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
