package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsFeedback;
import com.ruoyi.program.mapper.DtsFeedbackMapper;
import com.ruoyi.program.service.DtsFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DtsFeedbackServiceimpl implements DtsFeedbackService {
    @Autowired
    private DtsFeedbackMapper dtsFeedbackMapper;

    /**
     * 根据条件查询DtsFeedback列表。
     *
     * @param dtsFeedback 查询条件对象，可以为空，根据条件筛选反馈信息。
     * @return 返回符合条件的DtsFeedback列表。
     */
    @Override
    public List<DtsFeedback> selectDtsFeedbackList(DtsFeedback dtsFeedback) {
        // 调用服务层方法，根据传入的条件查询DtsFeedback列表
        return dtsFeedbackMapper.selectDtsFeedbackList(dtsFeedback);
    }
}
