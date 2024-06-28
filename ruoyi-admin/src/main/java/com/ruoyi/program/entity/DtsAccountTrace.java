package com.ruoyi.program.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 账户流水表(DtsAccountTrace)实体类
 *
 * @author makejava
 * @since 2024-06-20 20:54:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dts_account_trace")
public class DtsAccountTrace implements Serializable {
    private static final long serialVersionUID = -28818062188372794L;

    @Id
    @ApiModelProperty(value = "主键id", required = true)
    @NotNull
    private Integer id;
    /**
     * 操作流水
     */
    @ApiModelProperty(value = "操作流水", required = true)
    @NotBlank
    private String traceSn;
    /**
     * 用户表的用户ID
     */
    @ApiModelProperty(value = "用户表的用户ID", required = true)
    @NotBlank
    private Integer userId;
    /**
     * 操作类型 0:系统结算 1:用户提现
     */
    @ApiModelProperty(value = "操作类型 0:系统结算 1:用户提现", required = true)
    @NotBlank
    private Integer type;
    /**
     * 操作金额
     */
    @ApiModelProperty(value = "操作金额", required = true)
    @NotBlank
    private Double amount;
    /**
     * 总申请金额
     */
    @ApiModelProperty(value = "总申请金额", required = true)
    @NotBlank
    private Double totalAmount;
    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "申请时间", required = true)
    @NotBlank
    private Date addTime;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 短信提取码
     */
    @ApiModelProperty(value = "短信提取码")
    private String smsCode;
    /**
     * 审批状态
     */
    @ApiModelProperty(value = "审批状态", required = true)
    @NotBlank
    private Integer status;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String traceMsg;
    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "审批时间", required = true)
    @NotBlank
    private Date updateTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 修改者
     */
    @ApiModelProperty(value = "修改者")
    private String updatedBy;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除")
    private Long isDelete;
    /**
     * 关联管理用户表
     */
    @ApiModelProperty(value = "管理用户表")
    private List<DtsUser> dtsUserList;
}

