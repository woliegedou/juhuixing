package com.ruoyi.program.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
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
 * 管理员表(DtsAdmin)实体类
 *
 * @author makejava
 * @since 2024-07-02 09:42:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api(tags = "管理员表")
@Table(name = "dts_admin")
public class DtsAdmin implements Serializable {
    private static final long serialVersionUID = 255046486953937927L;
    @Id
    @ApiModelProperty(value = "管理员ID", required = true)
    @NotNull
    private Integer id;
    /**
     * 管理员名称
     */
    @ApiModelProperty(value = "管理员名称", required = true)
    @NotBlank
    private String username;
    /**
     * 管理员密码
     */
    @ApiModelProperty(value = "管理员密码", required = true)
    @NotBlank
    private String password;
    /**
     * 最近一次登录IP地址
     */
    @ApiModelProperty(value = "最近一次登录IP地址")
    private String lastLoginIp;
    /**
     * 最近一次登录时间
     */
    @ApiModelProperty(value = "最近一次登录时间")
    private Date lastLoginTime;
    /**
     * 头像图片
     */
    @ApiModelProperty(value = "头像图片", required = true)
    @NotBlank
    private String avatar;
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
    /**
     * 角色列表
     */
    @ApiModelProperty(value = "角色列表", required = true)
    @NotBlank
    private String roleIds;
    /**
     * 用户描述
     */
    @ApiModelProperty(value = "用户描述", required = true)
    @NotBlank
    private String desc;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", required = true)
    @NotBlank
    private String tel;
    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址", required = true)
    @NotBlank
    private String mail;

}

