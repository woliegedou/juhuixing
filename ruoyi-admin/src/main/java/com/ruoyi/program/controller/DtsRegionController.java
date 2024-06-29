package com.ruoyi.program.controller;

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

import java.util.List;

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


}
