package com.example.genx;

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

    /**
     * 初始化配置
     */
    protected void initConfig() {

    }
}
