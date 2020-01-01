package com.example.genx.config;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sunx
 * @date 2019-12-31
 * @description
 */
public class StrategyConfig {

    /**
     * 需要生成的表集合
     */
    private Set<String> include = null;
    /**
     * 是否包含  true  包含  false  过滤
     */
    private Boolean includeFlag = true;
    private String[] includeArray = null;

    public Set<String> getInclude() {
        if (null == includeArray) {
            return null;
        }
        Set<String> set = new HashSet<>();
        if (includeArray.length > 0) {
            for (String item : includeArray) {
                set.add(item);
            }
        }
        return set;
    }

    public Boolean getIncludeFlag() {
        return includeFlag;
    }
}
