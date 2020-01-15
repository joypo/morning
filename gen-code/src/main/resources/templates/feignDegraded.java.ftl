package ${package.FeignDegraded};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biz.dap.common.base.response.HttpResponse;
import ${package.Feign}.${table.upperCamelName}FeignClient;
import com.biz.dap.model.base.vo.BasePage;
import ${package.VO}.${table.upperCamelName}VO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ${table.upperCamelName}FeignClientDegraded
 *
 * @author ${author}
 * @date ${date}
 */
@Component
@Slf4j
public class ${table.upperCamelName}FeignClientDegraded implements ${table.upperCamelName}FeignClient {

    @Override
    public HttpResponse save(${table.upperCamelName}VO vo) {
        log.error("熔断了");
        return null;
    }

    @Override
    public HttpResponse<${table.upperCamelName}VO> findById(String id) {
        log.error("熔断了");
        return null;
    }

    @Override
    public HttpResponse deleteById(String id) {
        log.error("熔断了");
        return null;
    }

    @Override
    public HttpResponse<Page<${table.upperCamelName}VO>> selectPageList(BasePage<${table.upperCamelName}VO> pageRequest) {
        log.error("熔断了");
        return null;
    }
}