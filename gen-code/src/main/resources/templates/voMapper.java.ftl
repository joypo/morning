package ${package.VoMapper};

import com.biz.dap.model.base.vo.mapper.BaseVoMapper;
import ${package.Entity}.${table.upperCamelName}Entity;
import ${package.VO}.${table.upperCamelName}VO;
import org.mapstruct.Mapper;

/**
 * ${table.comment!}VoMapper 接口
 *
 * @author ${author}
 * @date ${date}
 */
@Mapper(componentModel = "spring")
public interface ${table.upperCamelName}VoMapper extends BaseVoMapper<${table.upperCamelName}Entity, ${table.upperCamelName}VO> {
}
