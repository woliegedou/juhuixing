package com.ruoyi.program.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 佣金管理(DtsCommissionManagement)实体类
 *
 * @author makejava
 * @since 2024-07-10 09:08:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api(tags = "佣金管理")
@Table(name = "dts_commission_management")
public class DtsCommissionManagement implements Serializable {
    private static final long serialVersionUID = -77173987549295249L;
    /**
     * 记录id
     */
    @Id
    @NonNull
    @ApiModelProperty(value = "记录id", required = true)
    private Long id;
    /**
     * 申请流水
     */
    @ApiModelProperty(value = "申请流水", required = true)
    @NonNull
    private Long applicationFlow;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型(0系统结算，1用户申请)", required = true)
    @NotBlank
    private String types;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    @NonNull
    private Long userId;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", required = true)
    @NotBlank
    private String phone;
    /**
     * 提现金额
     */
    @ApiModelProperty(value = "提现金额", required = true)
    @NonNull
    private Double withdrawal;
    /**
     * 已提总额
     */
    @ApiModelProperty(value = "已提总额", required = true)
    @NonNull
    private Double amountWithdrawn;
    /**
     * 审批状态(0提现申请,1审批拒绝,2审批通过)
     */
    @ApiModelProperty(value = "审批状态(0提现申请,1审批拒绝,2审批通过)", required = true)
    @NotBlank
    private String approvalStatus;
    /**
     * 审批备注
     */
    @ApiModelProperty(value = "审批备注", required = true)
    private String approvalRemarks;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    /**
     * 修改者
     */
    @ApiModelProperty(value = "修改者")
    private String updatedBy;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除")
    private Long isDelete;
    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息")
    private DtsUser user;

}

