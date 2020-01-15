package ${package.Mapper};

import ${superMapperClassPackage};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${table.upperCamelName}Entity;
import ${package.VO}.${table.upperCamelName}VO;
import org.apache.ibatis.annotations.Param;

/**
 * ${table.comment!}Mapper 接口
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${table.upperCamelName}Mapper extends ${superMapperClass}<${table.upperCamelName}Entity> {

    /**
     * ${table.comment!} 分页
     *
     * @param page
     * @param vo
     * @return
     */
    IPage<${table.upperCamelName}Entity> selectPageList(Page<${table.upperCamelName}VO> page,@Param("vo") ${table.upperCamelName}VO vo);
}
