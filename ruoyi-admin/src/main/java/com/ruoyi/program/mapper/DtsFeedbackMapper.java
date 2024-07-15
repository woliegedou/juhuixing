package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsFeedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtsFeedbackMapper {
    /**
     * 查询意见反馈列表
     * @param dtsFeedback
     * @return
     */
    List<DtsFeedback> selectDtsFeedbackList(DtsFeedback dtsFeedback);
}
