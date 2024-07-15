package com.ruoyi.program.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.program.entity.DtsCommissionManagement;
import com.ruoyi.program.entity.DtsFeedback;
import com.ruoyi.program.entity.DtsMemberManagement;
import com.ruoyi.program.service.DtsFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "意见反馈")
@RequestMapping("/feedback")
public class DtsFeedbackController {
    @Autowired
    private DtsFeedbackService dtsFeedbackService;

    /**
     * 通过GET请求查询全部历史搜索反馈
     * <p>
     * 本接口提供查询DtsFeedback表中所有搜索反馈记录的功能。可以通过传递DtsFeedback对象来实现条件查询。
     * 返回所有符合条件的搜索反馈记录列表。
     *
     * @param dtsFeedback 查询条件对象，包含可能的搜索条件。
     * @return ResponseEntity 包含查询结果的列表，以及HTTP响应状态。
     */
    @ApiOperation(value = "查询全部历史搜索")
    @GetMapping("/selectDtsFeedback")
    public ResponseEntity<List<DtsFeedback>> selectDtsFeedback(DtsFeedback dtsFeedback) {
        // 调用服务层方法查询搜索反馈记录列表
        List<DtsFeedback> feedbackList = dtsFeedbackService.selectDtsFeedbackList(dtsFeedback);
        // 返回查询结果，HTTP状态为200 OK
        return ResponseEntity.ok(feedbackList);
    }

    @ApiOperation(value = "分页查询历史搜索")
    @GetMapping("/selectDtsFeedbackByPage")
    public Map<String, Object> selectDtsMemberManagementByPage(DtsFeedback dtsFeedback, @RequestParam(defaultValue = "1") Integer pageNum,
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
        List<DtsFeedback> dtsRegions = dtsFeedbackService.selectDtsFeedbackList(dtsFeedback);

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

    /**
     * 导出会员管理数据为Excel文件。
     *
     * @param response HTTP响应，用于将生成的Excel文件发送给客户端。
     * @param dtsFeedback 意见反馈实体，其中包含了用于筛选导出数据的条件。
     *
     * 此方法首先根据传入的条件查询会员管理数据，然后使用ExcelUtil工具类将查询结果导出为Excel文件，
     * 最后将生成的Excel文件通过HTTP响应发送给客户端。
     */
    @ApiOperation(value = "导出数据")
    @GetMapping("/export")
    public void export(HttpServletResponse response, DtsFeedback dtsFeedback) {
        // 根据条件查询会员管理数据
        List<DtsFeedback> list = dtsFeedbackService.selectDtsFeedbackList(dtsFeedback);

        // 使用ExcelUtil工具类导出查询结果为Excel文件
        // 使用自定义的ExcelUtil工具类导出Excel
        ExcelUtil<DtsFeedback> util = new ExcelUtil<>(DtsFeedback.class);
        util.exportExcel(response, list, "会员信息", "会员信息"); // 指定后缀为xlsx

        // 如果ExcelUtil类中没有指定后缀的方法，可以直接在方法中进行处理
        // util.exportExcel(response, list, "会员信息.xlsx");
    }

}

