package com.example.morning.generator;

import com.example.morning.generator.config.DataSourceConfig;
import com.example.morning.generator.config.GlobalConfig;
import com.example.morning.generator.config.PackageConfig;
import com.example.morning.generator.config.StrategyConfig;
import com.example.morning.generator.core.ModuleEnums;
import com.mysql.cj.jdbc.Driver;

/**
 * @author sunx
 * @date 2020-01-08
 */
public class GenCode {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        String dbUrl = "jdbc:mysql://localhost:3306/sys_code_gen?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
        // 数据库配置
        autoGenerator.setDataSource(new DataSourceConfig()
                .setUrl(dbUrl)
                .setDriverName(Driver.class.getName())
                .setUsername("root")
                .setPassword("root"));

        // 全局配置
        autoGenerator.setGlobalConfig(
                new GlobalConfig()
                        .setAuthor("sun")
                        .setSwagger2(true)
                        .setFileOverride(true)
                        .setEasyCopy(true)
                        // 设置生成feign  必须设置feignServiceName
                        .setFeign(true)
                        .setFeignServiceName("service-tag")
        );

        // 包配置
        autoGenerator.setPackageConfig(
                new PackageConfig()
                        .setEntity(ModuleEnums.TAG.getPackageConfig().getEntity())
                        .setMapper(ModuleEnums.TAG.getPackageConfig().getMapper())
                        .setXml(ModuleEnums.TAG.getPackageConfig().getXml())
                        .setService(ModuleEnums.TAG.getPackageConfig().getService())
                        .setServiceImpl(ModuleEnums.TAG.getPackageConfig().getServiceImpl())
                        .setApiController(ModuleEnums.TAG.getPackageConfig().getApiController())
                        .setController(ModuleEnums.TAG.getPackageConfig().getController())
                        .setFeign(ModuleEnums.TAG.getPackageConfig().getFeign())
                        .setFeignDegraded(ModuleEnums.TAG.getPackageConfig().getFeignDegraded())
                        .setVo(ModuleEnums.TAG.getPackageConfig().getVo())
                        .setVoMapper(ModuleEnums.TAG.getPackageConfig().getVoMapper())
        );

        // 策略配置
        autoGenerator.setStrategy(
                new StrategyConfig()
                        .setTablePrefix("tb_")
                        .setIncludeFlag(true)
                        .setInclude("dap_tag_category", "dap_tag")
                        .setExcludeFields("id", "create_user_id", "create_user_name", "create_time", "update_user_id", "update_user_name", "update_time")
                        .setSuperEntityClass("com.biz.dap.model.base.entity.BaseEntity")
        );
        autoGenerator.execute();
    }
}
