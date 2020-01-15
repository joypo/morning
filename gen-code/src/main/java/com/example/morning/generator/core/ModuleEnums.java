package com.example.morning.generator.core;


import com.example.morning.generator.config.PackageConfig;

/**
 * @author sunx
 * @date 2020-01-14
 */
public enum ModuleEnums {
    /**
     *
     */
    CONFIGURATION(new PackageConfig()
            .setEntity(String.format("com.biz.dap.model.%s.entity", "configuration"))
            .setVo(String.format("com.biz.dap.model.%s.vo", "configuration"))
            .setMapper(String.format("com.biz.dap.service.%s.mapper", "configuration"))
            .setService(String.format("com.biz.dap.service.%s.service", "configuration"))
            .setServiceImpl(String.format("com.biz.dap.service.%s.service.impl", "configuration"))
            .setApiController(String.format("com.biz.dap.service.%s.controller", "configuration"))
            .setVoMapper(String.format("com.biz.dap.model.%s.vo.mapper", "configuration"))
            .setController(String.format("com.biz.dap.main.controller.%s", "configuration"))
            .setFeign(String.format("com.biz.dap.main.feign.%s", "configuration"))
            .setFeignDegraded(String.format("com.biz.dap.main.feign.degraded.%s", "configuration"))
    ),

    DATASOURCE(new PackageConfig()
            .setEntity(String.format("com.biz.dap.model.%s.entity", "datasource"))
            .setVo(String.format("com.biz.dap.model.%s.vo", "datasource"))
            .setMapper(String.format("com.biz.dap.service.%s.mapper", "datasource"))
            .setService(String.format("com.biz.dap.service.%s.service", "datasource"))
            .setServiceImpl(String.format("com.biz.dap.service.%s.service.impl", "datasource"))
            .setApiController(String.format("com.biz.dap.service.%s.controller", "datasource"))
            .setVoMapper(String.format("com.biz.dap.model.%s.vo.mapper", "datasource"))
            .setController(String.format("com.biz.dap.main.controller.%s", "datasource"))
            .setFeign(String.format("com.biz.dap.main.feign.%s", "datasource"))
            .setFeignDegraded(String.format("com.biz.dap.main.feign.degraded.%s", "datasource"))
    ),

    GROUP(new PackageConfig()
            .setEntity(String.format("com.biz.dap.model.%s.entity", "group"))
            .setVo(String.format("com.biz.dap.model.%s.vo", "group"))
            .setMapper(String.format("com.biz.dap.service.%s.mapper", "group"))
            .setService(String.format("com.biz.dap.service.%s.service", "group"))
            .setServiceImpl(String.format("com.biz.dap.service.%s.service.impl", "group"))
            .setApiController(String.format("com.biz.dap.service.%s.controller", "group"))
            .setVoMapper(String.format("com.biz.dap.model.%s.vo.mapper", "group"))
            .setController(String.format("com.biz.dap.main.controller.%s", "group"))
            .setFeign(String.format("com.biz.dap.main.feign.%s", "group"))
            .setFeignDegraded(String.format("com.biz.dap.main.feign.degraded.%s", "group"))
    ),

    TAG(new PackageConfig()
            .setEntity(String.format("com.biz.dap.model.%s.entity", "tag"))
            .setVo(String.format("com.biz.dap.model.%s.vo", "tag"))
            .setMapper(String.format("com.biz.dap.service.%s.mapper", "tag"))
            .setService(String.format("com.biz.dap.service.%s.service", "tag"))
            .setServiceImpl(String.format("com.biz.dap.service.%s.service.impl", "tag"))
            .setApiController(String.format("com.biz.dap.service.%s.controller", "tag"))
            .setVoMapper(String.format("com.biz.dap.model.%s.vo.mapper", "tag"))
            .setController(String.format("com.biz.dap.main.controller.%s", "tag"))
            .setFeign(String.format("com.biz.dap.main.feign.%s", "tag"))
            .setFeignDegraded(String.format("com.biz.dap.main.feign.degraded.%s", "tag"))
    ),
    ;

    private PackageConfig packageConfig;

    ModuleEnums(PackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }

    public PackageConfig getPackageConfig() {
        return packageConfig;
    }

    public void setPackageConfig(PackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }
}
