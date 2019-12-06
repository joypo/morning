package ${voPackage};

import com.biz.primus.model.csm.common.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}Vo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("${table.comment}Vo")
public class ${modelNameUpperCamel}Vo extends BaseVo {
<#list table.fields as item>

    /**
    * ${item.comment}
    */
    @ApiModelProperty("${item.comment}")
    <#if item.propertyName=="Date">
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${item.propertyName} ${item.lowerCamelName};
</#list>
}
