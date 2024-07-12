package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsCommissionManagement;

import java.util.List;

public interface DtsCommissionManagementService {


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
     * 模糊查询佣金管理
     *
     * @param dtsCommissionManagement
     * @return
     */
    List<DtsCommissionManagement> selectDtsCommissionManagement(DtsCommissionManagement dtsCommissionManagement );

}
