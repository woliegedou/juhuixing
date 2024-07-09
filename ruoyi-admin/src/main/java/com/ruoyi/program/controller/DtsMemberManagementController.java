package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.program.entity.DtsMemberManagement;
import com.ruoyi.program.entity.DtsRegion;
import com.ruoyi.program.service.DtsMemberManagementService;
import com.ruoyi.program.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "会员表")
@RequestMapping("/memberManagement")
public class DtsMemberManagementController {

    private static final Logger logger = LoggerFactory.getLogger(DtsMemberManagementController.class);


    @Autowired
    private DtsMemberManagementService dtsMemberManagementService;

    /**
     * 添加会员信息
     *
     * @param dtsMemberManagement 会员实体类，包含会员的各项信息
     * @return 如果添加成功，返回成功消息；如果添加失败，返回错误消息
     */
    @ApiOperation(value = "添加会员")
    @PostMapping("/insertDtsMemberManagements")
    public AjaxResult insertDtsMemberManagement(@Validated @RequestBody DtsMemberManagement dtsMemberManagement) {
        try {
            // 设置会员的创建时间
            // 设置创建时间
            dtsMemberManagement.setCreatedTime(new Date());

            // 调用服务层方法插入会员信息
            int result = dtsMemberManagementService.insertDtsMemberManagement(dtsMemberManagement);
            // 根据插入结果返回相应的提示信息
            if (result > 0) {
                return AjaxResult.success("会员添加成功");
            } else {
                return AjaxResult.error("会员添加失败");
            }
        } catch (Exception e) {
            // 记录异常信息
            logger.error("添加会员时发生错误: ", e);
            // 返回具体的异常信息
            return AjaxResult.error("添加会员时发生错误: " + e.getMessage());
        }
    }

    @ApiOperation(value = "更新会员信息")
    @PostMapping("/updateMemberManagement")
    public AjaxResult updateMemberManagement(@Validated @RequestBody DtsMemberManagement dtsMemberManagement) {
        try {
            // 设置会员的更新时间
            dtsMemberManagement.setUpdatedTime(new Date());

            // 调用服务层方法更新会员信息
            int result = dtsMemberManagementService.updateMemberManagement(dtsMemberManagement);
            // 根据更新结果返回相应的提示信息
            if (result > 0) {
                return AjaxResult.success("会员更新成功");
            } else {
                return AjaxResult.error("会员更新失败");
            }
        } catch (Exception e) {

            // 记录异常信息
            logger.error("更新会员时发生错误: ", e);
            // 返回具体的异常信息
            return AjaxResult.error("更新会员时发生错误: " + e.getMessage());
        }
    }

    @ApiOperation(value = "查询会员信息")
    @PostMapping("/selectDtsMemberManagementAll")
    public AjaxResult selectDtsMemberManagementAll() {
        try {
            // 调用服务层方法查询会员信息
            return AjaxResult.success(dtsMemberManagementService.selectDtsMemberManagementAll());
        } catch (Exception e) {
            // 记录异常信息
            logger.error("查询会员信息时发生错误: ", e);
            // 返回具体的异常信息
            return AjaxResult.error("查询会员信息时发生错误: " + e.getMessage());
        }
    }

    @ApiOperation(value = "模糊查询会员信息")
    @GetMapping("/selectDtsMemberManagementByNameFuzzy")
    public AjaxResult selectDtsMemberManagementByNameFuzzy(DtsMemberManagement dtsMemberManagement) {
        try {
            // 调用服务层方法查询会员信息
            List<DtsMemberManagement> members = dtsMemberManagementService.selectDtsMemberManagementByNameOrNumberFuzzy(dtsMemberManagement);
            return AjaxResult.success(members);
        } catch (Exception e) {
            // 记录异常信息
            logger.error("模糊查询会员信息时发生错误: ", e);
            // 返回具体的异常信息
            return AjaxResult.error("模糊查询会员信息时发生错误: " + e.getMessage());
        }
    }

    @ApiOperation(value = "分页查询会员信息")
    @GetMapping("/selectDtsMemberManagementByPage")
    public Map<String, Object> selectDtsMemberManagementByPage(DtsMemberManagement dtsMemberManagement, @RequestParam(defaultValue = "1") Integer pageNum,
                                                               @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();
        // 初始化分页插件，开始分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法查询行政区划信息
        List<DtsMemberManagement> dtsRegions = dtsMemberManagementService.selectDtsMemberManagementByType(dtsMemberManagement);

        // 创建PageInfo对象，用于包装分页信息
        PageInfo<Object> pageInfo = new PageInfo<>(dtsRegions);
        // 将总记录数和行政区划列表放入返回的Map中
        map.put("data", pageInfo.getTotal());
        map.put("dtsRegions", dtsRegions);
        // 返回包含分页信息和行政区划列表的Map
        return map;
    }

    @ApiOperation(value = "导出数据")
    @GetMapping("/export")
    public void export(HttpServletResponse response, DtsMemberManagement dtsMemberManagement) {
        List<DtsMemberManagement> list = dtsMemberManagementService.selectDtsMemberManagementByType(dtsMemberManagement);

        // 使用自定义的ExcelUtil工具类导出Excel
        ExcelUtil<DtsMemberManagement> util = new ExcelUtil<>(DtsMemberManagement.class);
        util.exportExcel(response, list, "会员信息", "xlsx"); // 指定后缀为xlsx

        // 如果ExcelUtil类中没有指定后缀的方法，可以直接在方法中进行处理
        // util.exportExcel(response, list, "会员信息.xlsx");
    }
}
