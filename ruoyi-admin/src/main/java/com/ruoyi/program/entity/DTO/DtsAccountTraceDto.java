package com.ruoyi.program.entity.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class DtsAccountTraceDto {
    private String traceSn;
    private Integer userId;
    private Integer type;
    private Double amount;
    private Double totalAmount;
    private Date addTime;
    private Integer status;

}
