package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsHistoricalSearch;
import com.ruoyi.program.mapper.DtsHistoricalSearchMapper;
import com.ruoyi.program.service.DtsHistoricalSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DtsHistoricalSearchServiceimpl implements DtsHistoricalSearchService {

    @Autowired
    private DtsHistoricalSearchMapper dtsHistoricalSearchMapper;

    /**
     * 插入历史搜索记录。
     *
     * @param dtsHistoricalSearch 待插入的历史搜索对象。
     * @return 返回插入操作的影响行数。
     */
    @Override
    public boolean inserthistoricalsearch(DtsHistoricalSearch dtsHistoricalSearch) {
        return dtsHistoricalSearchMapper.inserthistoricalsearch(dtsHistoricalSearch);
    }

    /**
     * 更新历史搜索记录。
     *
     * @param dtsHistoricalSearch 待更新的历史搜索对象。
     * @return 返回更新操作的影响行数。
     */
    @Override
    public int updatehistoricalsearch(DtsHistoricalSearch dtsHistoricalSearch) {
        return dtsHistoricalSearchMapper.updatehistoricalsearch(dtsHistoricalSearch);
    }

    /**
     * 查询历史搜索记录。
     *
     * @param dtsHistoricalSearch 包含查询条件的历史搜索对象。
     * @return 返回匹配条件的历史搜索记录列表。
     */
    @Override
    public List<DtsHistoricalSearch> selectDtsHistoricalSearch(DtsHistoricalSearch dtsHistoricalSearch) {
        return dtsHistoricalSearchMapper.selectDtsHistoricalSearch(dtsHistoricalSearch);
    }
}
