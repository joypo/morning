package com.example.demo.drools;

import com.example.swagger2.starter.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.drools.dao"})
@EnableSwagger2Doc
public class DemoDroolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDroolsApplication.class, args);
    }

}
