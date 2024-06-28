package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsAd;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DtsAdService {
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
    boolean deleteById(Integer id);

    /**
     * 上传DTS广告的接口。
     *
     * @param file 上传的文件对象，包含广告的详细信息。
     * @return 返回上传结果的字符串表示。成功上传返回特定的成功标识，失败则返回错误信息。
     */
    String uploadDtsAd(MultipartFile file);
}
