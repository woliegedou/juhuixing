package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsMemberManagement;

import java.util.List;

public interface DtsMemberManagementService {
    /**
     * 插入会员信息
     *
     * @param dtsMemberManagement
     * @return
     */
    int insertDtsMemberManagement(DtsMemberManagement dtsMemberManagement);

    /**
     * 更新会员信息
     *
     * @param dtsMemberManagement
     * @return
     */
    int updateMemberManagement(DtsMemberManagement dtsMemberManagement);


    /**
     * 查询全部会员信息
     *
     * @return
     */
    List<DtsMemberManagement> selectDtsMemberManagementAll();


    /**
     * 模糊查询会员信息
     *
     * @param dtsMemberManagement
     * @return
     */
    List<DtsMemberManagement> selectDtsMemberManagementByNameOrNumberFuzzy(DtsMemberManagement dtsMemberManagement);

    /**
     * 分页查询全部信息
     * @param dtsMemberManagement
     * @return
     */
    List<DtsMemberManagement> selectDtsMemberManagementByType(DtsMemberManagement dtsMemberManagement);


}
