package com.example.morning.generator.builder;

import com.example.morning.generator.config.*;
import com.example.morning.generator.core.CamelPrefixType;
import com.example.morning.generator.core.ConstVal;
import com.example.morning.generator.core.QuerySQL;
import com.example.morning.generator.po.TableField;
import com.example.morning.generator.po.TableInfo;
import com.example.morning.generator.util.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author sunx
 * @date 2020-01-08
 */
@Data
public class ConfigBuilder {

    protected static final Logger logger = LoggerFactory.getLogger(ConfigBuilder.class);

    /**
     * SQL语句类型
     */
    private QuerySQL querySQL = QuerySQL.MYSQL;

    /**
     * 模板路径配置信息
     */
    private TemplateConfig template;

    /**
     * 数据库配置
     */
    private DataSourceConfig dataSourceConfig;
    /**
     * SQL连接
     */
    private Connection connection;

    private String superEntityClass;
    private String superMapperClass;
    /**
     * service超类定义
     */
    private String superServiceClass;
    private String superServiceImplClass;
    private String superControllerClass;
    /**
     * 数据库表信息
     */
    private List<TableInfo> tableInfoList;
    /**
     * 包配置详情
     */
    private Map<String, String> packageInfo;

    /**
     * 默认包配置详情
     */
    private Map<String, String> defaultPackageInfo;

    /**
     * 路径配置信息
     */
    private Map<String, String> pathInfo;
    /**
     * 策略配置
     */
    private StrategyConfig strategyConfig;
    /**
     * 全局配置信息
     */
    private GlobalConfig globalConfig;

    /**
     * 包配置信息
     */
    private PackageConfig packageConfig;


    /**
     * 过滤正则
     */
    private static final Pattern REGX = Pattern.compile("[~!/@#$%^&*()-_=+\\\\|[{}];:\\'\\\",<.>/?]+");

    public ConfigBuilder(TemplateConfig template,
                         PackageConfig packageConfig,
                         DataSourceConfig dataSource,
                         StrategyConfig strategy,
                         GlobalConfig globalConfig) {
        // 全局配置
        this.globalConfig = globalConfig;
        if (null == globalConfig) {
            this.globalConfig = new GlobalConfig();
        }
        // 模板配置
        if (null == template) {
            this.template = new TemplateConfig();
        } else {
            this.template = template;
        }

        this.packageConfig = packageConfig;
        // 包配置
        if (null == this.packageConfig) {
            packageConfig = new PackageConfig();
        }
        initEasyFilterSets();
        handlerPackage(this.template, this.globalConfig.getOutputDir(), packageConfig);
        handlerDataSource(dataSource);
        // 策略配置
        strategyConfig = strategy;
        if (null == strategyConfig) {
            this.strategyConfig = new StrategyConfig();
        }
        handlerStrategy();
    }

    /**
     * 期初easyFilterSets
     */
    private void initEasyFilterSets() {
        if (!globalConfig.isEasyCopy()) {
            return;
        }
        defaultPackageInfo = new HashMap<>(32);
        defaultPackageInfo.put(ConstVal.MODULE_NAME, packageConfig.getModuleName());
        defaultPackageInfo.put(ConstVal.ENTITY, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_ENTITY_PACKAGE));
        defaultPackageInfo.put(ConstVal.MAPPER, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_MAPPER_PACKAGE));
        defaultPackageInfo.put(ConstVal.XML, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_XML_PACKAGE));
        defaultPackageInfo.put(ConstVal.SERVICE, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_SERVICE_PACKAGE));
        defaultPackageInfo.put(ConstVal.SERVICE_IMPL, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_SERVICE_IMPL_PACKAGE));
        defaultPackageInfo.put(ConstVal.CONTROLLER, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_CONTROLLER_PACKAGE));
        defaultPackageInfo.put(ConstVal.VO, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_VO_PACKAGE));
        defaultPackageInfo.put(ConstVal.FEIGN, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_FEIGN_PACKAGE));
        defaultPackageInfo.put(ConstVal.FEIGN_DEGRADED, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_FEIGN_DEGRADED_PACKAGE));
        defaultPackageInfo.put(ConstVal.API_CONTROLLER, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_API_CONTROLLER_PACKAGE));
        defaultPackageInfo.put(ConstVal.VO_MAPPER, joinPackage(packageConfig.getParent(), ConstVal.DEFAULT_VO_MAPPER_PACKAGE));
    }

    /**
     * 处理数据库表 加载数据库表、列、注释相关数据集
     */
    private void handlerStrategy() {
        processTypes();
        tableInfoList = getTablesInfo();
    }

    /**
     * 处理superClassName,IdClassType,IdStrategy配置
     */
    private void processTypes() {
        if (org.apache.commons.lang3.StringUtils.isBlank(strategyConfig.getSuperServiceClass())) {
            superServiceClass = ConstVal.SUPER_SERVICE_CLASS;
        } else {
            superServiceClass = strategyConfig.getSuperServiceClass();
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(strategyConfig.getSuperServiceImplClass())) {
            superServiceImplClass = ConstVal.SUPER_SERVICE_IMPL_CLASS;
        } else {
            superServiceImplClass = strategyConfig.getSuperServiceImplClass();
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(strategyConfig.getSuperMapperClass())) {
            superMapperClass = ConstVal.SUPER_MAPPER_CLASS;
        } else {
            superMapperClass = strategyConfig.getSuperMapperClass();
        }
        superEntityClass = strategyConfig.getSuperEntityClass();
        superControllerClass = strategyConfig.getSuperControllerClass();
    }

    /**
     * 获取所有的数据库表信息
     */
    private List<TableInfo> getTablesInfo() {
        List<TableInfo> list = Lists.newArrayList();
        try (PreparedStatement preparedStatement = connection.prepareStatement(querySQL.getTableCommentsSql())) {
            try (ResultSet results = preparedStatement.executeQuery()) {
                Boolean hasDecimal;
                Boolean hasDate;
                while (results.next()) {
                    String tableName = results.getString(querySQL.getTableName());
                    boolean f = validateTableName(tableName);
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(tableName) && f) {
                        hasDecimal = false;
                        hasDate = false;
                        String tableComment = results.getString(querySQL.getTableComment());
                        TableInfo tableInfo = new TableInfo();
                        tableInfo.setName(tableName);
                        tableInfo.setComment(tableComment);
                        tableInfo.setLowerCamelName(StringUtils.caseFormat(StringUtils.removePrefix(tableName, strategyConfig.getTablePrefix()), CamelPrefixType.LOWER));
                        tableInfo.setUpperCamelName(StringUtils.caseFormat(StringUtils.removePrefix(tableName, strategyConfig.getTablePrefix()), CamelPrefixType.UPPER));
                        tableInfo.setFields(getListFields(tableName, true));
                        tableInfo.setAllFields(getListFields(tableName, false));
                        tableInfo.setHasDecimal(false);
                        tableInfo.setHasDate(false);
                        for (TableField fieldInfo : tableInfo.getFields()) {
                            if (ConstVal.BIGDECIMALFLAG.equals(fieldInfo.getPropertyName())) {
                                tableInfo.setHasDecimal(true);
                                hasDecimal = true;
                            }
                            if (ConstVal.DATEFLAG.equals(fieldInfo.getPropertyName())) {
                                hasDate = true;
                            }
                        }
                        tableInfo.setHasDate(hasDate);
                        tableInfo.setHasDecimal(hasDecimal);
                        list.add(tableInfo);
                    }
                }
            }
        } catch (SQLException ex) {
            logger.error("SQLException [{}]", ex);
        }
        return list;
    }


    /**
     * 根据表名获取字段详情信息
     *
     * @param tableName
     * @param excludeFlag
     * @return
     * @throws SQLException
     */
    private List<TableField> getListFields(String tableName, Boolean excludeFlag) throws SQLException {
        List<TableField> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(querySQL.getTableFieldsSql(), tableName))) {
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    TableField field = new TableField();
                    String key = results.getString(querySQL.getFieldKey());
                    // 避免多重主键设置，目前只取第一个找到ID，并放到list中的索引为0的位置
                    boolean isId = org.apache.commons.lang3.StringUtils.isNotBlank(key) && ConstVal.PRI.equalsIgnoreCase(key);
                    String ext = results.getString(querySQL.getFieldExt());
                    // 处理ID
                    field.setKeyFlag(isId);
                    if (isId && org.apache.commons.lang3.StringUtils.isNotBlank(ext)) {
                        field.setKeyIdentityFlag(Objects.equals("auto_increment", ext.toLowerCase()));
                    }
                    // 处理其它信息
                    field.setName(results.getString(querySQL.getFieldName()));
                    if (null != strategyConfig.getExcludeFields()) {
                        Set<String> set = Sets.newHashSet(strategyConfig.getExcludeFields());
                        boolean flag1 = excludeFlag
                                && null != strategyConfig.getExcludeFields()
                                && strategyConfig.getExcludeFields().length > 0
                                && set.contains(field.getName());
                        if (flag1) {
                            continue;
                        }
                    }
                    field.setType(results.getString(querySQL.getFieldType()));
                    field.setComment(results.getString(querySQL.getFieldComment()));
                    field.setLowerCamelName(StringUtils.caseFormat(field.getName(), CamelPrefixType.LOWER));
                    field.setUpperCamelName(StringUtils.caseFormat(field.getName(), CamelPrefixType.UPPER));
                    field.setPropertyName(getPropertyNameByType(field.getType()));
                    list.add(field);
                }
            }
            return list;
        } catch (SQLException ex) {
            logger.error("error [{}]", ex);
        }
        return Lists.newArrayList();
    }

    private String getPropertyNameByType(String type) {
        String t = type.toLowerCase();
        if (t.contains(ConstVal.CHAR_1) || t.contains(ConstVal.TEXT_1) || t.contains(ConstVal.JSON_1) || t.contains(ConstVal.ENUM_1)) {
            return "String";
        } else if (t.contains(ConstVal.BIGINT_1)) {
            return "Long";
        } else if (t.contains(ConstVal.INT_1)) {
            return "Integer";
        } else if (t.contains(ConstVal.DATE_1) || t.contains(ConstVal.TIME_1) || t.contains(ConstVal.YEAR_1)) {
            return "Date";
        } else if (t.contains(ConstVal.BIT_1)) {
            return "Boolean";
        } else if (t.contains(ConstVal.DECIMAL_1)) {
            return "BigDecimal";
        } else if (t.contains(ConstVal.BLOB_1)) {
            return "byte[]";
        } else if (t.contains(ConstVal.FLOAT_1)) {
            return "Float";
        } else if (t.contains(ConstVal.DOUBLE_1)) {
            return "Double";
        }
        return "String";
    }

    /**
     * 验证当前表是否需要生成代码
     *
     * @param tableName
     * @return
     */
    private Boolean validateTableName(String tableName) {
        Boolean flag = Objects.equals(false, strategyConfig.getIncludeFlag());
        if (null == strategyConfig.getInclude() || strategyConfig.getInclude().length == 0) {
            return !flag;
        }
        return Sets.newHashSet(strategyConfig.getInclude()).contains(tableName) | flag;
    }

    private void handlerDataSource(DataSourceConfig config) {
        connection = config.getConn();
    }

    private void handlerPackage(TemplateConfig template, String outputDir, PackageConfig config) {

        // 包信息
        packageInfo = new HashMap<>(32);
        packageInfo.put(ConstVal.MODULE_NAME, config.getModuleName());
        packageInfo.put(ConstVal.ENTITY, joinPackage(config.getParent(), config.getEntity()));
        packageInfo.put(ConstVal.MAPPER, joinPackage(config.getParent(), config.getMapper()));
        packageInfo.put(ConstVal.XML, joinPackage(config.getParent(), config.getXml()));
        packageInfo.put(ConstVal.SERVICE, joinPackage(config.getParent(), config.getService()));
        packageInfo.put(ConstVal.SERVICE_IMPL, joinPackage(config.getParent(), config.getServiceImpl()));
        packageInfo.put(ConstVal.CONTROLLER, joinPackage(config.getParent(), config.getController()));
        packageInfo.put(ConstVal.VO, joinPackage(config.getParent(), config.getVo()));
        packageInfo.put(ConstVal.FEIGN, joinPackage(config.getParent(), config.getFeign()));
        packageInfo.put(ConstVal.FEIGN_DEGRADED, joinPackage(config.getParent(), config.getFeignDegraded()));
        packageInfo.put(ConstVal.API_CONTROLLER, joinPackage(config.getParent(), config.getApiController()));
        packageInfo.put(ConstVal.VO_MAPPER, joinPackage(config.getParent(), config.getVoMapper()));

        // 自定义路径
        Map<String, String> configPathInfo = config.getPathInfo();
        if (null != configPathInfo) {
            pathInfo = configPathInfo;
        } else {
            // 生成路径信息
            pathInfo = new HashMap<>(6);
            setPathInfo(pathInfo, template.getEntity(), outputDir, ConstVal.ENTITY_PATH, ConstVal.ENTITY);
            setPathInfo(pathInfo, template.getMapper(), outputDir, ConstVal.MAPPER_PATH, ConstVal.MAPPER);
            setPathInfo(pathInfo, template.getXml(), outputDir, ConstVal.XML_PATH, ConstVal.XML);
            setPathInfo(pathInfo, template.getService(), outputDir, ConstVal.SERVICE_PATH, ConstVal.SERVICE);
            setPathInfo(pathInfo, template.getServiceImpl(), outputDir, ConstVal.SERVICE_IMPL_PATH, ConstVal.SERVICE_IMPL);
            setPathInfo(pathInfo, template.getController(), outputDir, ConstVal.CONTROLLER_PATH, ConstVal.CONTROLLER);
            setPathInfo(pathInfo, template.getVo(), outputDir, ConstVal.VO_PATH, ConstVal.VO);
            setPathInfo(pathInfo, template.getFeign(), outputDir, ConstVal.FEIGN_PATH, ConstVal.FEIGN);
            setPathInfo(pathInfo, template.getFeignDegraded(), outputDir, ConstVal.FEIGN_DEGRADED_PATH, ConstVal.FEIGN_DEGRADED);
            setPathInfo(pathInfo, template.getApiController(), outputDir, ConstVal.API_CONTROLLER_PATH, ConstVal.API_CONTROLLER);
            setPathInfo(pathInfo, template.getVoMapper(), outputDir, ConstVal.VO_MAPPER_PATH, ConstVal.VO_MAPPER);
        }
    }

    private void setPathInfo(Map<String, String> pathInfo, String template, String outputDir, String path, String module) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(template)) {
            pathInfo.put(path, joinPath(outputDir, globalConfig.isEasyCopy() ? defaultPackageInfo.get(module) : packageInfo.get(module)));
        }
    }

    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (org.apache.commons.lang3.StringUtils.isBlank(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!org.apache.commons.lang3.StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", ConstVal.BACK_SLASH + File.separator);
        return parentDir + packageName;
    }


    /**
     * 连接父子包名
     *
     * @param parent     父包名
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    private String joinPackage(String parent, String subPackage) {
        if (org.apache.commons.lang3.StringUtils.isBlank(parent)) {
            return subPackage;
        }
        return parent + ConstVal.DOT + subPackage;
    }
}
