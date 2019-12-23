package com.icicle.masterdb.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * @author liumingming
 *         Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {
    /**
     * 持久化数据
     *
     * @param model 数据对象
     * @return
     */
    int save(T model);

    /**
     * 批量持久化
     *
     * @param models 数据对象集合
     * @return
     */
    int save(List<T> models);

    /**
     * 通过主鍵刪除数据
     *
     * @param id id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 批量刪除数据
     *
     * @param ids id集合 eg：ids -> “1,2,3,4”
     * @return
     */
    int deleteByIds(String ids);

    /**
     * 更新数据
     *
     * @param model 数据对象
     * @return
     */
    int update(T model);

    /**
     * 通过ID查找数据
     *
     * @param id id
     * @return
     */
    T findById(Integer id);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param record
     * @return
     * @throws TooManyResultsException
     */
    T selectOne(T record) throws TooManyResultsException;

    /**
     * 通过多个ID查找
     *
     * @param ids eg：ids -> “1,2,3,4”
     * @return
     */
    List<T> findByIds(String ids);

    /**
     * 根据条件查找数据
     *
     * @param condition 查找条件
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 根据条件查找数据
     *
     * @param condition 查找条件
     * @return
     * @throws TooManyResultsException
     */
    T findOneByCondition(Condition condition) throws TooManyResultsException;

    /**
     * 根据条件查找数据条数
     *
     * @param condition 查找条件
     * @return
     */
    int findCountByCondition(Condition condition);

    /**
     * 获取全部数据
     *
     * @return
     */
    List<T> findAll();
}