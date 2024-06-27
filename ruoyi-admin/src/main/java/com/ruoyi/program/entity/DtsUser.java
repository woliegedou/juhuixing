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
 * 用户表(DtsUser)实体类
 *
 * @author makejava
 * @since 2024-06-26 16:54:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dts_user")
@ApiModel(value = "用户表")
public class DtsUser implements Serializable {
    private static final long serialVersionUID = 826856804339060629L;
    /**
     * 用户ID
     */
    @Id
    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull
    private Integer id;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", required = true)
    @NotBlank
    private String username;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", required = true)
    @NotBlank
    private String password;
    /**
     * 性别：0 未知， 1男， 2 女
     */
    @ApiModelProperty(value = "性别：0 未知， 1男， 2 女", required = true)
    @NotBlank
    private Integer gender;
    /**
     * 生日
     */
    @ApiModelProperty(value = "生日", required = true)
    @NotBlank
    private Date birthday;
    /**
     * 最近一次登录时间
     */
    @ApiModelProperty(value = "最近一次登录时间", required = true)
    @NotBlank
    private Date lastLoginTime;
    /**
     * 最近一次登录IP地址
     */
    @ApiModelProperty(value = "最近一次登录IP地址", required = true)
    @NotBlank
    private String lastLoginIp;
    /**
     * 用户层级 0 普通用户，1 VIP用户，2 区域代理用户
     */
    @ApiModelProperty(value = "用户层级 0 普通用户，1 VIP用户，2 区域代理用户", required = true)
    @NotBlank
    private Integer userLevel;
    /**
     * 用户昵称或网络名称
     */
    @ApiModelProperty(value = "用户昵称或网络名称", required = true)
    @NotBlank
    private String nickname;
    /**
     * 用户手机号码
     */
    @ApiModelProperty(value = "用户手机号码")
    private String mobile;
    /**
     * 用户头像图片
     */
    @ApiModelProperty(value = "用户头像图片", required = true)
    @NotBlank
    private String avatar;
    /**
     * 微信登录openid
     */
    @ApiModelProperty(value = "微信登录openid")
    private String weixinOpenid;
    /**
     * 0 可用, 1 禁用, 2 注销
     */
    @ApiModelProperty(value = "0 可用, 1 禁用, 2 注销", required = true)
    @NotBlank
    private Integer status;
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


    public boolean hasValidRecentLoginTime() {
        return lastLoginTime != null && lastLoginTime.getTime() > 0;
    }

    public boolean hasValidRecentLoginIp() {
        return lastLoginIp != null && !lastLoginIp.isEmpty();
    }
}

