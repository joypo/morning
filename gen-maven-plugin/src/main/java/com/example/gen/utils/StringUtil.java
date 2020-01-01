package com.example.gen.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunx
 * @date 2020-01-01
 * @description
 */
public class StringUtil {
    /**
     * 驼峰转换
     *
     * @param value
     * @param type  0 小写 1  大写
     */
    public static String caseFormat(String value, int type) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        value = value.toLowerCase();
        String[] arr = value.split("_");
        List<String> list = new ArrayList<>();
        for (String item : arr) {
            if (StringUtils.isNotBlank(item)) {
                list.add(item);
            }
        }
        String pre = list.get(0);
        if (1 == type) {
            pre = initialCapital(pre);
        }
        if (list.size() == 1) {
            return pre;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(pre);
            for (int i = 1; i < list.size(); i++) {
                sb.append(initialCapital(list.get(i)));
            }
            return sb.toString();
        }
    }

    private static String initialCapital(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
