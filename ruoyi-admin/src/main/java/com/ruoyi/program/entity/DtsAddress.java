package com.ruoyi.program.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 收货地址表(DtsAddress)实体类
 *
 * @author makejava
 * @since 2024-06-30 16:40:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dts_address")
@ApiModel(value = "收货地址表(DtsAddress)")
public class DtsAddress implements Serializable {
    private static final long serialVersionUID = -99222174998927434L;

    /**
     * 收货地址ID
     */
    @ApiModelProperty(value = "收货地址ID", required = true)
    private Integer id;
    /**
     * 收货人名称
     */
    @ApiModelProperty(value = "收货人名称", required = true)
    @NotBlank
    private String name;
    /**
     * 用户表的用户ID
     */
    @ApiModelProperty(value = "用户表的用户ID", required = true)
    @NotNull
    private Integer userId;
    /**
     * 行政区域表的省ID
     */
    @ApiModelProperty(value = "行政区域表的省ID", required = true)
    @NotNull
    private Integer provinceId;
    /**
     * 行政区域表的市ID
     */
    @ApiModelProperty(value = "行政区域表的市ID", required = true)
    @NotNull
    private Integer cityId;
    /**
     * 行政区域表的区县ID
     */
    @ApiModelProperty(value = "行政区域表的区县ID", required = true)
    @NotNull
    private Integer areaId;
    /**
     * 具体收货地址
     */
    @ApiModelProperty(value = "具体收货地址", required = true)
    @NotBlank
    private String address;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    /**
     * 是否默认地址
     */
    @ApiModelProperty(value = "是否默认地址", required = true)
    @NotNull
    private Integer isDefault;
    /**
     * 创建时间
     */
    private Date addTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 逻辑删除
     */
    private Integer deleted;
    /**
     * 关联用户信息
     */
    @ApiModelProperty(value = "关联用户信息")
    private List<DtsUser> dtsUserList;
    /**
     * 关联区域信息
     */
    @ApiModelProperty(value = "关联区域信息")
    private List<DtsRegion> dtsRegionList;


}

