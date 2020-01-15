package com.example.morning.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sunx
 * @date 2020-01-08
 */
@Data
@Accessors(chain = true)
public class GlobalConfig {
    /**
     * 生成文件的输出目录【默认 D 盘根目录】
     */
    private String outputDir = System.getProperty("user.dir") + "/tmp";

    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride = false;

    /**
     * 是否打开输出目录
     */
    private boolean open = false;

    /**
     * 开发人员
     */
    private String author = "demo";

    /**
     * 开启 swagger2 模式
     */
    private boolean swagger2 = false;

    /**
     * 是否生成feign相关
     */
    private boolean feign = false;

    /**
     * 如需要生成feign需要配置服务名
     */
    private String feignServiceName = null;

    /**
     * 简单路径模式
     */
    private boolean easyCopy = false;
}
