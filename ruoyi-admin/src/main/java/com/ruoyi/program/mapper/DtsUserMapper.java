package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户表(DtsUser)表数据库访问层
 *
 * @author makejava
 * @since 2024-06-27 09:41:52
 */
@Mapper
public interface DtsUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DtsUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param dtsUser 查询条件
     * @return 对象列表
     */
    List<DtsUser> queryAllByLimit(DtsUser dtsUser);

    /**
     * 统计总行数
     *
     * @param dtsUser 查询条件
     * @return 总行数
     */
    long count(DtsUser dtsUser);

    /**
     * 新增数据
     *
     * @param dtsUser 实例对象
     * @return 影响行数
     */
    int insert(DtsUser dtsUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DtsUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DtsUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DtsUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DtsUser> entities);

    /**
     * 修改数据
     *
     * @param dtsUser 实例对象
     * @return 影响行数
     */
    int update(DtsUser dtsUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

