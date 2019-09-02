package com.example.morning.demo.base.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sunx
 * @date 2019-08-30
 * @description
 */
@Data
@ApiModel("页面分页入参")
public class PageSearchParam<T> {

    @ApiModelProperty("当前页码")
    private Integer page = 0;

    @ApiModelProperty("页面size")
    private Integer size = 0;

    @ApiModelProperty("查询条件")
    private T data;
}
