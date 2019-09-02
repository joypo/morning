package com.example.morning.demo.domain.vo;

import com.example.morning.demo.domain.AuthUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunx
 * @date 2019/08/30
 * @description 用户VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户VO")
public class AuthUserVO extends AuthUser {
}
