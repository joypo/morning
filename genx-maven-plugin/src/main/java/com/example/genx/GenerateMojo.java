package com.example.genx;

import com.example.genx.po.TableInfo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.List;

/**
 * @author sunx
 * @date 2019-12-06
 * @description
 */
@Mojo(name = "code", threadSafe = true)
public class GenerateMojo extends AbstractGenerateMojo {

    private static String PREFIX = "";
    private static String PROJECT_PATH = "";
    private static String PACKAGE = "";
    private static String MODULE = "";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        initConfig();
        PREFIX = this.getDataSource().getPrefix();
        PROJECT_PATH = this.getOutDir();
        PACKAGE = this.getPackageName();
        MODULE = this.getModule();
        System.out.println("开始执行");
        List<TableInfo> list = config.getTableInfoList();
        try {
//            genCode(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("出错了");
        }
        System.out.println("执行结束");
    }

//    private void genCode(List<TableInfo> list) throws Exception {
//        freemarker.template.Configuration cfg = getConfiguration();
//        if (null == list || list.size() == 0) {
//            return;
//        }
//        for (TableInfo item : list) {
//            genCodeByTable(item, cfg);
//        }
//    }
//
//    private void genCodeByTable(TableInfo tableInfo, freemarker.template.Configuration cfg) throws Exception {
//        GenModel genModel = new GenModel(tableInfo, getBindData(tableInfo), tableNameConvertUpperCamel(tableInfo.getName()), cfg);
//        genEntity(genModel);
//    }
//
//    private void genEntity(GenModel genModel) {
//        try {
//            File entityFile = new File(PROJECT_PATH + GlobalConstant.JAVA_PATH + GlobalConstant.packageConvertPath(PACKAGE + ".entity." + MODULE) + genModel.getModelNameUpperCamel() + "Entity.java");
//            fileGenerator(entityFile, genModel, GlobalConstant.JPA_ENTITY_TEMPLATE, "Entity.java");
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(genModel.getModelNameUpperCamel() + "生成Entity失败", e);
//        }
//    }
//
//    private freemarker.template.Configuration getConfiguration() throws IOException {
//        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
//        cfg.setDirectoryForTemplateLoading(new File(GenConstant.TEMPLATE_FILE_PATH));
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
//        return cfg;
//    }
//
//    private Map<String, Object> getBindData(TableInfo tableInfo) {
//        Map<String, Object> data = new HashMap<>();
//        data.put("date", GenConstant.DATE);
//        data.put("author", this.getAuthor());
//        data.put("desc", StringUtils.isNotBlank(tableInfo.getComment()) ? tableInfo.getComment() : "");
//        String modelNameUpperCamel = tableNameConvertUpperCamel(tableInfo.getName());
//        data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
//        data.put("modelNameUpperCamel", modelNameUpperCamel);
//        data.put("modelNameLowerCamel", caseFormat(modelNameUpperCamel, 0));
//        data.put("module", GlobalConstant.Module);
//        data.put("table", tableInfo);
//        data.put("entityPackage", GlobalConstant.ENTITY_PACKAGE);
//        return data;
//    }
//
//    private static String tableNameConvertLowerCamel(String tableName) {
//        return caseFormat(formatterTableName(tableName), 0);
//    }
//
//    private static String tableNameConvertUpperCamel(String tableName) {
//        return caseFormat(formatterTableName(tableName), 1);
//    }
//
//    private static String modelNameConvertMappingPath(String modelName) {
//        String tableName = caseFormat(modelName, 1);
//        return tableNameConvertMappingPath(tableName);
//    }
//
//    /**
//     * 驼峰转换
//     *
//     * @param value
//     * @param type  0 小写 1  大写
//     */
//    private static String caseFormat(String value, int type) {
//        if (StringUtils.isBlank(value)) {
//            return value;
//        }
//        value = value.toLowerCase();
//        List<String> list = Stream.of(value.split("_")).filter(a -> StringUtils.isNotBlank(a)).collect(Collectors.toList());
//        String pre = list.get(0);
//        if (1 == type) {
//            pre = initialCapital(pre);
//        }
//        if (list.size() == 1) {
//            return pre;
//        } else {
//            StringBuilder sb = new StringBuilder();
//            sb.append(pre);
//            for (int i = 1; i < list.size(); i++) {
//                sb.append(initialCapital(list.get(i)));
//            }
//            return sb.toString();
//        }
//    }
//
//    private static String initialCapital(String value) {
//        return value.substring(0, 1).toUpperCase() + value.substring(1);
//    }
//
//    private static String formatterTableName(String name) {
//        if (name.indexOf(PREFIX) == 0) {
//            name = name.substring(PREFIX.length(), name.length());
//        }
//        return name;
//    }
//
//    private static String tableNameConvertMappingPath(String tableName) {
//        //兼容使用大写的表名
//        tableName = formatterTableName(tableName).toLowerCase();
//        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
//    }
//
//    /**
//     * 文件生成
//     */
//    private static void fileGenerator(File file, GenModel genModel, String template, String suffix) throws Exception {
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        if (file.exists()) {
//            System.out.println(genModel.getModelNameUpperCamel() + suffix + "已存在");
//            file.delete();
//            System.out.println(genModel.getModelNameUpperCamel() + suffix + "删除成功");
//        }
//        genModel.getCfg().getTemplate(template).process(genModel.getData(), new FileWriter(file));
//        System.out.println(genModel.getModelNameUpperCamel() + suffix + "生成成功");
//    }
}
