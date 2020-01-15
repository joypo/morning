package com.example.morning.generator.util;

import com.example.morning.generator.core.CamelPrefixType;
import com.example.morning.generator.core.ConstVal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunx
 * @date 2020-01-08
 */
public class StringUtils {

    private StringUtils() {
    }

    /**
     * 驼峰转换
     *
     * @param value
     * @param type  0 小写 1  大写
     */
    public static String caseFormat(String value, CamelPrefixType type) {
        if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
            return value;
        }
        value = value.toLowerCase();
        String[] arr = value.split(ConstVal.UNDERLINE);
        List<String> list = new ArrayList<>();
        for (String item : arr) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(item)) {
                list.add(item);
            }
        }
        String pre = list.get(0);
        if (CamelPrefixType.UPPER == type) {
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

    /**
     * 去除前缀
     *
     * @param str
     * @param prefix
     * @return
     */
    public static String removePrefix(String str, String[] prefix) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
            return "";
        } else {
            if (null != prefix) {
                String[] prefixArray = prefix;
                for (int i = 0; i < prefix.length; ++i) {
                    String pf = prefixArray[i];
                    if (str.toLowerCase().matches("^" + pf.toLowerCase() + ".*")) {
                        //截取前缀后面的字符串
                        return str.substring(pf.length());
                    }
                }
            }
            return str;
        }
    }

    private static String initialCapital(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

}
