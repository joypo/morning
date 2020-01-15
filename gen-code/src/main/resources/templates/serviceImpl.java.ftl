package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biz.dap.model.base.vo.BasePage;
import ${package.Entity}.${table.upperCamelName}Entity;
import ${package.VO}.${table.upperCamelName}VO;
import ${package.VoMapper}.${table.upperCamelName}VoMapper;
import com.biz.dap.service.base.service.AbstractBaseService;
import ${package.Mapper}.${table.upperCamelName}Mapper;
import ${package.Service}.${table.upperCamelName}Service;
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${table.upperCamelName}ServiceImpl extends AbstractBaseService<${table.upperCamelName}Mapper, ${table.upperCamelName}Entity, ${table.upperCamelName}VO, ${table.upperCamelName}VoMapper> implements ${table.upperCamelName}Service {

    @Override
    public Page<${table.upperCamelName}VO> selectPageList(BasePage<${table.upperCamelName}VO> pageRequest) {
        IPage<${table.upperCamelName}Entity> page = this.baseMapper.selectPageList(pageRequest, pageRequest.getParam());
        return getPage(page.getTotal(), ${table.upperCamelName}VoMapper.INSTANCE.toVos(page.getRecords()));
    }
}