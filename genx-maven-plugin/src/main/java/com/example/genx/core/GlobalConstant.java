package com.example.genx.core;

/**
 * @author sunx
 * @date 2019-11-12
 * @description
 */
public final class GlobalConstant {
    /**
     * 项目在硬盘上的基础路径 可以手动配置
     * 生成代码存放的路径********
     */
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static String Module = "system";

    /**
     * java的默认前缀包
     */
    public static final String JAVA_PATH = "/src/main/java";

    public static final String WEB_PATH = "/src/main/resources/templates";


    public static final String DAO_PATH = "/dao";
    public static final String MODEL_PATH = "/model";
    public static final String SERVICE_PATH = "/service";
    public static final String MANAGEMENT_PATH = "/management";

    /**************dao***************/
    public static final String DAO_BASE_PACKAGE = "com.biz.primus.ms.csm.dao";
    public static final String ENTITY_PACKAGE = DAO_BASE_PACKAGE + ".entity";
    public static final String ENTITY_PACKAGE_PATH = packageConvertPath(ENTITY_PACKAGE);
    public static final String MAPPER_PACKAGE = DAO_BASE_PACKAGE + ".mapper";
    public static final String MAPPER_PACKAGE_PATH = packageConvertPath(MAPPER_PACKAGE);
    public static final String MAPPER_XML_PATH = packageConvertPath(MAPPER_PACKAGE + ".xml");


    /**************model*************/
    public static final String MODEL_BASE_PACKAGE = "com.biz.primus.model.csm";
    public static final String VO_PACKAGE = MODEL_BASE_PACKAGE + ".vo";
    public static final String VO_PACKAGE_PATH = packageConvertPath(VO_PACKAGE);
    public static final String REQ_VO_PACKAGE = VO_PACKAGE + ".req";
    public static final String REQ_VO_PACKAGE_PATH = packageConvertPath(REQ_VO_PACKAGE);


    /**************service***********/
    public static final String SERVICE_BASE_PACKAGE = "com.biz.primus.ms.csm";
    public static final String SERVICE_PACKAGE = SERVICE_BASE_PACKAGE + ".service";
    public static final String SERVICE_PACKAGE_PATH = packageConvertPath(SERVICE_PACKAGE);
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
    public static final String SERVICE_IMPL_PACKAGE_PATH = packageConvertPath(SERVICE_IMPL_PACKAGE);
    public static final String SERVICE_API_PACKAGE = SERVICE_BASE_PACKAGE + ".api";
    public static final String SERVICE_API_PACKAGE_PATH = packageConvertPath(SERVICE_API_PACKAGE);
    public static final String SERVICE_REST_PACKAGE = SERVICE_API_PACKAGE + ".rest";
    public static final String SERVICE_REST_PACKAGE_PATH = packageConvertPath(SERVICE_REST_PACKAGE);


    /**************management********/
    public static final String MANAGEMENT_BASE_PACKAGE = "com.biz.primus.csm";
    public static final String MANAGE_CONTROLLER_PACKAGE = MANAGEMENT_BASE_PACKAGE + ".controller";
    public static final String MANAGE_CONTROLLER_PACKAGE_PATH = packageConvertPath(MANAGE_CONTROLLER_PACKAGE);
    public static final String MANAGE_FEIGN_PACKAGE = MANAGEMENT_BASE_PACKAGE + ".feign";
    public static final String MANAGE_FEIGN_PACKAGE_PATH = packageConvertPath(MANAGE_FEIGN_PACKAGE);
    public static final String MANAGE_FEIGN_DEGRADED_PACKAGE = MANAGE_FEIGN_PACKAGE + ".degraded";
    public static final String MANAGE_FEIGN_DEGRADED_PACKAGE_PATH = packageConvertPath(MANAGE_FEIGN_DEGRADED_PACKAGE);

    /**************template*****************/
    public static final String ENTITY_TEMPLATE = "entity.ftl";
    public static final String MAPPER_TEMPLATE = "mapper-xml.ftl";
    public static final String DAO_TEMPLATE = "dao.ftl";

    public static final String VO_TEMPLATE = "vo.ftl";
    public static final String REQ_VO_TEMPLATE = "search-vo.ftl";

    public static final String SERVICE_TEMPLATE = "service.ftl";
    public static final String SERVICE_IMPL_TEMPLATE = "service-impl.ftl";
    public static final String SERVICE_API_TEMPLATE = "service-api.ftl";
    public static final String SERVICE_REST_TEMPLATE = "service-rest-api.ftl";

    public static final String MANAGEMENT_CONTROLLER_TEMPLATE = "manage-controller.ftl";
    public static final String MANAGEMENT_FEIGN_TEMPLATE = "manage-feign.ftl";
    public static final String MANAGEMENT_DEGRADED_TEMPLATE = "manage-degraded.ftl";
    public static final String WEB_LIST_TEMPLATE = "web-list.ftl";
    public static final String WEB_ADD_TEMPLATE = "web-add.ftl";
    public static final String WEB_VIEW_TEMPLATE = "web-view.ftl";
    public static final String WEB_EDIT_TEMPLATE = "web-edit.ftl";


    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}