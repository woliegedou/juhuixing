package com.ruoyi.program.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.program.entity.DtsAdmin;
import com.ruoyi.program.service.DtsAdminService;
import com.ruoyi.program.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "管理员表")
@RequestMapping("/admin")
public class DtsAdminController {
    @Autowired
    private DtsAdminService dtsAdminService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * 此函数用于通过POST请求添加新的管理员。
     * <p>
     * 请求路径: /addadmin
     * <p>
     * 接收一个JSON化的DtsAdmin对象，其中包含管理员信息，包括未加密的密码。
     * 函数首先使用密码编码器对密码进行加密，然后尝试将加密后的管理员信息插入数据库。
     *
     * @return AjaxResult 对象，表示操作结果
     * - 成功时，返回状态码为200，消息为"管理员成功添加，ID为: [管理员ID]"，数据部分包含管理员ID
     * - 失败时，返回状态码为500，消息为"无法添加管理员: [错误信息]"，数据部分包含异常信息
     * @throws Exception 如果在插入过程中发生任何异常
     */
    @ApiOperation(value = "添加管理员并上传头像")
    @PostMapping("/addadmin")
    public AjaxResult addAdminWithAvatar(@RequestParam("avatar") MultipartFile avatar,
                                         @RequestParam("password") String password,
                                         @RequestParam("username") String username,
                                         @RequestParam("lastLoginIp") String lastLoginIp,
                                         @RequestParam("lastLoginTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date lastLoginTime,
                                         @RequestParam("roleIds") String roleIds,
                                         @RequestParam("desc") String desc,
                                         @RequestParam("tel") String tel,
                                         @RequestParam("mail") String mail) throws IOException {
        if (StringUtils.isEmpty(password)) {
            // 如果密码为空或null，直接返回错误信息
            return AjaxResult.error("无法添加管理员: 密码不能为空");
        }

        try {
            // 先上传头像并获取图片URL
            String avatarUrl = dtsAdminService.uploadDtsAdmin(avatar);
            if (avatarUrl == null || avatarUrl.isEmpty()) {
                return AjaxResult.error("头像上传失败");
            }

            DtsAdmin dtsAdmin = new DtsAdmin();
            Date date = new Date();
            // 设置管理员的头像URL
            dtsAdmin.setAvatar(avatarUrl);
            dtsAdmin.setLastLoginTime(lastLoginTime);
            dtsAdmin.setAddTime(date);
            dtsAdmin.setUpdateTime(date);
            dtsAdmin.setLastLoginIp(lastLoginIp);
            dtsAdmin.setRoleIds(roleIds);
            dtsAdmin.setUsername(username);
            dtsAdmin.setMail(mail);
            dtsAdmin.setTel(tel);
            dtsAdmin.setDesc(desc);
            dtsAdmin.setDeleted(0);

            // 加密管理员的原始密码，先检查密码是否非空
            if (StringUtils.isNotBlank(password)) {
                String encodedPassword = passwordEncoder.encode(password);
                dtsAdmin.setPassword(encodedPassword);
            } else {
                // 虽然前面已经检查过，但这里作为二次确认，防止意外情况
                return AjaxResult.error("无法添加管理员: 密码不能为空");
            }

            // 尝试将加密后的管理员信息插入数据库
            Long adminId = dtsAdminService.insertadmin(dtsAdmin);
            if (adminId != null && adminId > 0) {
                return AjaxResult.success("管理员成功添加，ID为: " + adminId);
            } else {
                return AjaxResult.error("无法添加管理员: 数据库操作失败");
            }
        } catch (Exception e) {
            // 返回更具体的异常信息，如果可能，区分是密码处理异常还是数据库操作异常等
            return AjaxResult.error("无法添加管理员: " + (e.getMessage() != null ? e.getMessage() : "未知错误"));
        }
    }

    /**
     * 此函数通过POST请求执行管理员查询操作。
     * <p>
     * 使用了Swagger的{@link ApiOperation}注解，其value属性指明该接口的主要功能为"查询管理员"。
     *
     * @return 如果查询成功，将返回一个{@link AjaxResult}对象，其中包含状态码表示成功，并封装了管理员列表数据。
     * 如果发生异常，返回的{@link AjaxResult}对象会包含错误状态码和异常信息，如"无法查询管理员: 异常详细信息"。
     * @apiNote 此函数调用 方法获取管理员列表，
     * 并根据执行结果构造相应的Ajax响应。
     */
    @PostMapping("/queryadmin")
    @ApiOperation(value = "查询管理员")
    public AjaxResult queryAdmin() {
        try {
            // 查询管理员列表
            List<DtsAdmin> selectadmin = dtsAdminService.selectadmin();

            // 返回成功结果，包含管理员列表
            return AjaxResult.success(selectadmin);
        } catch (Exception e) {
            // 返回错误结果，包含异常信息
            return AjaxResult.error("无法查询管理员:" + e.getMessage());
        }
    }

    @ApiOperation(value = "修改管理员信息并上传头像")
    @PostMapping("/updateadmin")
    public ResponseEntity<?> updateAdminWithAvatar(
            @RequestParam(value = "id", required = true) int id,
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "lastLoginIp", required = false) String lastLoginIp,
            @RequestParam(value = "lastLoginTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date lastLoginTime,
            @RequestParam(value = "roleIds", required = false) String roleIds,
            @RequestParam(value = "desc", required = false) String desc,
            @RequestParam(value = "tel", required = false) String tel,
            @RequestParam(value = "mail", required = false) String mail) throws IOException {

        try {
            DtsAdmin adminById = dtsAdminService.getAdminById(id);
            if (adminById == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("管理员不存在");
            }
            if (avatar != null && !avatar.isEmpty()) {
                String uploadDtsAdmin = dtsAdminService.uploadDtsAdmin(avatar);
                adminById.setAvatar(uploadDtsAdmin);
            }
            adminById.setUsername(username);
            adminById.setPassword(passwordEncoder.encode(password));
            adminById.setLastLoginIp(lastLoginIp);
            adminById.setLastLoginTime(new Date());
            adminById.setRoleIds(roleIds);
            adminById.setDesc(desc);
            adminById.setTel(tel);
            adminById.setMail(mail);

            dtsAdminService.updateadmin(adminById);

            return ResponseEntity.ok("管理员信息更新成功");

        } catch (Exception e) {
            // 返回更新失败的响应
            // 返回更新失败信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失败: " + e.getMessage());
        }
    }
}
