package com.example.morning.generator.core;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sunx
 * @date 2019-08-23
 * @description 代码常量
 */
public final class GenConstant {

    //******************************************************************************************************************
    /**
     * 项目在硬盘上的基础路径 可以手动配置
     * 生成代码存放的路径********
     */
    public static final String PROJECT_PATH = System.getProperty("user.dir") + "/morning-demo";

    /**
     * 生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）
     * 生成项目的包名********
     */
    public static final String BASE_PACKAGE = "com.example.morning.demo";
    /**
     * @author 操作人********
     */
    public static final String AUTHOR = "sunx";

    /**
     * JDBC配置，请修改为你项目的实际配置
     */
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/demo1";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "";
    //******************************************************************************************************************

    /**
     * 生成的Model所在包
     */
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".domain";
    /**
     * 生成的Mapper所在包
     */
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";
    /**
     * 生成的Service所在包
     */
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";
    /**
     * 生成的ServiceImpl所在包
     */
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
    /**
     * 生成的Controller所在包
     */
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";
    /**
     * Mapper插件基础接口的完全限定名
     */
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";

    /**
     * @date
     */
    public static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    public static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    /**
     * 模板位置 路径
     */
    public static final String TEMPLATE_FILE_PATH = System.getProperty("user.dir") + "/gen-code" + "/src/main/resources/generator/template";

    public static final String JAVA_PATH = "/src/main/java";
    public static final String RESOURCES_PATH = "/src/main/resources";

    /**
     * 生成的entity存放路径
     */
    public static final String PACKAGE_PATH_ENTITY = packageConvertPath(MODEL_PACKAGE);

    /**
     * 生成的vo存放路径
     */
    public static final String PACKAGE_PATH_VO = packageConvertPath(MODEL_PACKAGE+".vo");

    /**
     * 生成的searchvo存放路径
     */
    public static final String PACKAGE_PATH_SEARCH_VO = packageConvertPath(MODEL_PACKAGE+".search");

    public static final String PACKAGE_PATH_DAO = packageConvertPath(MODEL_PACKAGE+".dao");

    public static final String PACKAGE_PATH_MAPPER_XML = packageConvertPath("mapper");

    /**
     * 生成的Service存放路径
     */
    public static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);
    /**
     * 生成的Service实现存放路径
     */
    public static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);
    /**
     * 生成的Controller存放路径
     */
    public static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);

//    public static final String ENTITY_TEMPLATE = "entity.ftl";
//    public static final String MAPPER_TEMPLATE = "mapper.ftl";
//    public static final String DAO_TEMPLATE = "dao.ftl";
//    public static final String SERVICE_TEMPLATE = "service.ftl";
//    public static final String SERVICE_IMPL_TEMPLATE = "service-impl.ftl";
//    public static final String CONTROLLER_TEMPLATE = "controller.ftl";

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}
