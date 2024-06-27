package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsAccountTrace;
import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.NotNull;
import java.util.List;

@Mapper
public interface DtsAccountTraceMapper {
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
    int deleteByPrimaryKey(@NotNull Integer id);

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
     * 根据主键查询
     *
     * @param id
     * @return
     */
    DtsAccountTrace selectByPrimaryKey(Integer id);

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
