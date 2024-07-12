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
import java.util.Date;
import java.io.Serializable;

/**
 * 会员收藏(DtsMemberCollection)实体类
 *
 * @author makejava
 * @since 2024-07-11 17:02:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api(tags = "会员收藏")
@Table(name = "dts_member_collection")
public class DtsMemberCollection implements Serializable {
    private static final long serialVersionUID = -28745339895412248L;
    /**
     * 会员收藏id
     */
    @Id
    @ApiModelProperty(value = "会员收藏主键", required = true)
    @Excel(name = "收藏id")
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
     * 商品id
     */
    @ApiModelProperty(value = "商品id", required = true)
    @Excel(name = "商品id")
    @NonNull
    private Long commodityId;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "添加时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
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


}

