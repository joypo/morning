<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.dao.${modelNameUpperCamel}Dao">
    <resultMap id="BaseResultMap" type="${basePackage}.domain.${modelNameUpperCamel}">
<#list table.fields as item>
    <#if item.keyFlag==true>
        <id column="${item.name}" property="${item.lowerCamelName}"/>
    <#else>
        <result column="${item.name}" property="${item.lowerCamelName}"/>
    </#if>
</#list>
    </resultMap>
</mapper>