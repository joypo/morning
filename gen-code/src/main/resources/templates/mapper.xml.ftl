<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.upperCamelName}Mapper">
    <!-- 通用查询结果列 -->
    <sql id="BaseSql">
        <#list table.allFields as item><#if item_index==0>${item.name} as ${item.lowerCamelName}<#else>, ${item.name} as ${item.lowerCamelName}</#if></#list>
    </sql>
    <sql id="BaseQuerySql"></sql>

    <select id="selectPageList" resultType="${package.Entity}.${table.upperCamelName}Entity">
        select <include refid="BaseSql"></include> from ${table.name} where 1=1
        <include refid="BaseQuerySql"></include>
    </select>
</mapper>