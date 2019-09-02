package com.example.morning.demo.config.durid;

import com.alibaba.druid.support.http.WebStatFilter;
import com.example.morning.demo.config.durid.properties.DruidProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

/**
 * @author sunx
 */
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "data.druid.web-stat-filter.enabled", havingValue = "true", matchIfMissing = true)
public class DruidWebStatFilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(DruidProperties properties) {
        DruidProperties.WebStatFilter config = properties.getWebStatFilter();
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        WebStatFilter filter = new WebStatFilter();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(config.getUrlPattern());
        registrationBean.addInitParameter("exclusions", config.getExclusions());
        if (config.getSessionStatEnable() != null) {
            registrationBean.addInitParameter("sessionStatEnable", config.getSessionStatEnable());
        }
        if (config.getSessionStatMaxCount() != null) {
            registrationBean.addInitParameter("sessionStatMaxCount", config.getSessionStatMaxCount());
        }
        if (config.getPrincipalSessionName() != null) {
            registrationBean.addInitParameter("principalSessionName", config.getPrincipalSessionName());
        }
        if (config.getPrincipalCookieName() != null) {
            registrationBean.addInitParameter("principalCookieName", config.getPrincipalCookieName());
        }
        if (config.getProfileEnable() != null) {
            registrationBean.addInitParameter("profileEnable", config.getProfileEnable());
        }
        return registrationBean;
    }
}
