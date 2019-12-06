package com.example.morning.demo.domain.vo;

import com.example.morning.demo.domain.TbTest1;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunx
 * @date 2019/12/06
 * @description 测试VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("测试VO")
public class TbTest1VO extends TbTest1 {
}
