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
import java.io.Serializable;
import java.util.Date;

/**
 * 会员足迹(DtsMemberFootprints)实体类
 *
 * @author makejava
 * @since 2024-07-12 16:46:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api(tags = "会员足迹")
@Table(name = "dts_member_footprints")
public class DtsMemberFootprints implements Serializable {
    private static final long serialVersionUID = 243047388613274326L;
    /**
     * 足迹id
     */
    @NonNull
    @Id
    @ApiModelProperty(value = "足迹id", required = true)
    @Excel(name = "足迹id")
    private Long id;
    /**
     * 用户id
     */
    @NonNull
    @ApiModelProperty(value = "用户id", required = true)
    @Excel(name = "用户id")
    private Long userId;
    /**
     * 商品id
     */
    @NonNull
    @ApiModelProperty(value = "商品id", required = true)
    @Excel(name = "商品id")
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

