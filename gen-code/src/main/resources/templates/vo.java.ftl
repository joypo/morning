package ${package.VO};

import ${package.Entity}.${table.upperCamelName}Entity;
<#if swagger2>
import io.swagger.annotations.ApiModel;
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * ${table.comment}VO
 *
 * @author ${author}
 * @date ${date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
<#if swagger2>
@ApiModel(value = "${table.upperCamelName}VO", description = "${table.comment!}VO")
</#if>
public class ${table.upperCamelName}VO extends ${table.upperCamelName}Entity {

}
