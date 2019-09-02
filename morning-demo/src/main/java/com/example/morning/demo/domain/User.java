package com.example.morning.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sunx
 * @date 2019-08-14
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户信息")
@Table(name = "user")
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 登录名
     */
    @Column(name = "username")
    @ApiModelProperty("登录名")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 性别
     */
    @Column(name = "sex")
    @ApiModelProperty("性别")
    private Integer sex;

    /**
     * 注册时间
     */
    @Column(name = "register_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("注册时间")
    private Date registerDate;
}
