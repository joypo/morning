package ${entityPackage};

import com.baomidou.mybatisplus.annotations.TableField;
<#if table.keyAutoIncr==false>
import com.baomidou.mybatisplus.annotations.TableId;
</#if>
import com.baomidou.mybatisplus.annotations.TableName;
<#if table.keyAutoIncr==false>
import com.baomidou.mybatisplus.enums.IdType;
</#if>
<#if table.hasDate==true>
import com.fasterxml.jackson.annotation.JsonFormat;
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#if table.hasDate==true>
import org.springframework.format.annotation.DateTimeFormat;
</#if>

import javax.persistence.*;
<#if table.hasDecimal==true>
import java.math.BigDecimal;
</#if>
<#if table.hasDate==true>
import java.util.Date;
</#if>

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("${table.comment}")
@TableName("${table.name}")
public class ${modelNameUpperCamel} extends BaseCsmEntity {

<#if table.keyAutoIncr==false>
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.INPUT)
    private Long id;
</#if>
<#list table.fields as item>

    /**
     * ${item.comment}
     */
    @TableField(value = "${item.name}")
    @ApiModelProperty("${item.comment}")
    <#if item.propertyName=="Date">
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${item.propertyName} ${item.lowerCamelName};
</#list>
}
