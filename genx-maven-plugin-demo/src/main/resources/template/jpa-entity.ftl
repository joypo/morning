package ${entityPackage}.${module};

import com.biz.dap.common.entity.BaseFieldsEntity;
import com.bizunited.platform.saturn.engine.annotation.SaturnColumn;
import com.bizunited.platform.saturn.engine.annotation.SaturnDomain;
<#if table.hasDate==true>
import com.fasterxml.jackson.annotation.JsonFormat;
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
<#if table.hasDate==true>
import org.springframework.format.annotation.DateTimeFormat;
</#if>

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
<#if table.hasDate==true>
import java.util.Date;
</#if>

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}
 */
@ApiModel(value = "${modelNameUpperCamel}Entity", description = "${table.comment}")
@Entity
@Table(name = "${table.name}")
@SaturnDomain(value = "${module}")
public class ${modelNameUpperCamel}Entity extends BaseFieldsEntity {
<#list table.fields as item>

    /**
     * ${item.comment}
     */
    <#if item.propertyName=="Date">
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    @Column(name = "${item.name}", columnDefinition = "${item.type} comment'${item.comment}'")
    @SaturnColumn(description = "${item.comment}")
    @ApiModelProperty(name = "${item.lowerCamelName}", value = "${item.comment}")
    private ${item.propertyName} ${item.lowerCamelName};
</#list>

<#list table.fields as item>

    public ${item.propertyName} get${item.upperCamelName}() {
        return ${item.lowerCamelName};
    }

    public void set${item.upperCamelName}(${item.propertyName} ${item.lowerCamelName}) {
        this.${item.lowerCamelName} = ${item.lowerCamelName};
    }
</#list>
}
