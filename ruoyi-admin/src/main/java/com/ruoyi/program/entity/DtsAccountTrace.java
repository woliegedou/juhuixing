package com.ruoyi.program.entity;

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
    private Integer type;
    /**
     * 操作金额
     */
    private Double amount;
    /**
     * 总申请金额
     */
    private Double totalAmount;
    /**
     * 申请时间
     */
    private Date addTime;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 短信提取码
     */
    private String smsCode;
    /**
     * 审批状态
     */
    private Integer status;
    /**
     * 消息内容
     */
    private String traceMsg;
    /**
     * 审批时间
     */
    private Date updateTime;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改者
     */
    private String updatedBy;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 逻辑删除
     */
    private Long isDelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTraceSn() {
        return traceSn;
    }

    public void setTraceSn(String traceSn) {
        this.traceSn = traceSn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTraceMsg() {
        return traceMsg;
    }

    public void setTraceMsg(String traceMsg) {
        this.traceMsg = traceMsg;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

}

