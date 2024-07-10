package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.ruoyi.program.entity.DtsCommissionManagement;
import com.ruoyi.program.entity.DtsMemberManagement;
import com.ruoyi.program.service.DtsCommissionManagementService;
import com.ruoyi.program.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "佣金管理")
@RequestMapping("/commissionManagement")
public class DtsCommissionManagementController {


    @Autowired
    private DtsCommissionManagementService dtsCommissionManagementService;


    /**
     * 插入佣金管理记录
     * 该方法用于接收来自前端的佣金管理信息，并将其插入到数据库中。
     * 如果插入成功，返回插入成功的消息；如果插入失败，返回插入失败的消息。
     *
     * @param dtsCommissionManagement 包含佣金管理信息的实体类，其中包含了需要插入数据库的所有字段。
     * @return 如果插入成功，返回一个包含成功消息的AjaxResult对象；如果插入失败，返回一个包含错误消息的AjaxResult对象。
     */
    /**
     * 插入佣金管理记录
     */
    @ApiOperation(value = "插入佣金管理")
    @PostMapping("/insertcommissionmanagement")
    public AjaxResult insertCommissionManagement(@RequestBody DtsCommissionManagement dtsCommissionManagement) {
        // 设置创建时间
        dtsCommissionManagement.setCreatedTime(new Date());
        // 调用服务层方法插入佣金管理记录
        int result = dtsCommissionManagementService.insertDtsCommissionManagement(dtsCommissionManagement);
        // 根据插入结果返回相应的提示信息
        if (result > 0) {
            return AjaxResult.success("插入成功");
        } else {
            return AjaxResult.error("插入失败");
        }
    }

    /**
     * 更新佣金管理记录
     * 通过@RequestBody注解，将请求体中的DtsCommissionManagement对象绑定到方法参数上，
     * 然后调用服务层方法更新佣金管理信息。
     *
     * @param dtsCommissionManagement 包含需要更新的佣金管理信息的数据对象
     * @return AjaxResult 对象，根据更新结果返回成功或失败的信息
     */
    /**
     * 更新佣金管理记录
     */
    @ApiOperation(value = "更新佣金管理")
    @PostMapping("/updatecommissionmanagement")
    public AjaxResult updateCommissionManagement(@RequestBody DtsCommissionManagement dtsCommissionManagement) {
        // 设置更新时间
        // 设置更新时间
        dtsCommissionManagement.setUpdatedTime(new Date());
        // 调用服务层方法，更新佣金管理记录
        // 调用服务层方法更新佣金管理记录
        int result = dtsCommissionManagementService.updateDtsCommissionManagement(dtsCommissionManagement);
        // 根据更新结果返回相应的提示信息
        // 根据更新结果返回相应的提示信息
        if (result > 0) {
            return AjaxResult.success("更新成功");
        } else {
            return AjaxResult.error("更新失败");
        }
    }

    /**
     * 通过API查询佣金管理信息。
     * 使用GET方法，并通过URL指定查询佣金管理的接口。
     *
     * @return 返回查询结果，以ResponseEntity包装，确保HTTP响应的状态和内容都能被正确处理。
     * @ApiOperation 注解用于说明该方法的API功能和价值。
     * @GetMapping 注解用于指定该方法处理GET请求的URL路径。
     */
    @ApiOperation(value = "查询全部佣金管理")
    @GetMapping("/selectDtsCommissionManagemen")
    public ResponseEntity<?> selectDtsCommissionManagemen() {
        // 调用服务层方法，查询佣金管理记录。
        // 根据查询条件对象dtsCommissionManagement，查询满足条件的佣金管理信息。
        // 调用服务层方法查询佣金管理记录
        List<DtsCommissionManagement> dtsCommissionManagementList = dtsCommissionManagementService.selectDtsCommissionManagementList();
        // 将查询结果返回给客户端，以响应体的形式。
        // 此处使用ResponseEntity.ok()方法，表示返回状态码为200的响应，并将查询结果作为内容返回。
        // 返回查询结果
        return ResponseEntity.ok(dtsCommissionManagementList);
    }


    /**
     * 通过@ApiOperation注解指定该方法的API说明，value属性指定了API的描述。
     * 使用@GetMapping注解指定该方法处理GET请求。
     * 请求的URL为/selectdtscommissionManagement。
     * 该方法用于模糊查询佣金管理信息。
     *
     * @param dtsCommissionManagement 查询条件对象，包含佣金管理的过滤条件。
     * @return 返回查询结果的ResponseEntity对象。
     * 如果查询结果不为空，使用ResponseEntity.ok()返回查询结果列表。
     * 如果查询结果为空，返回一个表示无内容的ResponseEntity。
     */
    @ApiOperation(value = "模糊查询佣金管理")
    @GetMapping("/selectdtscommissionManagement")
    public ResponseEntity<List<DtsCommissionManagement>> selectDtsCommissionManagement(DtsCommissionManagement dtsCommissionManagement) {
        // 调用服务层方法进行佣金管理信息的查询
        List<DtsCommissionManagement> dtsCommissionManagements =
                dtsCommissionManagementService.selectDtsCommissionManagement(dtsCommissionManagement);

        // 根据查询结果是否为空，返回不同的ResponseEntity
        if (!dtsCommissionManagements.isEmpty()) {
            return ResponseEntity.ok(dtsCommissionManagements);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * 分页查询佣金管理信息。
     * 通过DtsCommissionManagement对象过滤查询条件，支持分页查询。
     * 返回查询结果的总条数和分页后的佣金管理信息列表。
     *
     * @param dtsCommissionManagement 查询条件对象，包含佣金管理的过滤条件。
     * @param pageNum                  请求的页码，默认为1。
     * @param pageSize                 每页显示的条数，默认为10。
     * @return 返回一个Map对象，包含总条数（key为"data"）和佣金管理信息列表（key为"dtsRegions"）。
     */
    @ApiOperation(value = "分页查询佣金管理")
    @GetMapping("/selectdtscommission")
    public Map<String, Object> selectDtsMemberManagementByPage(DtsCommissionManagement dtsCommissionManagement, @RequestParam(defaultValue = "1") Integer pageNum,
                                                               @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回数据的Map对象
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();

        // 使用PageHelper进行分页，启动分页插件
        // 初始化分页插件，开始分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法，查询符合过滤条件的佣金管理信息列表
        // 调用服务层方法，查询符合过滤条件的会员信息列表
        // 调用服务层方法查询行政区划信息
        List<DtsCommissionManagement> dtsRegions = dtsCommissionManagementService.selectDtsCommissionManagement(dtsCommissionManagement);

        // 创建PageInfo对象，用于包装分页信息
        // 创建PageInfo对象，用于包装分页信息
        PageInfo<Object> pageInfo = new PageInfo<>(dtsRegions);

        // 将查询结果的总条数和分页后的佣金管理信息列表放入返回的Map对象中
        // 将总记录数和会员信息列表放入返回的Map中
        // 将总记录数和行政区划列表放入返回的Map中
        map.put("data", pageInfo.getTotal());
        map.put("dtsRegions", dtsRegions);

        // 返回包含分页信息和佣金管理信息列表的Map对象
        // 返回包含分页信息和会员信息列表的Map
        // 返回包含分页信息和行政区划列表的Map
        return map;
    }

}