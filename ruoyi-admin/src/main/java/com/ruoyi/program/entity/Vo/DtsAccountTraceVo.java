package com.ruoyi.program.entity.Vo;

import com.ruoyi.program.entity.DtsUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtsAccountTraceVo {

    private Integer id;

    private String traceSn;

    private Integer userId;

    private Integer type;

    private Double amount;

    private Double totalAmount;

    private Data addTime;

    private String status;

    private List<DtsUser> dtsUserList;
}
