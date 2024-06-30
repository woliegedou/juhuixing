package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.program.entity.DtsRegion;
import com.ruoyi.program.service.DtsRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "行政区划管理")
@RequestMapping("/administrativedivisionmanagement")
public class DtsRegionController {

    @Autowired
    private DtsRegionService dtsRegionService;


    /**
     * 添加行政区划信息
     * 通过该接口，可以向系统中添加新的行政区划数据。
     *
     * @param dtsRegion 包含行政区划信息的实体类，通过RequestBody传递。
     * @return 返回添加结果的信息，成功则返回"添加成功"，失败则返回"添加失败"。
     * @apiOperation value="添加行政区划"
     * @apiPostMapping path="/insertdtsRegion"
     */
    @ApiOperation(value = "添加行政区划")
    @PostMapping("/insertdtsRegion")
    public ResponseEntity<String> insertdtsRegion(@Validated @RequestBody DtsRegion dtsRegion) {
        // 调用服务层方法尝试添加行政区划信息
        int insertdtsRegion = dtsRegionService.insertdtsRegion(dtsRegion);
        // 根据添加结果返回相应的HTTP响应
        if (insertdtsRegion > 0) {
            return new ResponseEntity<>("添加成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("添加失败", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 查询所有行政区划树结构
     *
     * @return AjaxResult 返回查询结果，如果未找到数据，则返回错误信息
     */
    @ApiOperation(value = "查询所有行政区划树结构")
    @GetMapping("/queryAll")
    public AjaxResult queryAllRegions() {
        // 调用服务层方法查询所有行政区划
        List<DtsRegion> dtsRegions = dtsRegionService.dtsRegionids();
        // 判断查询结果是否为空，如果不为空则返回成功结果并附带查询数据，否则返回错误信息
        if (dtsRegions != null && !dtsRegions.isEmpty()) {
            // 查询结果不为空且不为空列表时，返回成功结果和查询到的专业信息列表
            return AjaxResult.success(dtsRegions);
        } else {
            // 未查询到任何专业信息时，返回错误结果提示未找到数据
            return AjaxResult.error("未找到数据");
        }
    }

    /**
     * 修改行政区划信息
     * 通过POST请求调用此方法来更新DtsRegion对象的行政区划信息。
     * 如果更新成功，返回状态码200和成功消息；如果更新失败，返回状态码500和错误消息。
     *
     * @param dtsRegion 包含待更新行政区划信息的请求体。
     * @return ResponseEntity<?> 根据更新结果返回不同的响应体，包括成功或失败的消息。
     */
    @ApiOperation(value = "修改行政区划")
    @PostMapping("/updatedtsRegion")
    public ResponseEntity<?> updatedtsRegion(@Validated @RequestBody DtsRegion dtsRegion) {
        // 调用服务层方法尝试更新行政区划信息
        int result = dtsRegionService.updatedtsRegion(dtsRegion);
        // 根据更新结果返回不同的响应
        if (result > 0) {
            // 修改成功
            return ResponseEntity.ok().body("行政区划修改成功");
        } else {
            // 修改失败
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("行政区划修改失败");
        }
    }

    @ApiOperation(value = "删除行政区划")
    @PostMapping("/deletedtsRegion")
    public ResponseEntity<String> deletedtsRegion(@RequestParam Long id) {
        // 调用服务层方法尝试删除行政区划信息
        boolean result = dtsRegionService.deletedtsRegion(id);
        // 根据删除结果返回不同的响应
        if (result) {
            // 删除成功
            return ResponseEntity.ok().body("行政区划删除成功");
        } else {
            // 删除失败
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("行政区划删除失败");
        }
    }

    /**
     * 分页查询行政区划信息。
     * 使用Swagger注解ApiOperation指明该方法的作用是分页查询行政区划。
     * 使用@GetMapping注解指明该方法处理GET请求，并指定请求的URL路径为/paginationqueryannouncement。
     *
     * @param dtsRegion 行政区划实体类，用于传递查询条件。
     * @param pageNum   请求的页码，默认为1。
     * @param pageSize  每页显示的记录数，默认为10。
     * @return 返回一个Map对象，其中包含分页信息和行政区划列表。
     * "data" 键对应的值是总记录数，"dtsRegions" 键对应的值是行政区划列表。
     */
    @ApiOperation(value = "分页查询行政区划")
    @GetMapping("/paginationqueryannouncement")
    public Map<String, Object> selectDtsRegion(DtsRegion dtsRegion,
                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();
        // 初始化分页插件，开始分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法查询行政区划信息
        List<DtsRegion> dtsRegions = dtsRegionService.queryAllByLimit(dtsRegion);

        // 创建PageInfo对象，用于包装分页信息
        PageInfo<Object> pageInfo = new PageInfo<>(dtsRegions);
        // 将总记录数和行政区划列表放入返回的Map中
        map.put("data", pageInfo.getTotal());
        map.put("dtsRegions", dtsRegions);
        // 返回包含分页信息和行政区划列表的Map
        return map;
    }

    @ApiOperation(value = "模糊查询行政区划")
    @GetMapping("/selectDtsRegionByLike")
    public AjaxResult selectDtsRegionByLike(@RequestParam("name") String name) {
        List<DtsRegion> dtsRegions = dtsRegionService.searchMajorByName(name);
        List<DtsRegion> dtsRegion = dtsRegionService.buildDeptTree(dtsRegions);
        return AjaxResult.success(dtsRegion);
    }

}
