package com.example.master.slave.config;

import com.example.master.slave.enums.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunx
 * @date 2019-12-20
 * @description
 */
public class DBContextHolder {
    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();
    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum type) {
        contextHolder.set(type);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        System.out.println("已切换到master");
    }

    public static void slave() {
        //轮询
//        int index = counter.getAndIncrement() % 2;
//        if (counter.get() > 999) {
//            counter.set(-1);
//        }
//
//        if (index == 0) {
//
//        } else {
//
//        }

        set(DBTypeEnum.SLAVE);
        System.out.println("已切换slave");
    }
}
