package com.ruoyi.program.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
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

/**
 * 会员管理(DtsMemberManagement)实体类
 *
 * @author makejava
 * @since 2024-07-09 14:09:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api(tags = "会员管理")
@Table(name = "dts_member_management")
public class DtsMemberManagement implements Serializable {
    private static final long serialVersionUID = -95106218712765454L;
    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键", required = true)
    @Id
    @NonNull
    @Excel(name = "用户主键")
    private Long id;
    /**
     * 用户名
     */
    @Excel(name = "用户名")
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank
    private String username;
    /**
     * 手机号
     */
    @Excel(name = "手机号")
    @ApiModelProperty(value = "手机号", required = true)
    private String number;
    /**
     * 性别
     */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    @ApiModelProperty(value = "性别", required = true)
    private String gender;
    /**
     * 用户等级
     */
    @Excel(name = "用户等级", readConverterExp = "0=普通会员,1=代理")
    @ApiModelProperty(value = "用户等级", required = true)
    @NotBlank
    private String userlevel;
    /**
     * 状态（0可用,1代理申请）
     */
    @Excel(name = "状态", readConverterExp = "0=可用,1=代理申请")
    @ApiModelProperty(value = "状态（0可用,1代理申请）", required = true)
    private Long state;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间",dateFormat="yyyy-MM-dd HH:mm:ss")
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
    @Excel(name = "修改时间",dateFormat="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除")
    private Long isDelete;


}

