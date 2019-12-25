package com.example.demo.drools.entity;

import lombok.Data;

import java.util.Date;


/**
 * @author sunx
 * @date 2019-12-23
 * @description
 */
@Data
public class Rules {
    private Integer id;
    private String name;
    private String rule;
    private Date createTime;
    private Date updateTime;
    private Integer visible;
}
