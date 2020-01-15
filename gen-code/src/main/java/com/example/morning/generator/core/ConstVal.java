package com.example.morning.generator.core;

import java.nio.charset.StandardCharsets;

/**
 * 常量
 *
 * @author sunx
 * @date 2020-01-08
 */
public class ConstVal {
    public static final String DOT = ".";
    public static final String BACK_SLASH = "\\";
    public static final String SLASH = "/";
    public static final String MODULE_NAME = "ModuleName";

    public static final String ENTITY = "Entity";
    public static final String SERVICE = "Service";
    public static final String SERVICE_IMPL = "ServiceImpl";
    public static final String MAPPER = "Mapper";
    public static final String XML = "Xml";
    public static final String API_CONTROLLER = "ApiController";
    public static final String CONTROLLER = "Controller";
    public static final String VO = "VO";
    public static final String FEIGN = "Feign";
    public static final String FEIGN_DEGRADED = "FeignDegraded";
    public static final String VO_MAPPER = "VoMapper";

    public static final String ENTITY_PATH = "entity_path";
    public static final String SERVICE_PATH = "service_path";
    public static final String SERVICE_IMPL_PATH = "service_impl_path";
    public static final String MAPPER_PATH = "mapper_path";
    public static final String XML_PATH = "xml_path";
    public static final String API_CONTROLLER_PATH = "api_controller_path";
    public static final String CONTROLLER_PATH = "controller_path";
    public static final String VO_PATH = "vo_path";
    public static final String VO_MAPPER_PATH = "vo_mapper_path";
    public static final String FEIGN_PATH = "feign_path";
    public static final String FEIGN_DEGRADED_PATH = "feign_degraded_path";

    public static final String JAVA_TMPDIR = "java.io.tmpdir";
    public static final String UTF8 = StandardCharsets.UTF_8.name();
    public static final String UNDERLINE = "_";

    public static final String JAVA_SUFFIX = ".java";
    public static final String XML_SUFFIX = ".xml";

    public static final String TEMPLATE_ENTITY_JAVA = "/templates/entity.java";
    public static final String TEMPLATE_MAPPER = "/templates/mapper.java";
    public static final String TEMPLATE_XML = "/templates/mapper.xml";
    public static final String TEMPLATE_SERVICE = "/templates/service.java";
    public static final String TEMPLATE_SERVICE_IMPL = "/templates/serviceImpl.java";
    public static final String TEMPLATE_API_CONTROLLER = "/templates/apiController.java";
    public static final String TEMPLATE_CONTROLLER = "/templates/controller.java";
    public static final String TEMPLATE_VO = "/templates/vo.java";
    public static final String TEMPLATE_VO_MAPPER = "/templates/voMapper.java";
    public static final String TEMPLATE_FEIGN = "/templates/feign.java";
    public static final String TEMPLATE_FEIGN_DEGRADED = "/templates/feignDegraded.java";

    public static final String SUPER_MAPPER_CLASS = "com.baomidou.mybatisplus.core.mapper.BaseMapper";
    public static final String SUPER_SERVICE_CLASS = "com.baomidou.mybatisplus.extension.service.IService";
    public static final String SUPER_SERVICE_IMPL_CLASS = "com.baomidou.mybatisplus.extension.service.impl.ServiceImpl";

    public static final String BIGDECIMALFLAG = "BigDecimal";
    public static final String DATEFLAG = "Date";
    public static final String PRI = "PRI";

    // 数据库字段类型

    public static final String CHAR_1 = "char";
    public static final String TEXT_1 = "text";
    public static final String JSON_1 = "json";
    public static final String ENUM_1 = "enum";
    public static final String BIGINT_1 = "bigint";
    public static final String INT_1 = "int";
    public static final String DATE_1 = "date";
    public static final String TIME_1 = "time";
    public static final String YEAR_1 = "year";
    public static final String BIT_1 = "bit";
    public static final String DECIMAL_1 = "decimal";
    public static final String BLOB_1 = "blob";
    public static final String FLOAT_1 = "float";
    public static final String DOUBLE_1 = "double";

    // 系统

    public static final String MAC_SYS = "Mac";
    public static final String WIN_SYS = "Windows";

    // 默认包

    public static final String DEFAULT_ENTITY_PACKAGE = "entity";
    public static final String DEFAULT_MAPPER_PACKAGE = "mapper";
    public static final String DEFAULT_XML_PACKAGE = "mapper.xml";
    public static final String DEFAULT_SERVICE_PACKAGE = "service";
    public static final String DEFAULT_SERVICE_IMPL_PACKAGE = "service.impl";
    public static final String DEFAULT_API_CONTROLLER_PACKAGE = "controller.api";
    public static final String DEFAULT_FEIGN_PACKAGE = "feign";
    public static final String DEFAULT_FEIGN_DEGRADED_PACKAGE = "feign.degraded";
    public static final String DEFAULT_VO_PACKAGE = "vo";
    public static final String DEFAULT_VO_MAPPER_PACKAGE = "vo.mapper";
    public static final String DEFAULT_CONTROLLER_PACKAGE = "controller";

    private ConstVal() {
    }
}
