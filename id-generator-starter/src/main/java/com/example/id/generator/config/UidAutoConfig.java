package com.example.id.generator.config;

import com.example.id.generator.impl.CachedUidGenerator;
import com.example.id.generator.impl.DefaultUidGenerator;
import com.example.id.generator.worker.DisposableWorkerIdAssigner;
import com.example.id.generator.worker.WorkerIdAssigner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author sunx
 * @date 2019-09-07
 * @description
 */
@Configuration
@ConditionalOnClass({DefaultUidGenerator.class, CachedUidGenerator.class})
@MapperScan({"com.example.id.generator.worker.dao"})
@EnableConfigurationProperties(UidProperties.class)
public class UidAutoConfig {

    @Autowired
    private UidProperties uidProperties;

    @Bean
    @ConditionalOnMissingBean
    @Lazy
    DefaultUidGenerator defaultUidGenerator() {
        return new DefaultUidGenerator(uidProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @Lazy
    CachedUidGenerator cachedUidGenerator() {
        return new CachedUidGenerator(uidProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    WorkerIdAssigner workerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }
}
