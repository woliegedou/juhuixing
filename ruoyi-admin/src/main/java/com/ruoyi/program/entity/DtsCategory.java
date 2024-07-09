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
 * 类目表(DtsCategory)实体类
 *
 * @author makejava
 * @since 2024-07-08 16:44:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dts_category")
@Api(tags = "类目表")
public class DtsCategory implements Serializable {
    private static final long serialVersionUID = 961671653444929558L;

    /**
     * 类目ID
     */
    @Id
    @ApiModelProperty(value = "类目ID", required = true)
    @NonNull
    private Long id;
    /**
     * 类目名称
     */
    @ApiModelProperty(value = "类目名称", required = true)
    @NotBlank
    private String name;
    /**
     * 类目关键字，以JSON数组格式
     */
    @ApiModelProperty(value = "类目关键字，以JSON数组格式")
    private String keywords;
    /**
     * 类目广告语介绍
     */
    @ApiModelProperty(value = "类目广告语介绍", required = true)
    @NotBlank
    private String desc;
    /**
     * 父类目ID
     */
    @ApiModelProperty(value = "父类目ID", required = true)
    @NonNull
    private Long pid;
    /**
     * 类目图标
     */
    @ApiModelProperty(value = "类目图标", required = true)
    @NotBlank
    private String iconUrl;
    /**
     * 类目图片
     */
    @ApiModelProperty(value = "类目图片", required = true)
    @NotBlank
    private String picUrl;

    /**
     * 类目级别，level=1表示一级分类，level=2表示二级分类
     */
    @ApiModelProperty(value = "类目级别，level=1表示一级分类，level=2表示二级分类", required = true)
    @NotBlank
    private String level;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    @NonNull
    private Integer sortOrder;
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
    @NonNull
    private Integer deleted;
    /**
     * 子类目列表
     */
    @ApiModelProperty(value = "子类目列表")
    private List<DtsCategory> children;


}

