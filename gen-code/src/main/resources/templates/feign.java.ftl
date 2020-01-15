package ${package.Feign};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biz.dap.common.base.response.HttpResponse;
import ${package.FeignDegraded}.${table.upperCamelName}FeignClientDegraded;
import com.biz.dap.model.base.vo.BasePage;
import ${package.VO}.${table.upperCamelName}VO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ${table.upperCamelName}FeignClient
 *
 * @author ${author}
 * @date ${date}
 */
@FeignClient(value = "${feignServiceName}", fallback = ${table.upperCamelName}FeignClientDegraded.class)
public interface ${table.upperCamelName}FeignClient {

    @PostMapping("api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/save")
    HttpResponse save(@RequestBody ${table.upperCamelName}VO vo);

    @GetMapping("api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/findById")
    HttpResponse<${table.upperCamelName}VO> findById(@RequestParam("id") String id);

    @GetMapping("api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/deleteById")
    HttpResponse deleteById(@RequestParam("id") String id);

    @PostMapping("api<#if package.ModuleName??>/${package.ModuleName}</#if>/${table.lowerCamelName}/page")
    HttpResponse<Page<${table.upperCamelName}VO>> selectPageList(@RequestBody BasePage<${table.upperCamelName}VO> pageRequest);
}