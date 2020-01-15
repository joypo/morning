package com.example.mapstruct.demo.base;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sunx
 * @date 2020-01-11
 */
@Data
@Accessors(chain = true)
public class BasePo {
    private String status;
}
