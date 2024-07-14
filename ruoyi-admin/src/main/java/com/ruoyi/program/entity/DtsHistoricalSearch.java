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
 * 历史搜索(DtsHistoricalSearch)实体类
 *
 * @author makejava
 * @since 2024-07-14 09:25:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api(tags = "历史搜索")
@Table(name = "dts_historical_search")
public class DtsHistoricalSearch implements Serializable {
    private static final long serialVersionUID = 363942646165738438L;
    /**
     * 搜索id
     */
    @ApiModelProperty(value = "搜索id", required = true)
    @NonNull
    @Id
    @Excel(name = "搜索id")
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    @NonNull
    @Excel(name = "用户id")
    private Long userId;
    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字", required = true)
    @NotBlank
    @Excel(name = "关键字")
    private String keywords;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除")
    private Long isDelete;


}

