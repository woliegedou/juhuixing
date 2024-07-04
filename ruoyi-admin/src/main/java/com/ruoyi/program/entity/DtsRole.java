package com.ruoyi.program.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
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
 * 角色表(DtsRole)实体类
 *
 * @author makejava
 * @since 2024-07-04 09:29:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "角色表")
@Table(name = "dts_role")
public class DtsRole implements Serializable {
    private static final long serialVersionUID = 290594428526244668L;
    /**
     * 角色ID
     */
    @Id
    @ApiModelProperty(value = "角色ID", required = true)
    @NonNull
    private Integer id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank
    private String name;
    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述", required = true)
    @NotBlank
    private String desc;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用", required = true)
    @NonNull
    private Integer enabled;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;
}

