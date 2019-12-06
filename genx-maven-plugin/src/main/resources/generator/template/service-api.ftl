package ${serviceApiPackage};

import com.biz.primus.common.pagehelper.PageInfo;
import ${voPackage}.${modelNameUpperCamel}Vo;
import ${reqVoPackage}.${modelNameUpperCamel}ReqVo;
import com.biz.primus.ms.base.api.BaseApiController;
import ${servicePackage}.${modelNameUpperCamel}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}Controller
 */
@RestController
@RequestMapping("csm/${modelNameLowerCamel}Api")
@Slf4j
public class ${modelNameUpperCamel}ApiController extends BaseApiController {

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /**
     * ${table.comment}分页查询
     *
     * @param vo
     * @return
     */
    @PostMapping("/selectPageList")
    public PageInfo<${modelNameUpperCamel}Vo> selectPageList(@RequestBody ${modelNameUpperCamel}ReqVo vo) {
        log.info("csm/${modelNameLowerCamel}Api/selectPageList params [{}]", vo);
        return ${modelNameLowerCamel}Service.selectPageList(vo);
    }


    /**
     * 获取${table.comment}详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public ${modelNameUpperCamel}Vo detail(@RequestParam String id) {
        log.info("csm/${modelNameLowerCamel}Api/detail?id={}", id);
        return ${modelNameLowerCamel}Service.findById(Long.valueOf(id));
    }

    /**
     * 保存${table.comment},新增或编辑
     *
     * @param vo
     */
    @PostMapping("/save")
    public void save(@RequestBody ${modelNameUpperCamel}Vo vo) {
        log.info("csm/${modelNameLowerCamel}Api/save params [{}]", vo);
        ${modelNameLowerCamel}Service.saveInfo(vo);
    }
}
