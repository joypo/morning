package com.example.gen;

import com.example.gen.builder.ConfigBuilder;
import com.example.gen.config.DataSourceConfig;
import com.example.gen.config.StrategyConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author sunx
 * @date 2019-12-06
 * @description
 */
public abstract class AbstractGenerateMojo extends AbstractMojo {

    @Parameter
    private String outDir;

    @Parameter(required = true)
    private String packageName;

    @Parameter(required = true)
    private String module;

    @Parameter
    private String author;

    @Parameter(required = true)
    private StrategyConfig strategy;

    @Parameter(required = true)
    private DataSourceConfig dataSource;

    protected ConfigBuilder config;

    /**
     * 初始化配置
     */
    protected void initConfig() {
        if (StringUtils.isBlank(outDir)) {
            outDir = System.getProperty("user.dir");
        }
        if (null == config) {
            config = ConfigBuilder.builder().strategy(strategy).dataSource(dataSource).build();
        }
    }

    public String getOutDir() {
        return outDir;
    }

    public String getAuthor() {
        return author;
    }

    public DataSourceConfig getDataSource() {
        return dataSource;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getModule() {
        return module;
    }
}
