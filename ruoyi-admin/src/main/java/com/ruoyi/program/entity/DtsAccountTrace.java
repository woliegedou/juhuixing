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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DtsAccountTrace other = (DtsAccountTrace) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTraceSn() == null ? other.getTraceSn() == null : this.getTraceSn().equals(other.getTraceSn()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
                && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
                && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
                && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
                && (this.getSmsCode() == null ? other.getSmsCode() == null : this.getSmsCode().equals(other.getSmsCode()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getTraceMsg() == null ? other.getTraceMsg() == null : this.getTraceMsg().equals(other.getTraceMsg()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
                && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
                && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()))
                && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTraceSn() == null) ? 0 : getTraceSn().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getSmsCode() == null) ? 0 : getSmsCode().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTraceMsg() == null) ? 0 : getTraceMsg().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedBy() == null) ? 0 : getUpdatedBy().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}

