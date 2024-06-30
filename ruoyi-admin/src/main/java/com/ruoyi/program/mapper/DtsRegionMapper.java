package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsAd;
import com.ruoyi.program.entity.DtsRegion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface DtsRegionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List<DtsRegion> DtsRegionid(Integer id);
    /**
     * 查询查询全部数据
     *
     * @return 对象列表
     */
    List<DtsRegion> dtsRegionids();

    /**
     * 插入行政区域
     *
     * @param dtsRegion
     * @return
     */
    int insertdtsRegion(DtsRegion dtsRegion);

    /**
     * 更新行政区域
     *
     * @param dtsRegion
     * @return
     */
    int updatedtsRegion(DtsRegion dtsRegion);

    /**
     * 查询指定行数据
     *
     * @param dtsRegion 查询条件
     * @return 对象列表
     */
    List<DtsRegion> queryAllByLimit(DtsRegion dtsRegion);

    /**
     * 通过主键删除行数据
     *
     * @param id
     * @return
     */
    int deletedtsRegion(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param name 实例对象
     * @return 对象列表
     */
    List<DtsRegion> searchMajorByName(@Param("name") String name);
}
