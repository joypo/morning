package com.example.morning.generator.config;

import com.example.morning.generator.core.ConstVal;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author sunx
 * @date 2020-01-08
 */
@Data
@Accessors(chain = true)
public class PackageConfig {
    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    private String parent = null;
    /**
     * 父包模块名
     */
    private String moduleName = null;
    /**
     * Entity包名
     */
    private String entity = ConstVal.DEFAULT_ENTITY_PACKAGE;
    /**
     * Service包名
     */
    private String service = ConstVal.DEFAULT_SERVICE_PACKAGE;
    /**
     * Service Impl包名
     */
    private String serviceImpl = ConstVal.DEFAULT_SERVICE_IMPL_PACKAGE;
    /**
     * Mapper包名
     */
    private String mapper = ConstVal.DEFAULT_MAPPER_PACKAGE;
    /**
     * Mapper XML包名
     */
    private String xml = ConstVal.DEFAULT_XML_PACKAGE;
    /**
     * Controller包名
     */
    private String controller = ConstVal.DEFAULT_CONTROLLER_PACKAGE;
    /**
     * VO包名
     */
    private String vo = ConstVal.DEFAULT_VO_PACKAGE;
    /**
     * feign
     */
    private String feign = ConstVal.DEFAULT_FEIGN_PACKAGE;
    /**
     * feign-degraded包名
     */
    private String feignDegraded = ConstVal.DEFAULT_FEIGN_DEGRADED_PACKAGE;
    /**
     * api-Controller包名
     */
    private String apiController = ConstVal.DEFAULT_API_CONTROLLER_PACKAGE;

    private String voMapper = ConstVal.DEFAULT_VO_MAPPER_PACKAGE;
    /**
     * 路径配置信息
     */
    private Map<String, String> pathInfo;

    /**
     * 父包名
     */
    public String getParent() {
        if (StringUtils.isNotBlank(moduleName)) {
            return parent + ConstVal.DOT + moduleName;
        }
        return parent;
    }
}
