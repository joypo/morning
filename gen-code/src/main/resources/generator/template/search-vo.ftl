package ${basePackage}.domain.search;

import ${basePackage}.domain.${modelNameUpperCamel};
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}SearchVO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("${table.comment}SearchVO")
public class ${modelNameUpperCamel}SearchVO extends ${modelNameUpperCamel} {
}
