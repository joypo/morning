package com.example.morning.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author sunx
 * @date 2019/12/06
 * @description 测试
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("测试")
@Table(name = "tb_test1")
public class TbTest1 {

    /**
     * 主键
     */
    @Column(name = "id")
    @ApiModelProperty("主键")
    @Id
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    @ApiModelProperty("用户名")
    private String user_name;

    /**
     * 年龄
     */
    @Column(name = "age")
    @ApiModelProperty("年龄")
    private Integer age;
}
