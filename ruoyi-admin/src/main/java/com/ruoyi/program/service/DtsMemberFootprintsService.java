package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsMemberCollection;
import com.ruoyi.program.entity.DtsMemberFootprints;

import java.util.List;

public interface DtsMemberFootprintsService {


    /**
     * 插入会员足迹
     *
     * @param dtsMemberFootprints
     * @return
     */
    int insertDtsMemberCollection(DtsMemberFootprints dtsMemberFootprints);

    /**
     * 修改会员足迹
     *
     * @param dtsMemberFootprints
     * @return
     */
    int updateMemberCollection(DtsMemberFootprints dtsMemberFootprints);

    /**
     * 查询会员足迹
     *
     * @param dtsMemberFootprints
     * @return
     */
    List<DtsMemberFootprints> selectDtsMemberCollection(DtsMemberFootprints dtsMemberFootprints);
}
