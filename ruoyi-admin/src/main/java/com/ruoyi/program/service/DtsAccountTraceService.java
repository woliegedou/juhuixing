package com.ruoyi.program.service;

import com.ruoyi.program.entity.DTO.DtsAccountTraceDto;
import com.ruoyi.program.entity.DtsAccountTrace;
import com.ruoyi.program.mapper.DtsAccountTraceMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DtsAccountTraceService {
    /**
     * 根据条件查询总记录数
     *
     * @param example
     * @return
     */
    List<DtsAccountTrace> countByExample(DtsAccountTrace example);


    /**
     * 根据条件删除
     *
     * @param example
     * @return
     */
    int deleteByExample(DtsAccountTrace example);

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
    boolean insertDtsAccountTrace(DtsAccountTrace record);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insertSelective(DtsAccountTrace record);

    /**
     * 根据条件查询
     *
     * @param example
     * @return
     */
    List<DtsAccountTrace> selectByExample(DtsAccountTrace example);

    /**
     * 根据条件更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(DtsAccountTrace record, DtsAccountTrace example);

    /**
     * 根据条件更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(DtsAccountTrace record, DtsAccountTrace example);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DtsAccountTrace record);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(DtsAccountTrace record);

}
