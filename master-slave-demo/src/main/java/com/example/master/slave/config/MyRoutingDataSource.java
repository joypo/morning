package com.example.master.slave.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.validation.constraints.NotNull;

/**
 * @author sunx
 * @date 2019-12-20
 * @description
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @NotNull
    @Override
    protected Object determineCurrentLookupKey(){
        return DBContextHolder.get();
    }
}
