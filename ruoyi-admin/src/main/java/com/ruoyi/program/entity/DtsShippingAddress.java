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
 * (DtsShippingAddress)实体类
 *
 * @author makejava
 * @since 2024-07-11 16:04:58
 */
@Data
@Api(tags = "收货地址")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dts_shipping_address")
public class DtsShippingAddress implements Serializable {
    private static final long serialVersionUID = -73363708335283515L;
    /**
     * 地址id
     */
    @Id
    @ApiModelProperty(value = "地址id", required = true)
    @Excel(name = "地址id")
    @NonNull
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    @Excel(name = "用户id")
    @NonNull
    private Long userId;
    /**
     * 收货人名称
     */
    @ApiModelProperty(value = "收货人名称", required = true)
    @Excel(name = "收货人名称")
    @NotBlank
    private String nameConsignee;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", required = true)
    @Excel(name = "手机号码")
    @NotBlank
    private String phoneNumber;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", required = true)
    @Excel(name = "地址")
    @NotBlank
    private String location;
    /**
     * 默认(0是，1否)
     */
    @ApiModelProperty(value = "默认(0是，1否)", required = true)
    @Excel(name = "默认(0是，1否)")
    @NotBlank
    private Long acquiesce;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
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
    @Excel(name = "修改时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除")
    private Long isDelete;


}

