package ${basePackage}.domain;

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
@Table(name = "${table.name}")
public class ${modelNameUpperCamel} {
<#list table.fields as item>

    /**
     * ${item.comment}
     */
    <#if item.keyFlag==true>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    @Column(name = "${item.name}")
    @ApiModelProperty("${item.comment}")
    <#if item.propertyName=="Date">
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${item.propertyName} ${item.lowerCamelName};
</#list>
}
