package com.baidu.fsg.uid.config;

import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
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
@MapperScan({"com.baidu.fsg.uid.worker.dao"})
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
