package com.example.mapstruct.demo.vo;

import com.example.mapstruct.demo.po.UserPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author sunx
 * @date 2020-01-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserVO extends UserPo {
    private String name;

    private String kk;
}
