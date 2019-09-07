package com.baidu.fsg.uid.service;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sunx
 * @date 2019-09-07
 * @description
 */
@Service
public class UidGenService {
    @Resource
    private UidGenerator uidGenerator;

    public long getUid() {
        return uidGenerator.getUID();
    }
}
