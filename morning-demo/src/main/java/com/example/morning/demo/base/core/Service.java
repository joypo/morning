package com.example.morning.demo.base.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 *
 * @author sunx
 */
public interface Service<T> {
    /**
     * 保存
     *
     * @param model
     */
    void save(T model);

    /**
     * 批量保存
     *
     * @param models
     */
    void save(List<T> models);

    /**
     * 主键删除
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 通过主键集合删除
     *
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 更新
     *
     * @param model
     */
    void update(T model);

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     *
     * @param fieldName
     * @param value
     * @return
     * @throws TooManyResultsException
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 通过id集合查询
     *
     * @param ids
     * @return
     */
    List<T> findByIds(List<Long> ids);

    /**
     * 根据条件查找
     *
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 获取所有
     *
     * @return
     */
    List<T> findAll();
}
