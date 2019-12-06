package ${servicePackage};

import com.biz.primus.common.pagehelper.PageInfo;
import ${voPackage}.${modelNameUpperCamel}Vo;
import ${reqVoPackage}.${modelNameUpperCamel}ReqVo;
import ${entityPackage}.${modelNameUpperCamel};

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}Service
 */
public interface ${modelNameUpperCamel}Service {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<${modelNameUpperCamel}Vo> selectPageList(${modelNameUpperCamel}ReqVo vo);

    /**
     * 新增or编辑${table.comment}信息
     *
     * @param vo
     * @return
     */
    void saveInfo(${modelNameUpperCamel}Vo vo);

    /**
     * 根据id获取${table.comment}信息
     *
     * @param id
     * @return
     */
    ${modelNameUpperCamel}Vo findById(Long id);
}