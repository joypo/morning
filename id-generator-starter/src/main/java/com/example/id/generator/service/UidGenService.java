package com.example.id.generator.service;

import com.example.id.generator.UidGenerator;
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
