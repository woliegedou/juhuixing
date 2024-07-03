package com.ruoyi.program.entity;

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
 * 文章信息表(DtsArticle)实体类
 *
 * @author makejava
 * @since 2024-07-03 10:34:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dts_article")
@Api(tags = "文章信息表")
public class DtsArticle implements Serializable {
    private static final long serialVersionUID = 184082889579616859L;

    /**
     * 主键ID
     */
    @Id
    @ApiModelProperty(value = "主键ID", required = true)
    @NonNull
    private Integer id;
    /**
     * 信息类型
     */
    @ApiModelProperty(value = "信息类型", required = true)
    @NotBlank
    private String type;
    /**
     * 信息标题
     */
    @ApiModelProperty(value = "信息标题", required = true)
    @NotBlank
    private String title;
    /**
     * 信息内容,富文本格式
     */
    @ApiModelProperty(value = "信息内容,富文本格式", required = true)
    @NotBlank
    private String content;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    @NotBlank
    private Date addTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    @NotBlank
    private Date updateTime;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除", required = true)
    @NotBlank
    private Integer deleted;

}

