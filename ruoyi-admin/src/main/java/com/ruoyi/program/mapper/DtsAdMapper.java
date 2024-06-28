package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsAd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DtsAdMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DtsAd queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param dtsAd 查询条件
     * @return 对象列表
     */
    List<DtsAd> queryAllByLimit(DtsAd dtsAd);

    /**
     * 统计总行数
     *
     * @param dtsAd 查询条件
     * @return 总行数
     */
    long count(DtsAd dtsAd);

    /**
     * 新增数据
     *
     * @param dtsAd 实例对象
     * @return 影响行数
     */
    int insert(DtsAd dtsAd);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DtsAd> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DtsAd> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DtsAd> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DtsAd> entities);

    /**
     * 修改数据
     *
     * @param dtsAd 实例对象
     * @return 影响行数
     */
    int update(DtsAd dtsAd);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
