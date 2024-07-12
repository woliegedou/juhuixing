package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsMemberCollection;

import java.util.List;

public interface DtsMemberCollectionService {

    /**
     * 插入会员收藏
     *
     * @param dtsMemberCollection
     * @return
     */
    int insertDtsMemberCollection(DtsMemberCollection dtsMemberCollection);

    /**
     * 修改会员收藏
     *
     * @param dtsMemberCollection
     * @return
     */
    int updateMemberCollection(DtsMemberCollection dtsMemberCollection);

    /**
     * 查询会员收藏
     *
     * @param dtsMemberCollection
     * @return
     */
    List<DtsMemberCollection> selectDtsMemberCollection(DtsMemberCollection dtsMemberCollection);
}
