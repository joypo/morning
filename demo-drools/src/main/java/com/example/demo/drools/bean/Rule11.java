package com.example.demo.drools.bean;

import java.util.Map;

/**
 * @author sunx
 * @date 2020-01-17
 */
public class Rule11 {

    private Integer ruleId;

    private String id;

    private Map<String, Object> map;

    public Rule11() {
    }

    public Rule11(Integer ruleId, String id, Map<String, Object> map) {
        this.ruleId = ruleId;
        this.id = id;
        this.map = map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }
}
