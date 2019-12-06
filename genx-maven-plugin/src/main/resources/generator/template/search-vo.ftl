package ${reqVoPackage};

import com.biz.primus.model.csm.page.PageParam;
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
@ApiModel("${table.comment}ReqVo")
public class ${modelNameUpperCamel}ReqVo extends PageParam {
}
