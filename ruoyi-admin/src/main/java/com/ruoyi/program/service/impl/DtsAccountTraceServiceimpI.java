package com.ruoyi.program.service.impl;

import com.ruoyi.program.mapper.DtsAccountTraceMapper;
import com.ruoyi.program.service.DtsAccountTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DtsAccountTraceServiceimpI implements DtsAccountTraceService {
    @Autowired
    private DtsAccountTraceMapper dtsAccountTraceMapper;

    @Override
    public long countByExample(DtsAccountTraceMapper example) {
        return dtsAccountTraceMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DtsAccountTraceMapper example) {
        return this.dtsAccountTraceMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dtsAccountTraceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertDtsAccountTrace(DtsAccountTraceMapper record) {
        return dtsAccountTraceMapper.insertDtsAccountTrace(record);
    }

    @Override
    public int insertSelective(DtsAccountTraceMapper record) {
        return 0;
    }

    @Override
    public List<DtsAccountTraceMapper> selectByExample(DtsAccountTraceMapper example) {
        return Collections.emptyList();
    }

    @Override
    public DtsAccountTraceMapper selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(DtsAccountTraceMapper record, DtsAccountTraceMapper example) {
        return 0;
    }

    @Override
    public int updateByExample(DtsAccountTraceMapper record, DtsAccountTraceMapper example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(DtsAccountTraceMapper record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(DtsAccountTraceMapper record) {
        return 0;
    }
}
