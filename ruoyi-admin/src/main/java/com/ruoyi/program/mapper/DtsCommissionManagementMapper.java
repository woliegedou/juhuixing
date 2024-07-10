package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsCommissionManagement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtsCommissionManagementMapper {
    /**
     * 插入佣金管理
     *
     * @param dtsCommissionManagement
     * @return
     */
    int insertDtsCommissionManagement(DtsCommissionManagement dtsCommissionManagement);

    /**
     * 修改佣金管理
     *
     * @param dtsCommissionManagement
     * @return
     */
    int updateDtsCommissionManagement(DtsCommissionManagement dtsCommissionManagement);
    /**
     * 查询佣金管理
     *
     * @return
     */
    List<DtsCommissionManagement> selectDtsCommissionManagementList();

    /**
     * 模糊查询
     * @param dtsCommissionManagement
     * @return
     */
    List<DtsCommissionManagement> selectDtsCommissionManagemen(DtsCommissionManagement dtsCommissionManagement);
}
