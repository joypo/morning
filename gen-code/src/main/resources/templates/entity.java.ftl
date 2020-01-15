package ${package.Entity};

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
<#if superEntityClass??>
import ${superEntityClassPage};
</#if>
<#if table.hasDate==true>
import com.fasterxml.jackson.annotation.JsonFormat;
</#if>
import com.fasterxml.jackson.annotation.JsonProperty;
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
<#if table.hasDate==true>
import org.springframework.format.annotation.DateTimeFormat;
</#if>

<#if table.hasDecimal==true>
import java.math.BigDecimal;
</#if>
<#if table.hasDate==true>
import java.util.Date;
</#if>

/**
 * ${table.comment!} Entity
 *
 * @author ${author}
 * @date ${date}
 */
@Data
<#if superEntityClass??>
@EqualsAndHashCode(callSuper = true)
<#else>
@EqualsAndHashCode(callSuper = false)
</#if>
@Accessors(chain = true)
@TableName("${table.name}")
<#if swagger2>
@ApiModel(value = "${table.upperCamelName}Entity", description = "${table.comment!}")
</#if>
<#if superEntityClass??>
public class ${table.upperCamelName}Entity extends ${superEntityClass} {
<#else>
public class ${table.upperCamelName}Entity implements Serializable {
</#if>

    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
        <#if swagger2>
    @ApiModelProperty(value = "${field.comment}")
        <#else>
    /**
     * ${field.comment}
     */
        </#if>
    </#if>
    <#if field.keyFlag>
    <#-- 主键 -->
        <#if field.keyIdentityFlag>
    @TableId(value = "${field.name}", type = IdType.AUTO)
        <#else>
    @TableId("${field.name}")
        </#if>
    <#-- 普通字段 -->
    <#else>
    @TableField("${field.name}")
    </#if>
    <#if field.propertyName=="Date">
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    @JsonProperty(value = "${field.name}")
    private ${field.propertyName} ${field.lowerCamelName};
</#list>
<#------------  END 字段循环遍历  ---------->
}
