package ${mapperPackage};

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.biz.primus.common.pagehelper.Page;
import ${entityPackage}.${modelNameUpperCamel};
import ${voPackage}.${modelNameUpperCamel}Vo;
import ${reqVoPackage}.${modelNameUpperCamel}ReqVo;
import ${entityPackage}.${modelNameUpperCamel};

import java.util.List;
/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}Mapper
 */
public interface ${modelNameUpperCamel}Mapper extends BaseMapper<${modelNameUpperCamel}> {

    /**
     * ${table.comment}分页查询
     *
     * @param vo
     * @return
     */
    Page<${modelNameUpperCamel}Vo> selectPageList(${modelNameUpperCamel}ReqVo vo);
}