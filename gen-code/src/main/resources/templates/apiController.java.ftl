package ${package.ApiController};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biz.dap.common.base.response.HttpResponse;
import com.biz.dap.model.base.vo.BasePage;
import ${package.VO}.${table.upperCamelName}VO;
import ${package.Service}.${table.upperCamelName}Service;
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
@RequestMapping("/api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}")
@Slf4j
public class ${table.upperCamelName}ApiController {

    @Autowired
    private ${table.upperCamelName}Service ${table.lowerCamelName}Service;

    @PostMapping("save")
    public HttpResponse save(@RequestBody ${table.upperCamelName}VO vo) {
        log.info("/api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/save param [{}]", vo);
        try {
            ${table.lowerCamelName}Service.saveInfo(vo);
        } catch (Exception ex) {
            log.error("/api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/save error [{}]", ex);
            return HttpResponse.error();
        }
        return HttpResponse.success();
    }

    @GetMapping("findById")
    public HttpResponse<${table.upperCamelName}VO> findById(@RequestParam("id") String id) {
        log.info("/api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/findById id [{}]", id);
        return HttpResponse.success(${table.lowerCamelName}Service.findInfoById(id));
    }

    @GetMapping("deleteById")
    public HttpResponse deleteById(@RequestParam("id") String id) {
        log.info("/api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/deleteById id [{}]", id);
        try {
            ${table.lowerCamelName}Service.deleteInfoById(id);
        } catch (Exception ex) {
            log.error("/api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/deleteById error [{}]", ex);
            return HttpResponse.error();
        }
        return HttpResponse.success();
    }

    @PostMapping("page")
    public HttpResponse<Page<${table.upperCamelName}VO>> selectPageList(@RequestBody BasePage<${table.upperCamelName}VO> pageRequest) {
        log.info("/api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/page param [{}]", pageRequest);
        return HttpResponse.success(${table.lowerCamelName}Service.selectPageList(pageRequest));
    }
}