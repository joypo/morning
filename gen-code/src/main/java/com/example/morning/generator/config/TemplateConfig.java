package com.example.morning.generator.config;

import com.example.morning.generator.core.ConstVal;
import lombok.Data;

/**
 * 模板路径
 * @author sunx
 * @date 2020-01-08
 */
@Data
public class TemplateConfig {
    private String entity = ConstVal.TEMPLATE_ENTITY_JAVA;

    private String service = ConstVal.TEMPLATE_SERVICE;

    private String serviceImpl = ConstVal.TEMPLATE_SERVICE_IMPL;

    private String mapper = ConstVal.TEMPLATE_MAPPER;

    private String xml = ConstVal.TEMPLATE_XML;

    private String apiController = ConstVal.TEMPLATE_API_CONTROLLER;

    private String vo = ConstVal.TEMPLATE_VO;

    private String voMapper = ConstVal.TEMPLATE_VO_MAPPER;

    private String controller = ConstVal.TEMPLATE_CONTROLLER;

    private String feign = ConstVal.TEMPLATE_FEIGN;

    private String feignDegraded = ConstVal.TEMPLATE_FEIGN_DEGRADED;
}
