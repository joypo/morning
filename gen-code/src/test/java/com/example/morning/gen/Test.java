package com.example.morning.gen;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sunx
 * @date 2019-09-12
 * @description
 */
public class Test {
    public static void main(String[] args) {
//        String k = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_CAMEL, "user_name");
//        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
//        System.out.println(System.getProperty("user.dir"));


        String s1 = "_zhangsdsd_ksks";

        String kk = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, s1);
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "_test_data"));
        System.out.println(caseFormat(s1,0));

    }

    private static String caseFormat(String value, int type) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        List<String> list = Stream.of(value.split("_")).filter(a -> StringUtils.isNotBlank(a)).collect(Collectors.toList());
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
