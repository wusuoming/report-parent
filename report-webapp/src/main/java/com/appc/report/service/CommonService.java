package com.appc.report.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * 增删改查公共方法
 *
 * @param <T>
 */
public interface CommonService<T> {

    /**
     * 新增
     */
    int insert(T entity);

    /**
     * 根据主键更新对象
     *
     * @param t 实体对象
     * @return 修改的数据条数
     */
    int updateById(T t);

    /**
     * 根据主键删除数据
     *
     * @param pk 主键
     * @return 删除的数据条数
     */
    int deleteById(Serializable pk);

    /**
     * 根据主键获数据
     *
     * @param pk 主键
     * @return 所获取的对象
     */
    T getById(Serializable pk);

    /**
     * 根据查询对象获取实体对象
     *
     * @param queryDto 查询对象
     * @param <Dto>    查询对象泛型
     * @return 所获取的对象
     */
    <Dto> T getEntity(Dto queryDto);

    /**
     * 根据主键和字段名获数据
     *
     * @param pk        主键
     * @param fieldName 需要获取的字段
     * @param <V>       返回的对象泛型
     * @return 查询后的数据
     */
    <V> V getValueById(@Param("id") Object pk, @Param("fieldName") String fieldName);

    /**
     * 批量新增数据
     *
     * @param list 待新增的数据
     * @return 新增的条数
     */
    int insertBatch(List<T> list);


    /**
     * 获取所有数据
     *
     * @return 返回的数据
     */
    List<T> getEntityList();

    /**
     * 根据查询对象获取数据
     *
     * @param queryDto 查询对象
     * @param <Dto>    查询对象泛型
     * @return 返回的数据
     */
    <Dto> List<T> getEntityList(Dto queryDto);


    /**
     * 分页查询数据
     *
     * @param pageable 分页对象
     * @return 返回的数据
     */
    Page<T> getEntityListForPage(Pageable pageable);

    /**
     * 根据查询条件分页查询数据
     *
     * @param queryDto 查询条件
     * @param pageable 分页条件
     * @param <Dto>    查询条件泛型
     * @return 返回的数据
     */
    <Dto> Page<T> getEntityListForPage(Dto queryDto, Pageable pageable);


    /**
     * 获取所有数据
     *
     * @return 返回的数据
     */
    Long getEntityCount();

    /**
     * 根据查询对象获取数据
     *
     * @param queryDto 查询对象
     * @param <Dto>    查询对象泛型
     * @return 返回的数据
     */
    <Dto> Long getEntityCount(Dto queryDto);


}
