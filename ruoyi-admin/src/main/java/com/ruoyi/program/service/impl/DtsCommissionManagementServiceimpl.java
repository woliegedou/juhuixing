package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsCommissionManagement;
import com.ruoyi.program.mapper.DtsCommissionManagementMapper;
import com.ruoyi.program.service.DtsCommissionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DtsCommissionManagementServiceimpl implements DtsCommissionManagementService {

    @Autowired
    private DtsCommissionManagementMapper dtsCommissionManagementMapper;

    /**
     * 插入Dts佣金管理信息。
     * 本方法通过调用dtsCommissionManagementMapper的insertDtsCommissionManagement方法，
     * 实现对DtsCommissionManagement对象的插入操作。此方法是对接口方法的实现，
     * 其具体逻辑由MyBatis的映射器框架完成，本方法主要用于提供一个插入数据的接口，
     * 对外隐藏了数据访问的细节。
     *
     * @param dtsCommissionManagement 待插入的Dts佣金管理对象，包含所有的属性信息。
     * @return 返回插入操作的影响行数。
     */
    @Override
    public int insertDtsCommissionManagement(DtsCommissionManagement dtsCommissionManagement) {
        return dtsCommissionManagementMapper.insertDtsCommissionManagement(dtsCommissionManagement);
    }

    /**
     * 更新Dts佣金管理信息。
     * 此方法通过调用dtsCommissionManagementMapper中的updateDtsCommissionManagement方法，
     * 实现对Dts佣金管理数据的更新操作。
     *
     * @param dtsCommissionManagement 包含需要更新的Dts佣金管理信息的对象。
     * @return 返回更新操作的影响行数，具体数值由dtsCommissionManagementMapper层返回。
     */
    @Override
    public int updateDtsCommissionManagement(DtsCommissionManagement dtsCommissionManagement) {
        return dtsCommissionManagementMapper.updateDtsCommissionManagement(dtsCommissionManagement);
    }

    /**
     * 根据条件查询DtsCommissionManagement表中的数据
     *
     * @return 返回符合条件的数据列表，列表中的每个元素都是DtsCommissionManagement类型的对象
     */
    @Override
    public List<DtsCommissionManagement> selectDtsCommissionManagementList() {
        // 调用Mapper接口的方法，根据传入的条件查询DtsCommissionManagement表中的数据
        return dtsCommissionManagementMapper.selectDtsCommissionManagementList();
    }


    /**
     * 通过DtsCommissionManagement对象查询佣金管理信息。
     *
     * @return 返回匹配条件的佣金管理信息列表。
     */
    @Override
    public List<DtsCommissionManagement> selectDtsCommissionManagement(DtsCommissionManagement dtsCommissionManagement) {
        return dtsCommissionManagementMapper.selectDtsCommissionManagemen(dtsCommissionManagement);
    }
}
