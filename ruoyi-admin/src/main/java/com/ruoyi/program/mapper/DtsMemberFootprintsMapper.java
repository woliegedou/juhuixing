package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsMemberCollection;
import com.ruoyi.program.entity.DtsMemberFootprints;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtsMemberFootprintsMapper {
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
