package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsMemberCollection;
import com.ruoyi.program.mapper.DtsMemberCollectionMapper;
import com.ruoyi.program.service.DtsMemberCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DtsMemberCollectionServiceimpl implements DtsMemberCollectionService {
    @Autowired
    private DtsMemberCollectionMapper dtsMemberCollectionMapper;

    /**
     * 插入DtsMemberCollection信息。
     * <p>
     * 本方法通过调用dtsMemberCollectionMapper的insertDtsMemberCollection方法，来实现DtsMemberCollection数据的插入操作。
     * 主要用于将新的成员集合信息添加到数据库中，以便后续的同步任务分配和管理。
     *
     * @param dtsMemberCollection 待插入的成员集合对象，包含成员的相关信息。
     * @return 返回插入操作的影响行数，用于判断插入操作是否成功。
     */
    @Override
    public int insertDtsMemberCollection(DtsMemberCollection dtsMemberCollection) {
        return dtsMemberCollectionMapper.insertDtsMemberCollection(dtsMemberCollection);
    }

    /**
     * 更新会员收藏信息。
     * 通过DtsMemberCollection对象来更新数据库中的会员收藏记录。
     *
     * @param dtsMemberCollection 包含需要更新的会员收藏信息的对象。
     * @return 返回更新操作影响的行数。
     */
    @Override
    public int updateMemberCollection(DtsMemberCollection dtsMemberCollection) {
        return dtsMemberCollectionMapper.updateMemberCollection(dtsMemberCollection);
    }

    /**
     * 查询会员收藏信息。
     * 根据提供的DtsMemberCollection对象的条件来查询数据库中的会员收藏记录。
     *
     * @param dtsMemberCollection 包含查询条件的DtsMemberCollection对象。
     * @return 返回符合条件的会员收藏信息列表。
     */
    @Override
    public List<DtsMemberCollection> selectDtsMemberCollection(DtsMemberCollection dtsMemberCollection) {
        return dtsMemberCollectionMapper.selectDtsMemberCollection(dtsMemberCollection);
    }

}
