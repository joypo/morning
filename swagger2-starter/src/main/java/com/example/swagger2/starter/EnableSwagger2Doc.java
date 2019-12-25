package com.example.swagger2.starter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author sunx
 * @date 2019-12-23
 * @description
 */
@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SwaggerAutoConfiguration.class})
public @interface EnableSwagger2Doc {
}
