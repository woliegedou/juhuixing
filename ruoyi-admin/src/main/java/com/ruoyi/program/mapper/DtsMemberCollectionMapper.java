package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsMemberCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtsMemberCollectionMapper {
    /**
     * 插入会员收藏
     * @param dtsMemberCollection
     * @return
     */
    int insertDtsMemberCollection(DtsMemberCollection dtsMemberCollection);
    /**
     * 修改会员收藏
     * @param dtsMemberCollection
     * @return
     */
    int updateMemberCollection(DtsMemberCollection dtsMemberCollection);

    /**
     * 查询会员收藏
     * @param dtsMemberCollection
     * @return
     */
    List<DtsMemberCollection> selectDtsMemberCollection(DtsMemberCollection dtsMemberCollection);
}
