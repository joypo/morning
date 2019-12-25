package com.example.demo.drools.bean;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

/**
 * @author sunx
 * @date 2019-12-23
 * @description
 */
@Data
@Serialization
public class Person {
    private Integer age;

    private String name;

    private String desc;
}
