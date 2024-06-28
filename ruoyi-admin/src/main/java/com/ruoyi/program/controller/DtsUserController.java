package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.program.entity.DtsUser;
import com.ruoyi.program.mapper.DtsUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "用户管理")
@RequestMapping("/userStatement")
public class DtsUserController {

    private static final Logger logger = LoggerFactory.getLogger(DtsUserController.class);

    @Autowired
    private DtsUserMapper dtsUserMapper;


    /**
     * 通过POST请求插入DtsUser记录。
     *
     * @param record 用户信息对象，包含待插入的数据。
     * @return 返回一个ResponseEntity对象，包含操作结果的消息和HTTP状态码。
     * @ApiOperation 注解用于说明该接口的功能。
     * @PostMapping 注解指定该方法处理POST请求的URL路径。
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("/insertDtsUser")
    public ResponseEntity<String> insertDtsUser(@RequestBody DtsUser record) {
        // 调用dtsUserMapper的insert方法插入用户记录。
        int insert = dtsUserMapper.insert(record);
        if (insert > 0) {
            // 如果插入成功，记录日志并返回成功消息及HTTP状态码。
            logger.info("成功添加用户: {}", record.getUsername());
            return new ResponseEntity<>("用户添加成功", HttpStatus.CREATED);
        } else {
            // 如果插入失败，记录日志并返回失败消息及HTTP状态码。
            logger.warn("用户添加失败: {}", record.getUsername());
            return new ResponseEntity<>("用户添加失败", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 分页查询用户信息。
     * 通过此接口可以实现用户信息的分页查询，支持根据用户条件进行筛选。
     * 使用PageHelper进行分页处理，返回分页后的用户列表及总条数。
     *
     * @param dtsUser  查询条件对象，包含用户信息的过滤条件。
     * @param pageNum  当前页码，默认为1。
     * @param pageSize 每页显示的记录数，默认为10。
     * @return 返回一个Map对象，包含用户列表和总记录数。
     * "data" 键存储总记录数，"list" 键存储用户列表。
     */
    @ApiOperation(value = "分页查询用户")
    @GetMapping("/selectDtsUser")
    public Map<String, Object> selectDtsUser(DtsUser dtsUser,
                                             @RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回数据的Map对象
        HashMap<String, Object> map = new HashMap<>();

        // 开始分页查询，设置当前页码和每页记录数
        PageHelper.startPage(pageNum, pageSize);

        // 根据查询条件查询所有用户信息，限制返回的结果集大小
        List<DtsUser> list = dtsUserMapper.queryAllByLimit(dtsUser);

        // 创建PageInfo对象，用于包装分页信息
        PageInfo<DtsUser> info = new PageInfo<>(list);

        // 将总记录数和用户列表分别放入map中
        map.put("data", info.getTotal());
        map.put("list", list);

        // 返回包含分页信息的Map对象
        return map;
    }

    /**
     * 通过API操作注解和HTTP GET方法，定义了一个接口用于查询所有DtsUser对象。
     * 此方法没有接收任何参数，直接返回查询结果的列表。
     *
     * @return List<DtsUser> 返回一个DtsUser对象的列表，包含所有用户的数据。
     */
    @ApiOperation(value = "查询全部用户")
    @GetMapping("/selectAllDtsUser")
    public List<DtsUser> selectAllDtsUser() {
        // 调用dtsUserMapper的queryAllByLimit方法，传入一个新实例的DtsUser对象，查询所有用户数据
        return dtsUserMapper.queryAllByLimit(new DtsUser());
    }

    /**
     * 通过模糊查询条件获取DtsUser列表
     *
     * @param username  可选参数，用于按用户名进行模糊查询
     * @param nickename 可选参数，用于按昵称进行模糊查询
     * @return 返回匹配的DtsUser列表。如果查询结果为空，返回状态码为NOT_FOUND的ResponseEntity
     */
    @ApiOperation(value = "模糊查询")
    @GetMapping("/selectDtsUserByLike")
    public ResponseEntity<List<DtsUser>> selectDtsUserByLike(@RequestParam(required = false) String username,
                                                             @RequestParam(required = false) String nickename) {
        // 创建一个DtsUser对象，用于设置查询条件
        DtsUser dtsUser = new DtsUser();
        // 设置用户名查询条件
        dtsUser.setUsername(username);
        // 设置昵称查询条件
        dtsUser.setNickname(nickename);

        // 调用dtsUserMapper的queryAllByLimit方法进行模糊查询
        List<DtsUser> dtsUsers = dtsUserMapper.queryAllByLimit(dtsUser);
        // 如果查询结果为空，返回状态码为NOT_FOUND的ResponseEntity
        if (dtsUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            // 如果查询结果不为空，返回查询结果
            return ResponseEntity.ok(dtsUsers);
        }
    }

    /**
     * 修改用户信息接口。
     * 使用@ApiOperation注解来说明该接口的作用是更新用户信息。
     *
     * @return 返回一个ResponseEntity对象，其中包含更新操作的结果信息和HTTP状态码。
     * 如果用户更新成功，返回状态码201和消息"用户修改成功"；
     * 如果更新失败，返回状态码400和消息"用户修改失败"。
     * PostMapping注解指明该方法处理的HTTP请求方法为POST，请求的URL为/updateDtsUser。
     * RequestBody注解表明请求体中的数据将被绑定到方法参数record上，用于传递更新用户的信息。
     */
    @ApiOperation(value = "修改用户")
    @PostMapping("/updateDtsUser")
    public ResponseEntity<String> updateDtsUser(@RequestBody DtsUser record) {
        // 调用dtsUserMapper的update方法尝试更新用户信息
        int update = dtsUserMapper.update(record);
        // 根据update方法的返回值判断用户信息更新是否成功
        if (update == 1) {
            // 如果更新成功，记录日志并返回成功消息及状态码
            logger.info("成功修改用户: {}", record);
            return new ResponseEntity<>("用户修改成功", HttpStatus.CREATED);
        } else {
            // 如果更新失败，记录日志并返回失败消息及状态码
            logger.warn("用户修改失败: {}", record);
            return new ResponseEntity<>("用户修改失败", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/deleteDtsUser")
    public ResponseEntity<String> deleteDtsUser(@RequestParam Integer id) {
        try {
            // 调用dtsUserMapper的deleteById方法尝试删除用户信息
            int delete = dtsUserMapper.deleteById(id);

            // 根据delete方法的返回值判断用户信息删除是否成功
            if (delete == 1) {
                // 如果删除成功，记录日志并返回成功消息及状态码
                logger.info("成功删除用户: {}", id);
                return ResponseEntity.ok("用户删除成功");
            } else {
                // 如果删除失败，记录日志并返回失败消息及状态码
                logger.warn("用户删除失败: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户删除失败，用户ID不存在");
            }
        } catch (Exception e) {
            // 处理可能的异常情况，记录日志并返回服务器错误状态码
            logger.error("删除用户时发生错误: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除用户时发生错误");
        }
    }

}
