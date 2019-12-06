package ${degradedPackage};

import com.biz.primus.common.pagehelper.PageInfo;
import ${feignPackage}.${modelNameUpperCamel}FeignClient;
import ${voPackage}.${modelNameUpperCamel}Vo;
import ${reqVoPackage}.${modelNameUpperCamel}ReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
* @author ${author}
* @date ${date}
* @description ${table.comment}feign接口降级
*/
@Component
@Slf4j
public class ${modelNameUpperCamel}FeignClientDegraded implements ${modelNameUpperCamel}FeignClient {

    /**
     * ${table.comment}分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<${modelNameUpperCamel}Vo> selectPageList(${modelNameUpperCamel}ReqVo vo) {
        log.error("${table.comment}分页查询降级熔断了,vo[{}]...", vo);
        return null;
    }


    /**
     * 获取${table.comment}详情
     *
     * @param id
     * @return
     */
    @Override
    public ${modelNameUpperCamel}Vo detail(String id) {
        log.error("获取${table.comment}详情降级熔断了,id[{}]...", id);
        return null;
    }

    /**
     * 保存${table.comment},新增或编辑
     *
     * @param vo
     */
    @Override
    public void save(@RequestBody ${modelNameUpperCamel}Vo vo) {
        log.error("保存${table.comment},新增或编辑降级熔断了,vo[{}]...", vo);
    }
}
