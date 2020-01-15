package com.example.morning.generator.config;

import com.example.morning.generator.core.ConstVal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sunx
 * @date 2020-01-08
 */
@Data
@Accessors(chain = true)
public class StrategyConfig {

    /**
     * 包含的标识 false  不包含  true  包含
     */
    private Boolean includeFlag = false;

    /**
     * 包含的表
     */
    private String[] include;

    /**
     * 表前缀
     */
    private String[] tablePrefix;

    /**
     * 需要排除的字段
     */
    private String[] excludeFields;

    /**
     * 自定义继承的Entity类全称，带包名
     */
    private String superEntityClass;

    /**
     * 自定义继承的Mapper类全称，带包名
     */
    private String superMapperClass = ConstVal.SUPER_MAPPER_CLASS;
    /**
     * 自定义继承的Service类全称，带包名
     */
    private String superServiceClass = ConstVal.SUPER_SERVICE_CLASS;
    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    private String superServiceImplClass = ConstVal.SUPER_SERVICE_IMPL_CLASS;
    /**
     * 自定义继承的Controller类全称，带包名
     */
    private String superControllerClass;

    public StrategyConfig setInclude(String... include) {
        this.include = include;
        return this;
    }

    public StrategyConfig setTablePrefix(String... tablePrefix) {
        this.tablePrefix = tablePrefix;
        return this;
    }

    public StrategyConfig setExcludeFields(String... excludeFields) {
        this.excludeFields = excludeFields;
        return this;
    }
}
