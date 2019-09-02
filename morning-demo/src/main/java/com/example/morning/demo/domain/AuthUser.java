package com.example.morning.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sunx
 * @date 2019/08/30
 * @description 用户
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户")
@Table(name = "auth_user")
public class AuthUser {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 账号
     */
    @Column(name = "user_name")
    @ApiModelProperty("账号")
    private String user_name;

    /**
     * 用户名
     */
    @Column(name = "name")
    @ApiModelProperty("用户名")
    private String name;

    /**
     * 密码
     */
    @Column(name = "password")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;

    /**
     * 金额
     */
    @Column(name = "price")
    @ApiModelProperty("金额")
    private BigDecimal price;
}
