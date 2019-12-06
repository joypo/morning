package ${serviceImplPackage};

import com.biz.primus.common.pagehelper.Page;
import com.biz.primus.common.pagehelper.PageHelper;
import com.biz.primus.common.pagehelper.PageInfo;
import ${voPackage}.${modelNameUpperCamel}Vo;
import ${reqVoPackage}.${modelNameUpperCamel}ReqVo;
import com.biz.primus.ms.base.service.IdService;
import ${entityPackage}.${modelNameUpperCamel};
import ${mapperPackage}.${modelNameUpperCamel}Mapper;
import ${servicePackage}.${modelNameUpperCamel}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}ServiceImpl
 */
@Service
@Slf4j
public class ${modelNameUpperCamel}ServiceImpl extends BaseServiceImpl<${modelNameUpperCamel}Mapper, ${modelNameUpperCamel}, ${modelNameUpperCamel}Vo> implements ${modelNameUpperCamel}Service {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<${modelNameUpperCamel}Vo> selectPageList(${modelNameUpperCamel}ReqVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getRows());
        Page<${modelNameUpperCamel}Vo> page = this.baseMapper.selectPageList(vo);
        return new PageInfo<>(page);
    }

    /**
     * 新增or编辑${table.comment}信息
     *
     * @param vo
     * @return
     */
    @Override
    public void saveInfo(${modelNameUpperCamel}Vo vo) {
        saveVo(vo);
    }

    /**
     * 根据id获取${table.comment}信息
     *
     * @param id
     * @return
     */
    @Override
    public ${modelNameUpperCamel}Vo findById(Long id) {
        ${modelNameUpperCamel} entity = this.baseMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return null;
        }
        ${modelNameUpperCamel}Vo vo = new ${modelNameUpperCamel}Vo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
