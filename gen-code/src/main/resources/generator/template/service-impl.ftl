package ${basePackage}.service.impl;

import ${basePackage}.base.core.AbstractService;
import ${basePackage}.dao.${modelNameUpperCamel}Dao;
import ${basePackage}.domain.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}ServiceImpl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Dao ${modelNameLowerCamel}Dao;
}
