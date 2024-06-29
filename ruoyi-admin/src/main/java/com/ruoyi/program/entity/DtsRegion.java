package com.ruoyi.program.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 行政区域表(DtsRegion)实体类
 *
 * @author makejava
 * @since 2024-06-29 14:21:46
 */
@ApiModel(value = "行政区域表")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "dts_region")
public class DtsRegion implements Serializable {
    private static final long serialVersionUID = -62113335430628854L;

    /**
     * 行政区域ID
     */
    @Id
    @ApiModelProperty(value = "行政区域ID", required = true)
    @NotNull
    private Long id;

    /**
     * 行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0
     */
    @NotNull
    @ApiModelProperty(value = "行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0", required = true)
    private Long pid;

    /**
     * 行政区域名称
     */
    @ApiModelProperty(value = "行政区域名称", required = true)
    @NotBlank
    private String name;

    /**
     * 行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县
     */
    @ApiModelProperty(value = "行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县", required = true)
    @NotBlank
    private Integer type;

    /**
     * 行政区域编码
     */
    @ApiModelProperty(value = "行政区域编码", required = true)
    @NotBlank
    private Integer code;

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
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
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
    private Integer isDelete;

    /**
     * 子节点
     */
    @ApiModelProperty(value = "子节点")
    private List<DtsRegion> children;


}

