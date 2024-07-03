package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.program.entity.DtsAddress;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 修改管理员信息并上传头像。
     * 该接口用于更新管理员的各项信息，包括头像、密码、用户名等，并支持批量角色分配。
     * 使用POST请求方法，通过@RequestParam注解从请求中获取参数。
     *
     * @param id            管理员ID，用于定位要更新的管理员信息。
     * @param avatar        管理员头像，以MultipartFile形式接收，可为空。如果提供，将上传新头像。
     * @param password      新密码，可选。如果提供，将加密后更新密码。
     * @param username      新用户名，可选。如果提供，将更新用户名。
     * @param lastLoginIp   最后登录IP，可选。如果提供，将更新最后登录IP。
     * @param lastLoginTime 最后登录时间，可选。如果提供，将更新最后登录时间。
     * @param roleIds       角色ID字符串，可选。如果提供，将更新管理员的角色分配。
     * @param desc          管理员描述，可选。如果提供，将更新管理员描述。
     * @param tel           联系电话，可选。如果提供，将更新管理员的联系电话。
     * @param mail          电子邮件，可选。如果提供，将更新管理员的电子邮件。
     * @return ResponseEntity对象，包含更新结果信息。成功时返回HTTP状态200和更新成功的消息；管理员不存在时返回HTTP状态404和相应错误消息；更新失败时返回HTTP状态500和错误详情。
     * @throws IOException 如果文件上传过程中发生错误。
     */
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
            // 根据ID查询管理员是否存在
            DtsAdmin adminById = dtsAdminService.getAdminById(id);
            if (adminById == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("管理员不存在");
            }
            // 如果上传了头像，则进行头像上传并更新管理员头像信息
            if (avatar != null && !avatar.isEmpty()) {
                String uploadDtsAdmin = dtsAdminService.uploadDtsAdmin(avatar);
                adminById.setAvatar(uploadDtsAdmin);
            }
            // 更新管理员信息，包括密码（如果提供了新密码）、用户名、最后登录信息等
            adminById.setUsername(username);
            if (password != null && !password.isEmpty()) {
                adminById.setPassword(passwordEncoder.encode(password));
            }
            adminById.setLastLoginIp(lastLoginIp);
            adminById.setLastLoginTime(new Date());
            adminById.setRoleIds(roleIds);
            adminById.setDesc(desc);
            adminById.setTel(tel);
            adminById.setMail(mail);

            // 调用服务层方法更新管理员信息
            dtsAdminService.updateadmin(adminById);

            // 返回HTTP状态200和成功更新的消息
            return ResponseEntity.ok("管理员信息更新成功");

        } catch (Exception e) {
            // 如果更新过程中发生异常，返回HTTP状态500和异常信息
            // 返回更新失败的响应
            // 返回更新失败信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失败: " + e.getMessage());
        }
    }

    /**
     * 删除管理员。
     * 通过接收管理员信息，调用服务层方法进行管理员删除操作。
     * 如果删除成功，返回HTTP状态200和成功消息；
     * 如果删除失败（管理员不存在），返回HTTP状态404和错误消息。
     *
     * @param record 包含待删除管理员信息的请求体。
     * @return 删除操作的结果，以ResponseEntity封装，包含HTTP状态码和响应消息。
     */
    @ApiOperation(value = "删除管理员")
    @PostMapping("/deleteadmin")
    public ResponseEntity<String> deleteAdmin(@RequestBody DtsAdmin record) {
        // 调用服务层方法，尝试根据管理员ID删除管理员
        // 调用服务层方法删除管理员
        boolean deleteById = dtsAdminService.deleteById(record.getId());
        if (deleteById) {
            // 如果删除成功，返回HTTP状态200和成功删除的消息
            // 返回HTTP状态200和成功删除的消息
            return ResponseEntity.ok("管理员删除成功");
        } else {
            // 如果删除失败（管理员不存在），返回HTTP状态404和错误消息
            // 返回HTTP状态404和找不到管理员的消息
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到管理员");
        }
    }

    /**
     * 通过用户名进行模糊查询管理员信息。
     * <p>
     * 该接口使用GET方法，并通过@RequestParam注解接收可选的username参数。
     * 如果提供了username参数，则查询与之匹配的管理员；如果未提供，则返回所有管理员。
     * 使用@ApiOperation注解来说明该接口的作用和API文档中显示的名称。
     * 使用@GetMapping注解来指定该方法处理GET请求的URL路径。
     *
     * @param username 可选参数，用于模糊查询管理员用户名。
     * @return 返回包含管理员列表的ResponseEntity对象，HTTP状态码为200。
     */
    @ApiOperation(value = "模糊查询管理员")
    @GetMapping("/selectadminByLike")
    public ResponseEntity<List<DtsAdmin>> selectadminByLike(@RequestParam(required = false) String username) {
        // 调用服务层方法，根据用户名模糊查询管理员
        List<DtsAdmin> adminByName = dtsAdminService.getAdminByNameFuzzy(username);
        // 返回HTTP状态200和查询结果
        return ResponseEntity.ok(adminByName);
    }

    /**
     * 分页查询管理员信息。
     * 使用GET方法，通过管理员对象条件和分页参数进行查询。
     * 返回包含管理员列表和总条数的Map对象。
     *
     * @param dtsAdmin 管理员对象，包含查询条件。
     * @param pageNum  当前页码，默认为1。
     * @param pageSize 每页条数，默认为10。
     * @return Map对象，包含管理员列表（list）和总条数（total）。
     */
    @ApiOperation(value = "分页查询管理员")
    @GetMapping("/selectadminByPage")
    public Map<String, Object> selectadminByPage(DtsAdmin dtsAdmin,
                                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回数据的Map对象
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();

        // 开始分页查询，通过PageHelper插件实现
        // 开始分页查询，这里使用了PageHelper插件来实现分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法查询管理员列表
        // 调用服务层方法查询广告信息，根据dtsAd中的条件进行查询
        List<DtsAdmin> list = dtsAdminService.selectadmin();

        // 使用PageInfo对查询结果进行包装，获取分页信息
        // 使用PageInfo对查询结果进行包装，获取分页信息
        PageInfo<DtsAdmin> info = new PageInfo<>(list);

        // 将总条数和管理员列表分别放入Map对象中
        // 将查询结果的总条数和广告列表分别放入返回的Map中
        map.put("data", info.getTotal());
        map.put("list", list);

        return map;

    }
}
