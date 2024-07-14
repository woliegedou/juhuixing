package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsHistoricalSearch;

import java.util.List;

public interface DtsHistoricalSearchService {

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
