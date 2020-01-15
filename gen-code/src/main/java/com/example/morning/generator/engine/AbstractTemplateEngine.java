package com.example.morning.generator.engine;

import com.example.morning.generator.builder.ConfigBuilder;
import com.example.morning.generator.config.GlobalConfig;
import com.example.morning.generator.config.TemplateConfig;
import com.example.morning.generator.core.ConstVal;
import com.example.morning.generator.po.TableInfo;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 模板引擎抽象类
 *
 * @author 12
 */
public abstract class AbstractTemplateEngine {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractTemplateEngine.class);
    /**
     * 配置信息
     */
    private ConfigBuilder configBuilder;


    /**
     * 模板引擎初始化
     */
    public AbstractTemplateEngine init(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
        return this;
    }


    /**
     * 输出 java xml 文件
     */
    public AbstractTemplateEngine batchOutput() {
        try {
            List<TableInfo> tableInfoList = getConfigBuilder().getTableInfoList();
            for (TableInfo tableInfo : tableInfoList) {
                Map<String, Object> objectMap = getObjectMap(tableInfo);
                Map<String, String> pathInfo = getConfigBuilder().getPathInfo();
                TemplateConfig template = getConfigBuilder().getTemplate();
                // Mp.java
                if (null != pathInfo.get(ConstVal.ENTITY_PATH)) {
                    String entityFile = String.format((pathInfo.get(ConstVal.ENTITY_PATH) + File.separator + "%sEntity" + ConstVal.JAVA_SUFFIX), tableInfo.getUpperCamelName());
                    if (isCreate(entityFile)) {
                        writer(objectMap, templateFilePath(template.getEntity()), entityFile);
                    }
                }
                // MpMapper.java
                if (null != pathInfo.get(ConstVal.MAPPER_PATH)) {
                    String mapperFile = String.format((pathInfo.get(ConstVal.MAPPER_PATH) + File.separator + "%sMapper" + ConstVal.JAVA_SUFFIX), tableInfo.getUpperCamelName());
                    if (isCreate(mapperFile)) {
                        writer(objectMap, templateFilePath(template.getMapper()), mapperFile);
                    }
                }

                // MpMapper.xml
                if (null != pathInfo.get(ConstVal.XML_PATH)) {
                    String xmlFile = String.format((pathInfo.get(ConstVal.XML_PATH) + File.separator + "%sMapper" + ConstVal.XML_SUFFIX), tableInfo.getUpperCamelName());
                    if (isCreate(xmlFile)) {
                        writer(objectMap, templateFilePath(template.getXml()), xmlFile);
                    }
                }

                // IMpService.java
                if (null != pathInfo.get(ConstVal.SERVICE_PATH)) {
                    String serviceFile = String.format((pathInfo.get(ConstVal.SERVICE_PATH) + File.separator + "%sService" + ConstVal.JAVA_SUFFIX), tableInfo.getUpperCamelName());
                    if (isCreate(serviceFile)) {
                        writer(objectMap, templateFilePath(template.getService()), serviceFile);
                    }
                }

                // MpServiceImpl.java
                if (null != pathInfo.get(ConstVal.SERVICE_IMPL_PATH)) {
                    String implFile = String.format((pathInfo.get(ConstVal.SERVICE_IMPL_PATH) + File.separator + "%sServiceImpl" + ConstVal.JAVA_SUFFIX), tableInfo.getUpperCamelName());
                    if (isCreate(implFile)) {
                        writer(objectMap, templateFilePath(template.getServiceImpl()), implFile);
                    }
                }

                // MpController.java
                if (null != pathInfo.get(ConstVal.CONTROLLER_PATH)) {
                    String controllerFile = String.format((pathInfo.get(ConstVal.CONTROLLER_PATH) + File.separator + "%sController" + ConstVal.JAVA_SUFFIX), tableInfo.getUpperCamelName());
                    if (isCreate(controllerFile)) {
                        writer(objectMap, templateFilePath(template.getController()), controllerFile);
                    }
                }

                // MpApiController.java
                if (null != pathInfo.get(ConstVal.API_CONTROLLER_PATH)) {
                    String apiControllerFile = String.format((pathInfo.get(ConstVal.API_CONTROLLER_PATH) + File.separator + "%sApiController" + ConstVal.JAVA_SUFFIX), tableInfo.getUpperCamelName());
                    if (isCreate(apiControllerFile)) {
                        writer(objectMap, templateFilePath(template.getApiController()), apiControllerFile);
                    }
                }

                // MpVO.java
                if (null != pathInfo.get(ConstVal.VO_PATH)) {
                    String voFile = String.format((pathInfo.get(ConstVal.VO_PATH) + File.separator + "%sVO" + ConstVal.JAVA_SUFFIX), tableInfo.getUpperCamelName());
                    if (isCreate(voFile)) {
                        writer(objectMap, templateFilePath(template.getVo()), voFile);
                    }
                }

                // MpVoMapper.java
                if (null != pathInfo.get(ConstVal.VO_MAPPER_PATH)) {
                    String voMapperFile = String.format((pathInfo.get(ConstVal.VO_MAPPER_PATH) + File.separator + "%sVoMapper" + ConstVal.JAVA_SUFFIX), tableInfo.getUpperCamelName());
                    if (isCreate(voMapperFile)) {
                        writer(objectMap, templateFilePath(template.getVoMapper()), voMapperFile);
                    }
                }

                if (configBuilder.getGlobalConfig().isFeign()) {
                    // MpFeign.java
                    if (null != pathInfo.get(ConstVal.FEIGN_PATH)) {
                        String feignFile = String.format((pathInfo.get(ConstVal.FEIGN_PATH) + File.separator + "%sFeignClient" + ConstVal.JAVA_SUFFIX), tableInfo.getUpperCamelName());
                        if (isCreate(feignFile)) {
                            writer(objectMap, templateFilePath(template.getFeign()), feignFile);
                        }
                    }

                    // MpFeginDegraded.java
                    if (null != pathInfo.get(ConstVal.FEIGN_DEGRADED_PATH)) {
                        String feignDegradedFile = String.format((pathInfo.get(ConstVal.FEIGN_DEGRADED_PATH) + File.separator + "%sFeignClientDegraded" + ConstVal.JAVA_SUFFIX), tableInfo.getUpperCamelName());
                        if (isCreate(feignDegradedFile)) {
                            writer(objectMap, templateFilePath(template.getFeignDegraded()), feignDegradedFile);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("无法创建文件，请检查配置信息！", e);
        }
        return this;
    }


    /**
     * 将模板转化成为文件
     *
     * @param objectMap    渲染对象 MAP 信息
     * @param templatePath 模板文件
     * @param outputFile   文件生成的目录
     */
    public abstract void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception;

    /**
     * 处理输出目录
     */
    public AbstractTemplateEngine mkdirs() {
        getConfigBuilder().getPathInfo().forEach((key, value) -> {
            File dir = new File(value);
            if (!dir.exists()) {
                boolean result = dir.mkdirs();
                if (result) {
                    logger.debug("创建目录： [{}]", value);
                }
            }
        });
        return this;
    }


    /**
     * 打开输出目录
     */
    public void open() {
        String outDir = getConfigBuilder().getGlobalConfig().getOutputDir();
        if (getConfigBuilder().getGlobalConfig().isOpen()
                && StringUtils.isNotBlank(outDir)) {
            try {
                String osName = System.getProperty("os.name");
                if (osName != null) {
                    if (osName.contains(ConstVal.MAC_SYS)) {
                        Runtime.getRuntime().exec("open " + outDir);
                    } else if (osName.contains(ConstVal.WIN_SYS)) {
                        Runtime.getRuntime().exec("cmd /c start " + outDir);
                    } else {
                        logger.debug("文件输出目录:{}", outDir);
                    }
                }
            } catch (IOException e) {
                logger.error("error [{}]", e);
            }
        }
    }


    /**
     * 渲染对象 MAP 信息
     *
     * @param tableInfo 表信息对象
     * @return ignore
     */
    public Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> objectMap = new HashMap<>(30);
        ConfigBuilder config = getConfigBuilder();
        objectMap.put("package", config.getPackageInfo());
        GlobalConfig globalConfig = config.getGlobalConfig();
        objectMap.put("author", globalConfig.getAuthor());
        objectMap.put("swagger2", globalConfig.isSwagger2());
        objectMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        objectMap.put("table", tableInfo);
        objectMap.put("superEntityClass", getSuperClassName(config.getSuperEntityClass()));
        objectMap.put("superEntityClassPage", config.getSuperEntityClass());
        objectMap.put("superMapperClassPackage", config.getSuperMapperClass());
        objectMap.put("superMapperClass", getSuperClassName(config.getSuperMapperClass()));
        objectMap.put("superServiceClassPackage", config.getSuperServiceClass());
        objectMap.put("superServiceClass", getSuperClassName(config.getSuperServiceClass()));
        objectMap.put("superServiceImplClassPackage", config.getSuperServiceImplClass());
        objectMap.put("superServiceImplClass", getSuperClassName(config.getSuperServiceImplClass()));
        objectMap.put("superControllerClassPackage", verifyClassPacket(config.getSuperControllerClass()));
        objectMap.put("superControllerClass", getSuperClassName(config.getSuperControllerClass()));
        objectMap.put("feignServiceName", globalConfig.getFeignServiceName());
        objectMap.put("feignCamelServiceName", getFeignCamelName(globalConfig.getFeignServiceName()));
        return objectMap;
    }

    /**
     * 用于渲染对象MAP信息 {@link #getObjectMap(TableInfo)} 时的superClassPacket非空校验
     *
     * @param classPacket ignore
     * @return ignore
     */
    private String verifyClassPacket(String classPacket) {
        return StringUtils.isBlank(classPacket) ? null : classPacket;
    }

    /**
     * 获取类名
     *
     * @param classPath ignore
     * @return ignore
     */
    private String getSuperClassName(String classPath) {
        if (StringUtils.isBlank(classPath)) {
            return null;
        }
        return classPath.substring(classPath.lastIndexOf(ConstVal.DOT) + 1);
    }

    /**
     * 获取feign驼峰命名
     *
     * @param feignServiceName
     * @return
     */
    private String getFeignCamelName(String feignServiceName) {
        if (StringUtils.isBlank(feignServiceName)) {
            return feignServiceName;
        }
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, feignServiceName.replace('-', '_'));
    }


    /**
     * 模板真实文件路径
     *
     * @param filePath 文件路径
     * @return ignore
     */
    public abstract String templateFilePath(String filePath);


    /**
     * 检测文件是否存在
     *
     * @return 文件是否存在
     */
    protected boolean isCreate(String filePath) {
        // 全局判断【默认】
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            file.getParentFile().mkdirs();
        }
        return !exist || getConfigBuilder().getGlobalConfig().isFileOverride();
    }


    public ConfigBuilder getConfigBuilder() {
        return configBuilder;
    }
}
