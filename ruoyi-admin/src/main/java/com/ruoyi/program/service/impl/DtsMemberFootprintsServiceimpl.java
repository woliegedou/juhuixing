package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsMemberCollection;
import com.ruoyi.program.entity.DtsMemberFootprints;
import com.ruoyi.program.mapper.DtsMemberFootprintsMapper;
import com.ruoyi.program.service.DtsMemberFootprintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DtsMemberFootprintsServiceimpl implements DtsMemberFootprintsService {

    @Autowired
    private DtsMemberFootprintsMapper dtsMemberFootprintsMapper;

    /**
     * 插入会员足迹信息。
     * 通过调用DtsMemberFootprintsMapper的insertDtsMemberCollection方法，将会员的足迹数据插入数据库。
     *
     * @param dtsMemberFootprints 会员足迹信息对象，包含要插入的数据。
     * @return 返回插入操作的影响行数。
     */
    @Override
    public int insertDtsMemberCollection(DtsMemberFootprints dtsMemberFootprints) {
        return dtsMemberFootprintsMapper.insertDtsMemberCollection(dtsMemberFootprints);
    }

    /**
     * 更新会员足迹信息。
     * 通过调用DtsMemberFootprintsMapper的updateMemberCollection方法，更新已存在的会员足迹数据。
     *
     * @param dtsMemberFootprints 会员足迹信息对象，包含要更新的数据。
     * @return 返回更新操作的影响行数。
     */
    @Override
    public int updateMemberCollection(DtsMemberFootprints dtsMemberFootprints) {
        return dtsMemberFootprintsMapper.updateMemberCollection(dtsMemberFootprints);
    }

    /**
     * 查询会员足迹信息。
     * 通过调用DtsMemberFootprintsMapper的selectDtsMemberCollection方法，根据条件查询会员的足迹数据。
     *
     * @param dtsMemberFootprints 会员足迹信息对象，包含查询条件。
     * @return 返回查询结果的列表，包含符合条件的所有会员足迹信息。
     */
    @Override
    public List<DtsMemberFootprints> selectDtsMemberCollection(DtsMemberFootprints dtsMemberFootprints) {
        return dtsMemberFootprintsMapper.selectDtsMemberCollection(dtsMemberFootprints);
    }
}
