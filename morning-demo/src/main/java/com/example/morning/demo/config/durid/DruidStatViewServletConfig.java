package com.example.morning.demo.config.durid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.example.morning.demo.config.durid.properties.DruidProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServlet;

/**
 * @author sunx
 */
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "data.druid.stat-view-servlet.enabled", havingValue = "true", matchIfMissing = true)
public class DruidStatViewServletConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean(DruidProperties properties) {
        DruidProperties.StatViewServlet config = properties.getStatViewServlet();
        ServletRegistrationBean<HttpServlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.addUrlMappings(config.getUrlPattern());
        if (config.getAllow() != null) {
            registrationBean.addInitParameter("allow", config.getAllow());
        }
        if (config.getDeny() != null) {
            registrationBean.addInitParameter("deny", config.getDeny());
        }
        if (config.getLoginUsername() != null) {
            registrationBean.addInitParameter("loginUsername", config.getLoginUsername());
        }
        if (config.getLoginPassword() != null) {
            registrationBean.addInitParameter("loginPassword", config.getLoginPassword());
        }
        if (config.getResetEnable() != null) {
            registrationBean.addInitParameter("resetEnable", config.getResetEnable());
        }
        return registrationBean;
    }
}
