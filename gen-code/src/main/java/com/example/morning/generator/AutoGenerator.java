package com.example.morning.generator;

import com.example.morning.generator.builder.ConfigBuilder;
import com.example.morning.generator.config.*;
import com.example.morning.generator.engine.AbstractTemplateEngine;
import com.example.morning.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunx
 * @date 2020-01-08
 */
@Data
@NoArgsConstructor
public class AutoGenerator {
    private static final Logger logger = LoggerFactory.getLogger(AutoGenerator.class);
    /**
     * 配置信息
     */
    protected ConfigBuilder config;

    /**
     * 数据源配置
     */
    private DataSourceConfig dataSource;
    /**
     * 数据库表配置
     */
    private StrategyConfig strategy;
    /**
     * 包 相关配置
     */
    private PackageConfig packageConfig;

    /**
     * 全局 相关配置
     */
    private GlobalConfig globalConfig;

    /**
     * 模板路径配置信息
     */
    private TemplateConfig template;

    /**
     * 生成代码
     */
    public void execute() {
        logger.debug("==========================准备生成文件...==========================");
        // 初始化配置
        if (null == config) {
            config = new ConfigBuilder(template, packageConfig, dataSource, strategy, globalConfig);
        }
        AbstractTemplateEngine templateEngine = new FreemarkerTemplateEngine();
        templateEngine.init(config).mkdirs().batchOutput().open();
        logger.debug("==========================文件生成完成！！！==========================");
    }
}
