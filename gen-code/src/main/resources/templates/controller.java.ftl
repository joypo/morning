package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biz.dap.common.base.response.HttpResponse;
import ${package.Feign}.${table.upperCamelName}FeignClient;
import com.biz.dap.model.base.vo.BasePage;
import ${package.VO}.${table.upperCamelName}VO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @date ${date}
 */
@RestController
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}")
@Slf4j
public class ${table.upperCamelName}Controller {

    @Autowired
    private ${table.upperCamelName}FeignClient ${table.lowerCamelName}FeignClient;

    @PostMapping("save")
    public HttpResponse save(@RequestBody ${table.upperCamelName}VO vo) {
        log.info("<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/save param [{}]", vo);
        return ${table.lowerCamelName}FeignClient.save(vo);
    }

    @GetMapping("findById")
    public HttpResponse<${table.upperCamelName}VO> findById(@RequestParam("id") String id) {
        log.info("<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/findById id [{}]", id);
        return ${table.lowerCamelName}FeignClient.findById(id);
    }

    @GetMapping("deleteById")
    public HttpResponse deleteById(@RequestParam("id") String id) {
        log.info("<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/deleteById id [{}]", id);
        return ${table.lowerCamelName}FeignClient.deleteById(id);
    }

    @PostMapping("page")
    public HttpResponse<Page<${table.upperCamelName}VO>> selectPageList(@RequestBody BasePage<${table.upperCamelName}VO> pageRequest) {
        log.info("<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/page param [{}]", pageRequest);
        return ${table.lowerCamelName}FeignClient.selectPageList(pageRequest);
    }
}