package com.example.rate.limiter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sunx
 * @date 2019-10-10
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    /**
     * 资源名称，用于描述接口功能
     *
     * @return
     */
    String name() default "";

    /**
     * 资源 key
     *
     * @return
     */
    String key() default "";

    /**
     * key prefix
     *
     * @return
     */
    String prefix() default "";

    /**
     * 时间的，单位秒
     *
     * @return
     */
    int period() default 1;

    /**
     * 限制访问次数
     *
     * @return
     */
    int count() default 10;

    /**
     * 限制类型
     *
     * @return
     */
    LimitType limitType() default LimitType.IP;
}
