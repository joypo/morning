package com.example.mapstruct.demo.po;

import com.example.mapstruct.demo.base.BasePo;
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
public class UserPo extends BasePo {
    private String id;

    private String name;

    private String sex;
}
