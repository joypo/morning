package ${basePackage}.domain.vo;

import ${basePackage}.domain.${modelNameUpperCamel};
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("${table.comment}VO")
public class ${modelNameUpperCamel}VO extends ${modelNameUpperCamel} {
}
