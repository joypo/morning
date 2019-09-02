package com.example.morning.generator;

import com.example.morning.generator.builder.GenBuilder;
import com.example.morning.generator.core.GenConstant;
import com.example.morning.generator.po.TableInfo;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunx
 * @date 2019-08-23
 * @description 代码生成
 */
public class CodeGenerator {

    public static void main(String[] args) {
        StrategyConfig strategy = StrategyConfig.builder().include(Sets.newHashSet("auth_user")).build();
        GenBuilder genBuilder = GenBuilder.builder().strategy(strategy).build();
        List<TableInfo> list = genBuilder.getTableInfoList();
        CodeGenerator generator = new CodeGenerator();
        generator.genCode(list);
    }

    private void genCode(List<TableInfo> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (TableInfo item : list) {
            genCodeByTable(item);
        }
    }


    private void genCodeByTable(TableInfo tableInfo) {
        genDaoAndMapperXmlAndPojo(tableInfo);
        genService(tableInfo);
        genController(tableInfo);
    }


    private void genDaoAndMapperXmlAndPojo(TableInfo tableInfo) {
        try {
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableInfo.getName());
            freemarker.template.Configuration cfg = getConfiguration();
            Map<String, Object> data = getBindData(tableInfo);

            File file2 = new File(GenConstant.PROJECT_PATH + GenConstant.JAVA_PATH + GenConstant.PACKAGE_PATH_ENTITY + modelNameUpperCamel + ".java");
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (!file2.exists()) {
                cfg.getTemplate("entity.ftl").process(data, new FileWriter(file2));
                System.out.println(modelNameUpperCamel + ".java 生成成功");
            } else {
                System.out.println(modelNameUpperCamel + ".java 已存在0");
            }

            File file = new File(GenConstant.PROJECT_PATH + GenConstant.JAVA_PATH + GenConstant.PACKAGE_PATH_DAO + modelNameUpperCamel + "Dao.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                cfg.getTemplate("dao.ftl").process(data, new FileWriter(file));
                System.out.println(modelNameUpperCamel + "Dao.java 生成成功1");
            } else {
                System.out.println(modelNameUpperCamel + "Dao.java 已存在0");
            }


            File file1 = new File(GenConstant.PROJECT_PATH + GenConstant.RESOURCES_PATH + GenConstant.PACKAGE_PATH_MAPPER_XML + modelNameUpperCamel + "Dao.xml");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            if (!file1.exists()) {
                cfg.getTemplate("mapper-xml.ftl").process(data, new FileWriter(file1));
                System.out.println(modelNameUpperCamel + "Dao.xml 生成成功1");
            } else {
                System.out.println(modelNameUpperCamel + "Dao.xml 已存在0");
            }


            File file3 = new File(GenConstant.PROJECT_PATH + GenConstant.JAVA_PATH + GenConstant.PACKAGE_PATH_VO + modelNameUpperCamel + "VO.java");
            if (!file3.getParentFile().exists()) {
                file3.getParentFile().mkdirs();
            }
            if (!file3.exists()) {
                cfg.getTemplate("vo.ftl").process(data, new FileWriter(file3));
                System.out.println(modelNameUpperCamel + "VO.java 生成成功1");
            } else {
                System.out.println(modelNameUpperCamel + "VO.java 已存在0");
            }


            File file4 = new File(GenConstant.PROJECT_PATH + GenConstant.JAVA_PATH + GenConstant.PACKAGE_PATH_SEARCH_VO + modelNameUpperCamel + "SearchVO.java");
            if (!file4.getParentFile().exists()) {
                file4.getParentFile().mkdirs();
            }
            if (!file4.exists()) {
                cfg.getTemplate("search-vo.ftl").process(data, new FileWriter(file4));
                System.out.println(modelNameUpperCamel + "SearchVO.java 生成成功");
            } else {
                System.out.println(modelNameUpperCamel + "SearchVO.java 已存在0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成SearchVO失败", e);
        }
    }

    public void genService(TableInfo tableInfo) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableInfo.getName());
            Map<String, Object> data = getBindData(tableInfo);

            File file = new File(GenConstant.PROJECT_PATH + GenConstant.JAVA_PATH + GenConstant.PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                cfg.getTemplate("service.ftl").process(data,
                        new FileWriter(file));
                System.out.println(tableNameConvertUpperCamel(tableInfo.getName()) + "Service.java 生成成功1");
            } else {
                System.out.println(tableNameConvertUpperCamel(tableInfo.getName()) + "Service.java 已存在0");
            }


            File file1 = new File(GenConstant.PROJECT_PATH + GenConstant.JAVA_PATH + GenConstant.PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            if (!file1.exists()) {
                cfg.getTemplate("service-impl.ftl").process(data,
                        new FileWriter(file1));
                System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功1");
            } else {
                System.out.println(modelNameUpperCamel + "ServiceImpl.java 已存在0");
            }
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    private void genController(TableInfo tableInfo) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();
            Map<String, Object> data = getBindData(tableInfo);
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableInfo.getName());

            File file = new File(GenConstant.PROJECT_PATH + GenConstant.JAVA_PATH + GenConstant.PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                System.out.println(modelNameUpperCamel + "Controller.java 已存在0");
                return;
            }
            cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private Map<String, Object> getBindData(TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("date", GenConstant.DATE);
        data.put("author", GenConstant.AUTHOR);
        data.put("desc", StringUtils.isNotBlank(tableInfo.getComment()) ? tableInfo.getComment() : "");
        String modelNameUpperCamel = tableNameConvertUpperCamel(tableInfo.getName());
        data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
        data.put("modelNameUpperCamel", modelNameUpperCamel);
        data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
        data.put("basePackage", GenConstant.BASE_PACKAGE);
        data.put("table", tableInfo);
        return data;
    }

    private freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(GenConstant.TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }


    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertMappingPath(String tableName) {
        //兼容使用大写的表名
        tableName = tableName.toLowerCase();
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }


}
