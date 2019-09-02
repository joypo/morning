package com.example.morning.demo.domain.search;

import com.example.morning.demo.domain.AuthUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunx
 * @date 2019/08/30
 * @description 用户SearchVO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户SearchVO")
public class AuthUserSearchVO extends AuthUser {
}
