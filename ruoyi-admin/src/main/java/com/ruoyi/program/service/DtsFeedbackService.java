package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsFeedback;

import java.util.List;

public interface DtsFeedbackService {

    /**
     * 查询意见反馈列表
     * @param dtsFeedback
     * @return
     */
    List<DtsFeedback> selectDtsFeedbackList(DtsFeedback dtsFeedback);
}
