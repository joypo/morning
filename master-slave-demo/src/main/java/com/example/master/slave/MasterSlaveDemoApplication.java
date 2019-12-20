package com.example.master.slave;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.master.slave.dao")
public class MasterSlaveDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterSlaveDemoApplication.class, args);
    }

}
