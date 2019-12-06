package com.example.genx.run;

import com.example.genx.builder.GenBuilder;
import com.example.genx.core.GenConstant;
import com.example.genx.core.GlobalConstant;
import com.example.genx.po.GenModel;
import com.example.genx.po.TableInfo;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author sunx
 * @date 2019-08-23
 * @description 代码生成
 */
public class CodeGenerator {

    public static void main(String[] args) {
        /**
         * 模块
         */
        GlobalConstant.Module = "interactive";
        StrategyConfig strategy = StrategyConfig.builder().include(Sets.newHashSet("tb_interactive_question_scope")).build();
        GenBuilder genBuilder = GenBuilder.builder().strategy(strategy).build();
        List<TableInfo> list = genBuilder.getTableInfoList();
        CodeGenerator generator = new CodeGenerator();
        try {
            generator.genCode(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("出错了");
        }
    }

    private void genCode(List<TableInfo> list) throws Exception {
        freemarker.template.Configuration cfg = getConfiguration();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (TableInfo item : list) {
            genCodeByTable(item, cfg);
        }
    }

    private void genCodeByTable(TableInfo tableInfo, freemarker.template.Configuration cfg) throws Exception {
        GenModel genModel = new GenModel(tableInfo, getBindData(tableInfo), tableNameConvertUpperCamel(tableInfo.getName()), cfg);
        genDao(genModel);
        genModel(genModel);
        genService(genModel);
        genManagement(genModel);
    }

    private void genDao(GenModel genModel) {
        try {
            File entityFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.DAO_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.ENTITY_PACKAGE_PATH + genModel.getModelNameUpperCamel() + ".java");
            fileGenerator(entityFile, genModel, GlobalConstant.ENTITY_TEMPLATE, ".java");

            File mapperFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.DAO_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.MAPPER_PACKAGE_PATH + genModel.getModelNameUpperCamel() + "Mapper.java");
            fileGenerator(mapperFile, genModel, GlobalConstant.DAO_TEMPLATE, "Mapper.java");

            File mapperXmlFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.DAO_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.MAPPER_XML_PATH + genModel.getModelNameUpperCamel() + "Mapper.xml");
            fileGenerator(mapperXmlFile, genModel, GlobalConstant.MAPPER_TEMPLATE, "Mapper.xml");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(genModel.getModelNameUpperCamel() + "生成DAO失败", e);
        }
    }

    private void genModel(GenModel genModel) {
        try {
            File voFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.MODEL_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.VO_PACKAGE_PATH + genModel.getModelNameUpperCamel() + "Vo.java");
            fileGenerator(voFile, genModel, GlobalConstant.VO_TEMPLATE, "Vo.java");

            File reqVoFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.MODEL_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.REQ_VO_PACKAGE_PATH + genModel.getModelNameUpperCamel() + "ReqVo.java");
            fileGenerator(reqVoFile, genModel, GlobalConstant.REQ_VO_TEMPLATE, "ReqVo.java");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(genModel.getModelNameUpperCamel() + "生成VO失败", e);
        }
    }

    private void genService(GenModel genModel) {
        try {
            File serviceFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.SERVICE_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.SERVICE_PACKAGE_PATH + genModel.getModelNameUpperCamel() + "Service.java");
            fileGenerator(serviceFile, genModel, GlobalConstant.SERVICE_TEMPLATE, "Service.java");

            File serviceImplFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.SERVICE_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.SERVICE_IMPL_PACKAGE_PATH + genModel.getModelNameUpperCamel() + "ServiceImpl.java");
            fileGenerator(serviceImplFile, genModel, GlobalConstant.SERVICE_IMPL_TEMPLATE, "ServiceImpl.java");

            File serviceApiFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.SERVICE_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.SERVICE_API_PACKAGE_PATH + genModel.getModelNameUpperCamel() + "ApiController.java");
            fileGenerator(serviceApiFile, genModel, GlobalConstant.SERVICE_API_TEMPLATE, "ApiController.java");

            File serviceRestApiFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.SERVICE_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.SERVICE_REST_PACKAGE_PATH + genModel.getModelNameUpperCamel() + "RestApiController.java");
            fileGenerator(serviceRestApiFile, genModel, GlobalConstant.SERVICE_REST_TEMPLATE, "RestApiController.java");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(genModel.getModelNameUpperCamel() + "生成Service失败", e);
        }
    }

    private void genManagement(GenModel genModel) {
        try {
            File feignFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.MANAGEMENT_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.MANAGE_FEIGN_PACKAGE_PATH + genModel.getModelNameUpperCamel() + "FeignClient.java");
            fileGenerator(feignFile, genModel, GlobalConstant.MANAGEMENT_FEIGN_TEMPLATE, "FeignClient.java");

            File degradedFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.MANAGEMENT_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.MANAGE_FEIGN_DEGRADED_PACKAGE_PATH + genModel.getModelNameUpperCamel() + "FeignClientDegraded.java");
            fileGenerator(degradedFile, genModel, GlobalConstant.MANAGEMENT_DEGRADED_TEMPLATE, "FeignClientDegraded.java");

            File controllerFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.MANAGEMENT_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.MANAGE_CONTROLLER_PACKAGE_PATH + genModel.getModelNameUpperCamel() + "Controller.java");
            fileGenerator(controllerFile, genModel, GlobalConstant.MANAGEMENT_CONTROLLER_TEMPLATE, "Controller.java");

            File webListFile = new File(GlobalConstant.PROJECT_PATH + GlobalConstant.MANAGEMENT_PATH + GlobalConstant.WEB_PATH + "/" + GlobalConstant.Module + "/" + genModel.getData().get("modelNameLowerCamel") + "/" + "list.ftl");
            fileGenerator(webListFile, genModel, GlobalConstant.WEB_LIST_TEMPLATE, "list.ftl");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(genModel.getModelNameUpperCamel() + "生成Management失败", e);
        }
    }

    private Map<String, Object> getBindData(TableInfo tableInfo) {
        Map<String, Object> data = Maps.newHashMap();
        data.put("date", GenConstant.DATE);
        data.put("author", GenConstant.AUTHOR);
        data.put("desc", StringUtils.isNotBlank(tableInfo.getComment()) ? tableInfo.getComment() : "");
        String modelNameUpperCamel = tableNameConvertUpperCamel(tableInfo.getName());
        data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
        data.put("modelNameUpperCamel", modelNameUpperCamel);
        data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));

        data.put("module", GlobalConstant.Module);

        data.put("table", tableInfo);
        ///
        data.put("entityPackage", GlobalConstant.ENTITY_PACKAGE);
        data.put("mapperPackage", GlobalConstant.MAPPER_PACKAGE);

        data.put("voPackage", GlobalConstant.VO_PACKAGE);
        data.put("reqVoPackage", GlobalConstant.REQ_VO_PACKAGE);

        data.put("servicePackage", GlobalConstant.SERVICE_PACKAGE);
        data.put("serviceImplPackage", GlobalConstant.SERVICE_IMPL_PACKAGE);
        data.put("serviceApiPackage", GlobalConstant.SERVICE_API_PACKAGE);
        data.put("serviceRestPackage", GlobalConstant.SERVICE_REST_PACKAGE);


        data.put("feignPackage", GlobalConstant.MANAGE_FEIGN_PACKAGE);
        data.put("degradedPackage", GlobalConstant.MANAGE_FEIGN_DEGRADED_PACKAGE);
        data.put("controllerPackage", GlobalConstant.MANAGE_CONTROLLER_PACKAGE);

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
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, formatterTableName(tableName).toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, formatterTableName(tableName).toLowerCase());
    }

    private static String tableNameConvertMappingPath(String tableName) {
        //兼容使用大写的表名
        tableName = formatterTableName(tableName).toLowerCase();
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String formatterTableName(String name) {
        if (name.indexOf("tb_interactive_") == 0) {
            name = name.substring(15, name.length());
        }
        return name;
    }

    /**
     * 文件生成
     */
    private static void fileGenerator(File file, GenModel genModel, String template, String suffix) throws Exception {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            System.out.println(genModel.getModelNameUpperCamel() + suffix + "已存在0");
            file.delete();
            System.out.println(genModel.getModelNameUpperCamel() + suffix + "删除成功");
        }
        genModel.getCfg().getTemplate(template).process(genModel.getData(), new FileWriter(file));
        System.out.println(genModel.getModelNameUpperCamel() + suffix + "生成成功");
    }
}
