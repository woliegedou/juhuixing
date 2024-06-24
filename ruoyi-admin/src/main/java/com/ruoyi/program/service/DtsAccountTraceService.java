package com.ruoyi.program.service;

import com.ruoyi.program.mapper.DtsAccountTraceMapper;

import java.util.List;

public interface DtsAccountTraceService {
    /**
     * 根据条件查询总记录数
     *
     * @param example
     * @return
     */
    long countByExample(DtsAccountTraceMapper example);

    /**
     * 根据条件删除
     *
     * @param example
     * @return
     */
    int deleteByExample(DtsAccountTraceMapper example);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insertDtsAccountTrace(DtsAccountTraceMapper record);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insertSelective(DtsAccountTraceMapper record);

    /**
     * 根据条件查询
     *
     * @param example
     * @return
     */
    List<DtsAccountTraceMapper> selectByExample(DtsAccountTraceMapper example);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    DtsAccountTraceMapper selectByPrimaryKey(Integer id);

    /**
     * 根据条件更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(DtsAccountTraceMapper record, DtsAccountTraceMapper example);

    /**
     * 根据条件更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(DtsAccountTraceMapper record, DtsAccountTraceMapper example);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DtsAccountTraceMapper record);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(DtsAccountTraceMapper record);

}
