package com.example.morning.demo.config.durid;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.morning.demo.config.durid.properties.DruidProperties;
import com.example.morning.demo.exception.BizRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;

/**
 * @author sunx
 * @date 2019-08-21
 * @description
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@EnableConfigurationProperties({DruidProperties.class})
@Import({
        DruidStatViewServletConfig.class,
        DruidWebStatFilterConfig.class
})
public class DruidConfig {
    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource(DruidProperties properties) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(properties.getUrl());
        datasource.setUsername(properties.getUsername());
        datasource.setPassword(properties.getPassword());
        if (properties.getConnectionInitSqls() != null) {
            datasource.setConnectionInitSqls(Collections.singleton(properties.getConnectionInitSqls()));
        }
        if (properties.getInitialSize() != null) {
            datasource.setInitialSize(properties.getInitialSize());
        }
        if (properties.getMinIdle() != null) {
            datasource.setMinIdle(properties.getMinIdle());
        }
        if (properties.getMaxWait() != null) {
            datasource.setMaxWait(properties.getMaxWait());
        }
        if (properties.getMaxActive() != null) {
            datasource.setMaxActive(properties.getMaxActive());
        }
        if (properties.getMinEvictableIdleTimeMillis() != null) {
            datasource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        }
        if (properties.getTimeBetweenEvictionRunsMillis() != null) {
            datasource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        }
        if (StringUtils.isNotBlank(properties.getFilters())) {
            try {
                datasource.setFilters(properties.getFilters());
            } catch (SQLException e) {
                throw new BizRuntimeException(e);
            }
        }
        return datasource;
    }
}
