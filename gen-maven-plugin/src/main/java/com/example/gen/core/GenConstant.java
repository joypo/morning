package com.example.gen.core;

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
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    /**
     * 生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）
     * 生成项目的包名********
     */
    public static final String BASE_PACKAGE = "com.biz.primus.ms.tsa";
    public static final String BASE_DAO_PACKAGE = "com.biz.primus.ms.tsa";
    public static final String BASE_MODEL_PACKAGE = "com.biz.primus.model.tsa.vo";
    /**
     * @author 操作人********
     */
    public static final String AUTHOR = "sunx";

    /**
     * 生成的Model所在包
     */
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".dao.entity";

    /**
     * 生成的vo所在包
     */
    public static final String VO_PACKAGE = "com.biz.primus.model.tsa.vo";

    /**
     * 生成的Mapper所在包
     */
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao.mapper";
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
     * @date
     */
    public static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    /**
     * 模板位置 路径
     */
//    public static final String TEMPLATE_FILE_PATH = System.getProperty("user.dir") + "/gen" + "/src/main/resources/generator/template";
    public static final String TEMPLATE_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/template";

    public static final String JAVA_PATH = "/src/main/java";
    public static final String RESOURCES_PATH = "/src/main/resources";

    /**
     * 生成的entity存放路径
     */
    public static final String PACKAGE_PATH_ENTITY = packageConvertPath(MODEL_PACKAGE);

    /**
     * 生成的vo存放路径
     */
    public static final String PACKAGE_PATH_VO = packageConvertPath(VO_PACKAGE);

    /**
     * 生成的searchvo存放路径
     */
    public static final String PACKAGE_PATH_SEARCH_VO = packageConvertPath(VO_PACKAGE + ".search");

    public static final String PACKAGE_PATH_DAO = packageConvertPath(MAPPER_PACKAGE);

    public static final String PACKAGE_PATH_MAPPER_XML = packageConvertPath(MAPPER_PACKAGE + ".xml");

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

//    public static final String ENTITY_TEMPLATE = "jpa-entity.ftl";
//    public static final String MAPPER_TEMPLATE = "mapper.ftl";
//    public static final String DAO_TEMPLATE = "dao.ftl";
//    public static final String SERVICE_TEMPLATE = "service.ftl";
//    public static final String SERVICE_IMPL_TEMPLATE = "service-impl.ftl";
//    public static final String CONTROLLER_TEMPLATE = "manage-controller.ftl";

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}
