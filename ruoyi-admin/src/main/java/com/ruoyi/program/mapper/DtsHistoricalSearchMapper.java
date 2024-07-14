package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsHistoricalSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtsHistoricalSearchMapper {


    /**
     * 插入历史搜索
     * @param dtsHistoricalSearch
     * @return
     */
    boolean inserthistoricalsearch(DtsHistoricalSearch dtsHistoricalSearch);

    /**
     * 修改历史搜索
     * @param dtsHistoricalSearch
     * @return
     */
    int updatehistoricalsearch(DtsHistoricalSearch dtsHistoricalSearch);

    /**
     * 查询历史搜索
     * @param dtsHistoricalSearch
     * @return
     */
    List<DtsHistoricalSearch> selectDtsHistoricalSearch(DtsHistoricalSearch dtsHistoricalSearch);
}
