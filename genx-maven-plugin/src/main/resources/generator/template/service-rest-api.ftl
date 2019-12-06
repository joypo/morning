package ${serviceRestPackage};

import com.biz.primus.ms.base.api.BaseApiController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}RestApiController
 */
@RestController
@RequestMapping("rest/csm/${modelNameLowerCamel}RestApi")
@Slf4j
@Api(tags = "小程序-${table.comment}-rest-api")
public class ${modelNameUpperCamel}RestApiController extends BaseApiController {

}
