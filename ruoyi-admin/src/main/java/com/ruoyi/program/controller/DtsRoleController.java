package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.program.entity.DtsRegion;
import com.ruoyi.program.entity.DtsRole;
import com.ruoyi.program.mapper.DtsArticleMapper;
import com.ruoyi.program.mapper.DtsRoleMapper;
import com.ruoyi.program.service.DtsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "角色表")
@RequestMapping("/role")
public class DtsRoleController {
    @Autowired
    private DtsRoleService dtsRoleService;

    /**
     * 插入角色信息到角色表。
     *
     * @param dtsRole 包含新角色信息的DtsRole对象。
     * @return 返回一个ResponseEntity对象，包含插入操作的成功信息。
     * @ApiOperation 注解用于说明该接口的功能是插入角色信息。
     * @PostMapping 注解用于指定该方法处理POST请求的URL路径。
     */
    @ApiOperation(value = "插入角色表")
    @PostMapping("/insertRole")
    public ResponseEntity<String> insertRole(@RequestBody DtsRole dtsRole) {
        // 设置角色的添加时间为当前时间
        dtsRole.setAddTime(new Date());
            // 调用dtsRoleMapper的insertRole方法，插入新角色信息
            // 调用dtsRoleMapper的insertRole方法，插入角色信息到数据库
            int insertRole = dtsRoleService.insertRole(dtsRole);
        // 返回响应，告知插入角色表成功   
        // 返回响应实体，告知调用方角色插入操作成功
        return ResponseEntity.ok("插入角色表成功");
    }

    /**
     * 更新角色信息。
     * <p>
     * 通过调用dtsRoleMapper的updateRole方法，更新指定的角色信息到数据库中。
     * 此方法接收一个DtsRole对象作为参数，该对象包含了需要更新的角色信息。
     * 方法执行成功后，返回一个ResponseEntity对象，其中包含成功更新角色的信息。
     *
     * @param dtsRole 包含待更新角色信息的对象。
     * @return ResponseEntity<String> 返回一个包含更新成功信息的ResponseEntity对象。
     */
    @ApiOperation(value = "更新角色表")
    @PostMapping("/updateRole")
    public ResponseEntity<String> updateRole(DtsRole dtsRole) {

        dtsRole.setUpdateTime(new Date());
        // 调用dtsRoleMapper的updateRole方法，更新角色信息到数据库
        int updateRole = dtsRoleService.updateRole(dtsRole);
        // 返回响应实体，告知调用方角色更新操作成功
        return ResponseEntity.ok("更新角色表成功");
    }

    @ApiOperation(value = "查询角色列表")
    @GetMapping("/selectRoleList")
    public ResponseEntity<List<DtsRole>> selectRoleList() {
        // 调用dtsRoleMapper的selectRoleList方法，查询所有的角色信息
        List<DtsRole> dtsRoleList = dtsRoleService.selectRoleList();
        // 返回响应实体，包含查询到的角色信息列表
        return ResponseEntity.ok(dtsRoleList);
    }

    @ApiOperation(value = "模糊查询角色")
    @GetMapping("/selectRoleListFuzzyQuery")
    public ResponseEntity<List<DtsRole>> selectRoleListFuzzyQuery(String name) {
        // 调用dtsRoleMapper的selectRoleListFuzzyQuery方法，根据角色名称模糊查询角色信息
        List<DtsRole> dtsRoleList = dtsRoleService.selectRoleListFuzzyQuery(name);
        // 返回响应实体，包含查询到的角色信息列表
        return ResponseEntity.ok(dtsRoleList);
    }

    /**
     * 通过POST请求删除角色。
     * <p>
     * 该接口使用@ApiOperation注解指明其API操作的含义，即删除角色。使用@PostMapping注解指定请求方法为POST，并定义请求URL为/deleteRole。
     * 请求体中包含一个DtsRole对象，用于指定要删除的角色。接口返回一个ResponseEntity<String>对象，其中包含操作结果的信息。
     *
     * @param dtsRole 要删除的角色对象，其中包含角色的相关信息，如角色ID等。
     * @return ResponseEntity<String> 返回一个包含操作结果信息的响应实体。成功删除角色时，返回"删除角色成功"的消息。
     */
    @ApiOperation(value = "删除角色")
    @PostMapping("/deleteRole")
    public ResponseEntity<String> deleteRole(@RequestBody DtsRole dtsRole) {
        // 调用dtsRoleService的deleteRole方法，尝试删除指定的角色
        // 调用dtsRoleMapper的deleteRole方法，根据角色ID删除角色信息
        boolean deleteRole = dtsRoleService.deleteRole(dtsRole);
        // 如果删除成功，返回一个表示成功消息的ResponseEntity
        // 返回响应实体，告知调用方角色删除操作成功
        return ResponseEntity.ok("删除角色成功");
    }

    /**
     * 分页查询角色信息。
     * 使用Swagger注解ApiOperation说明该方法的作用是分页查询角色信息。
     * 使用@GetMapping注解指定该方法处理GET请求，并指定了请求的URL路径。
     *
     * @param dtsRole  角色实体类，用于传递查询条件。
     * @param pageNum  当前页码，使用@RequestParam注解将其作为请求参数传递，默认值为1。
     * @param pageSize 每页显示的数量，使用@RequestParam注解将其作为请求参数传递，默认值为10。
     * @return 返回一个Map对象，包含分页信息和角色列表。
     * "data" 键对应的值是总记录数，"dtsRegions" 键对应的值是角色列表。
     */
    @ApiOperation(value = "分页查询角色")
    @GetMapping("/selectRoleByPage")
    public Map<String, Object> selectRoleByPage(DtsRole dtsRole,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();
        // 初始化分页插件，开始分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法查询角色列表
        // 调用服务层方法查询行政区划信息
        List<DtsRole> dtsRegions = dtsRoleService.selectRoleList();

        // 创建PageInfo对象，用于包装分页信息
        PageInfo<Object> pageInfo = new PageInfo<>(dtsRegions);
        // 将总记录数和角色列表放入返回的Map中
        // 将总记录数和行政区划列表放入返回的Map中
        map.put("data", pageInfo.getTotal());
        map.put("dtsRegions", dtsRegions);
        // 返回包含分页信息和角色列表的Map
        // 返回包含分页信息和行政区划列表的Map
        return map;
    }
}
