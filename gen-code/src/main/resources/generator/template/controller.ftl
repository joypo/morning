package ${basePackage}.controller;

import ${basePackage}.base.HttpResult;
import ${basePackage}.base.core.PageSearchParam;
import ${basePackage}.domain.${modelNameUpperCamel};
import ${basePackage}.domain.search.${modelNameUpperCamel}SearchVO;
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}Controller
 */
@RestController
@RequestMapping("${modelNameLowerCamel}")
@Slf4j
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping
    public HttpResult add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return HttpResult.success();
    }

    @DeleteMapping
    public HttpResult delete(@RequestParam String id) {
        ${modelNameLowerCamel}Service.deleteById(Long.valueOf(id));
        return HttpResult.success();
    }

    @PutMapping
    public HttpResult update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return HttpResult.success();
    }

    @GetMapping
    public HttpResult<${modelNameUpperCamel}> detail(@RequestParam String id) {
        return HttpResult.successGenerics(${modelNameLowerCamel}Service.findById(Long.valueOf(id)));
    }

    @PostMapping("page")
    public HttpResult<PageInfo<${modelNameUpperCamel}>> list(@RequestBody PageSearchParam<${modelNameUpperCamel}SearchVO> searchVO) {
        PageHelper.startPage(searchVO.getPage(), searchVO.getSize());
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return HttpResult.successGenerics(pageInfo);
    }
}
