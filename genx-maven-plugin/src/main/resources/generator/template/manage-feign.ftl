package ${feignPackage};

import com.biz.primus.common.pagehelper.PageInfo;
import ${degradedPackage}.${modelNameUpperCamel}FeignClientDegraded;
import ${voPackage}.${modelNameUpperCamel}Vo;
import ${reqVoPackage}.${modelNameUpperCamel}ReqVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;

/**
* @author ${author}
* @date ${date}
* @description ${table.comment}feign接口
*/
@FeignClient(qualifier = "${modelNameLowerCamel}FeignClient", name = "service-csm", path = "csm/${modelNameLowerCamel}Api",
        fallback = ${modelNameUpperCamel}FeignClientDegraded.class)
public interface ${modelNameUpperCamel}FeignClient {

    /**
     * ${table.comment}分页查询
     *
     * @param vo
     * @return
     */
    @PostMapping("/selectPageList")
    PageInfo<${modelNameUpperCamel}Vo> selectPageList(@RequestBody ${modelNameUpperCamel}ReqVo vo);


    /**
     * 获取${table.comment}详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    ${modelNameUpperCamel}Vo detail(@RequestParam String id);

    /**
     * 保存${table.comment},新增或编辑
     *
     * @param vo
     */
    @PostMapping("/save")
    void save(@RequestBody ${modelNameUpperCamel}Vo vo);
}
