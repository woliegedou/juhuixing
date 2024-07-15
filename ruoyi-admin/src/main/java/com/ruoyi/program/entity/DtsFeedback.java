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
 * 意见反馈表(DtsFeedback)实体类
 *
 * @author makejava
 * @since 2024-07-15 09:40:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api(tags = "意见反馈")
@Table(name = "dts_feedback")
public class DtsFeedback implements Serializable {
    private static final long serialVersionUID = -33897880577796625L;

    @Id
    @ApiModelProperty(value = "反馈id", required = true)
    @Excel(name = "反馈id")
    @NonNull
    private Integer id;
    /**
     * 用户表的用户ID
     */
    @ApiModelProperty(value = "用户表的用户ID", required = true)
    @Excel(name = "用户表的用户ID")
    @NonNull
    private Integer userId;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", required = true)
    @Excel(name = "用户名称")
    @NotBlank
    private String username;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    @Excel(name = "手机号")
    @NotBlank
    private String mobile;
    /**
     * 反馈类型
     */
    @ApiModelProperty(value = "反馈类型", required = true)
    @Excel(name = "反馈类型")
    @NotBlank
    private String feedType;
    /**
     * 反馈内容
     */
    @ApiModelProperty(value = "反馈内容", required = true)
    @Excel(name = "反馈内容")
    @NotBlank
    private String content;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    @NonNull
    private Integer status;
    /**
     * 是否含有图片
     */
    @ApiModelProperty(value = "是否含有图片", required = true)
    @NonNull
    private Integer hasPicture;
    /**
     * 图片地址列表，采用JSON数组格式
     */
    @ApiModelProperty(value = "图片地址列表，采用JSON数组格式", required = true)
    @Excel(name = "图片地址列表，采用JSON数组格式")
    @NotBlank
    private String picUrls;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
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

