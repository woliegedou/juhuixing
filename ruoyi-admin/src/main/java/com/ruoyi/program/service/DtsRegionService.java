package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsAd;
import com.ruoyi.program.entity.DtsRegion;

import java.util.List;

public interface DtsRegionService {

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
     * @return 对象列表
     */
    List<DtsRegion> queryAllByLimit(DtsRegion dtsRegion);

    /**
     * 通过主键删除行数据
     *
     * @param id
     * @return
     */
    boolean deletedtsRegion(Long id);

    /**
     * 构建树
     *
     * @param dtsRegion
     * @return
     */
    public List<DtsRegion> buildDeptTree(List<DtsRegion> dtsRegion);
}
