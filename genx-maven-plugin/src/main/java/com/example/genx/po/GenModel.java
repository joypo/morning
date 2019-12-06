package com.example.genx.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author sunx
 * @date 2019-11-12
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenModel {
    private TableInfo tableInfo;
    private Map<String, Object> data;
    private String modelNameUpperCamel;
    private freemarker.template.Configuration cfg;
}
