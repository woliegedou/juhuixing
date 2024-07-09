package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsMemberManagement;
import com.ruoyi.program.mapper.DtsMemberManagementMapper;
import com.ruoyi.program.service.DtsMemberManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DtsMemberManagementServiceimpl implements DtsMemberManagementService {

    @Autowired
    private DtsMemberManagementMapper dtsMemberManagementMapper;

    /**
     * 插入DtsMemberManagement信息。
     * 本方法通过调用dtsMemberManagementMapper的insertDtsMemberManagement方法，来实现DtsMemberManagement信息的数据库插入操作。
     *
     * @param dtsMemberManagement 待插入的DtsMemberManagement对象，包含完整的成员管理信息。
     * @return 返回插入操作的影响行数，通常情况下，如果插入成功，返回1；如果插入失败，返回0。
     */
    @Override
    public int insertDtsMemberManagement(DtsMemberManagement dtsMemberManagement) {
        // 调用dtsMemberManagementMapper的insertDtsMemberManagement方法插入成员管理信息
        return dtsMemberManagementMapper.insertDtsMemberManagement(dtsMemberManagement);
    }

    /**
     * 更新会员管理信息。
     * <p>
     * 本方法通过调用dtsMemberManagementMapper的updateMemberManagement方法，来更新会员管理表中的数据。
     * 它接受一个DtsMemberManagement对象作为参数，该对象包含了需要更新的会员管理信息。
     * 方法返回一个整型值，表示更新操作影响的行数。
     *
     * @param dtsMemberManagement 包含待更新会员管理信息的对象。
     * @return 更新操作影响的行数。
     */
    @Override
    public int updateMemberManagement(DtsMemberManagement dtsMemberManagement) {
        return dtsMemberManagementMapper.updateMemberManagement(dtsMemberManagement);
    }

    /**
     * 获取所有DtsMemberManagement对象的列表。
     * <p>
     * 本方法通过调用dtsMemberManagementMapper的selectDtsMemberManagementAll方法，
     * 来查询并返回所有DtsMemberManagement实体的列表。
     * 这对于需要检索系统中所有成员管理信息的场景非常有用，例如在系统初始化或数据备份时。
     *
     * @return List<DtsMemberManagement> 返回一个包含所有DtsMemberManagement对象的列表。
     */
    @Override
    public List<DtsMemberManagement> selectDtsMemberManagementAll() {
        return dtsMemberManagementMapper.selectDtsMemberManagementAll();
    }

    /**
     * 根据名称和号码模糊查询Dts成员管理信息。
     * 通过调用dtsMemberManagementMapper的selectDtsMemberManagementByNameFuzzy方法，实现对Dts成员管理数据的模糊查询。
     * 主要用于在成员管理模块中，提供根据成员名称或号码进行模糊搜索的功能支持。
     *
     * @return 返回匹配条件的Dts成员管理信息列表。
     */
    @Override
    public List<DtsMemberManagement> selectDtsMemberManagementByNameOrNumberFuzzy(DtsMemberManagement dtsMemberManagement) {
        return dtsMemberManagementMapper.selectDtsMemberManagementByNameOrNumberFuzzy(dtsMemberManagement);

    }

    @Override
    public List<DtsMemberManagement> selectDtsMemberManagementByType(DtsMemberManagement dtsMemberManagement) {
        return dtsMemberManagementMapper.selectDtsMemberManagementByType(dtsMemberManagement);
    }

}