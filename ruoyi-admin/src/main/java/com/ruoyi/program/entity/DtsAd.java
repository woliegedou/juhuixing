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
import java.util.Date;

/**
 * 广告表(DtsAd)实体类
 *
 * @author makejava
 * @since 2024-06-28 17:25:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "广告表")
@Table(name = "dts_ad")
public class DtsAd implements Serializable {
    private static final long serialVersionUID = 212519883921134250L;

    /**
     * 广告id
     */
    @Id
    @ApiModelProperty(value = "广告id", required = true)
    @NotNull
    private Integer id;
    /**
     * 广告标题
     */
    @ApiModelProperty(value = "广告标题", required = true)
    @NotBlank
    private String name;
    /**
     * 所广告的商品页面或者活动页面链接地址
     */
    @ApiModelProperty(value = "所广告的商品页面或者活动页面链接地址", required = true)
    @NotBlank
    private String link;
    /**
     * 广告宣传图片
     */
    @ApiModelProperty(value = "广告宣传图片", required = true)
    @NotBlank
    private String url;
    /**
     * 广告位置：1则是首页
     */
    @ApiModelProperty(value = "广告位置：1则是首页", required = true)
    @NotBlank
    private Integer position;
    /**
     * 活动内容
     */
    @ApiModelProperty(value = "活动内容", required = true)
    @NotBlank
    private String content;
    /**
     * 广告开始时间
     */
    @ApiModelProperty(value = "广告开始时间", required = true)
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /**
     * 广告结束时间
     */
    @ApiModelProperty(value = "广告结束时间", required = true)
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /**
     * 是否启动
     */
    @ApiModelProperty(value = "是否启动", required = true)
    @NotBlank
    private Integer enabled;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
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

