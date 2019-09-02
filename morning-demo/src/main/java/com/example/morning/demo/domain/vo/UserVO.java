package com.example.morning.demo.domain.vo;

import com.example.morning.demo.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunx
 * @date 2019-08-27
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户VO")
public class UserVO extends User {

}
