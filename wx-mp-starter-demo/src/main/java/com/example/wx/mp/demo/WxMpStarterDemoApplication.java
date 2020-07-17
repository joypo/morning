package com.example.wx.mp.demo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xs
 */
@SpringBootApplication
@EnableSwagger2Doc
public class WxMpStarterDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxMpStarterDemoApplication.class, args);
    }
}
