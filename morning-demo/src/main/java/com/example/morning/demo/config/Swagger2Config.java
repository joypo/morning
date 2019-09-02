package com.example.morning.demo.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunx
 * @date 2019-08-16
 * @description 基本的swagger2配置 使用的是BootstrapUI
 * 链接地址变为  /doc.html
 * springfox-swagger-ui 地址为 /swagger-ui.html
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@Profile({"!release", "!master", "!pro"})
@Slf4j
public class Swagger2Config {
    /**
     * 添加公共参数
     *
     * @return
     */
    @Bean
    public Docket createDefaultRestApi() {
        ParameterBuilder builder = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        builder.name("access_token")
                .description("token信息")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(builder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(defaultApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.morning.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }


    private ApiInfo defaultApiInfo() {
        return new ApiInfoBuilder()
                .title("Api Docs")
                .version("1.0.0")
                .build();
    }


    /**
     * 系统管理
     *
     * @return
     */
    @Bean
    public Docket createSystemRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("system")
                .apiInfo(systemApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.morning.demo.controller.system"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo systemApiInfo() {
        return new ApiInfoBuilder()
                .title("System Api Docs")
                .version("1.0.0")
                .build();
    }
}
