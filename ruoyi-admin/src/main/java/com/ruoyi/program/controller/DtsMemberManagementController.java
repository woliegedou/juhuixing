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
     * @param dtsMemberManagement 会员实体类，包含需要插入数据库的会员信息
     * @return 返回操作结果，成功则包含成功消息，失败则包含错误消息
     */
    @ApiOperation(value = "添加会员")
    @PostMapping("/insertDtsMemberManagements")
    public AjaxResult insertDtsMemberManagement(@Validated @RequestBody DtsMemberManagement dtsMemberManagement) {
        try {
            // 设置会员的创建时间
            // 设置创建时间
            dtsMemberManagement.setCreatedTime(new Date());

            // 调用服务层方法，插入会员信息到数据库
            // 调用服务层方法插入会员信息
            int result = dtsMemberManagementService.insertDtsMemberManagement(dtsMemberManagement);
            // 根据插入结果返回相应的提示信息
            // 根据插入结果返回相应的提示信息
            if (result > 0) {
                return AjaxResult.success("会员添加成功");
            } else {
                return AjaxResult.error("会员添加失败");
            }
        } catch (Exception e) {
            // 记录添加会员时发生的异常
            // 记录异常信息
            logger.error("添加会员时发生错误: ", e);
            // 返回具体的异常信息
            return AjaxResult.error("添加会员时发生错误: " + e.getMessage());
        }
    }

    /**
     * 更新会员信息接口。
     * 通过@ApiOperation注解说明该方法的API用途和价值。
     * 使用@PostMapping注解指定该方法处理HTTP POST请求，并指明请求的URL路径。
     *
     * @param dtsMemberManagement 会员信息对象，包含需要更新的会员数据。
     *                            使用@Validated注解对参数进行验证，确保数据的合法性和完整性。
     *                            使用@RequestBody注解指示Spring MVC将请求体中的数据绑定到该参数上。
     * @return 返回AjaxResult对象，根据更新操作的结果返回不同的消息。
     *         如果更新成功，返回包含成功消息和数据的AjaxResult；
     *         如果更新失败，返回包含错误消息的AjaxResult。
     *         在异常情况下，返回包含异常详细信息的错误消息。
     */
    @ApiOperation(value = "更新会员信息")
    @PostMapping("/updateMemberManagement")
    public AjaxResult updateMemberManagement(@Validated @RequestBody DtsMemberManagement dtsMemberManagement) {
        try {
            // 更新会员的最后修改时间
            // 设置会员的更新时间
            dtsMemberManagement.setUpdatedTime(new Date());

            // 调用服务层方法，尝试更新会员信息
            // 调用服务层方法更新会员信息
            int result = dtsMemberManagementService.updateMemberManagement(dtsMemberManagement);
            // 根据更新结果返回相应的提示信息
            // 根据更新结果返回相应的提示信息
            if (result > 0) {
                return AjaxResult.success("会员更新成功");
            } else {
                return AjaxResult.error("会员更新失败");
            }
        } catch (Exception e) {
            // 记录捕获到的异常信息
            // 记录异常信息
            logger.error("更新会员时发生错误: ", e);
            // 返回具体的异常信息给前端
            // 返回具体的异常信息
            return AjaxResult.error("更新会员时发生错误: " + e.getMessage());
        }
    }

    /**
     * 通过POST请求查询所有会员信息。
     * <p>
     * 本接口提供了一个途径来获取系统的全部会员信息。使用时，客户端发送一个POST请求到/selectDtsMemberManagementAll路径。
     * 接口会尝试调用服务层方法查询所有会员信息，并将结果封装成AjaxResult对象返回。
     * 如果在查询过程中发生异常，接口会返回一个包含错误信息的AjaxResult。
     *
     * @return AjaxResult 包含查询结果的成功结果对象，或包含错误信息的错误结果对象。
     */
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

    /**
     * 模糊查询会员信息接口
     * 通过提供姓名或编号的模糊查询条件，检索会员信息。
     *
     * @param dtsMemberManagement 查询条件对象，包含姓名和编号等信息
     * @return 返回查询结果集，如果发生异常，则返回错误信息
     * @ApiOperation 注解用于说明该接口的功能
     * @GetMapping 注解用于定义HTTP GET请求的路径
     */
    @ApiOperation(value = "模糊查询会员信息")
    @GetMapping("/selectDtsMemberManagementByNameFuzzy")
    public AjaxResult selectDtsMemberManagementByNameFuzzy(DtsMemberManagement dtsMemberManagement) {
        try {
            // 调用服务层方法，根据提供的条件模糊查询会员信息
            // 调用服务层方法查询会员信息
            List<DtsMemberManagement> members = dtsMemberManagementService.selectDtsMemberManagementByNameOrNumberFuzzy(dtsMemberManagement);
            // 返回查询结果
            return AjaxResult.success(members);
        } catch (Exception e) {
            // 记录查询过程中发生的异常信息
            // 记录异常信息
            logger.error("模糊查询会员信息时发生错误: ", e);
            // 返回异常信息给前端
            // 返回具体的异常信息
            return AjaxResult.error("模糊查询会员信息时发生错误: " + e.getMessage());
        }
    }

    /**
     * 分页查询会员信息接口。
     * 通过此接口可以获取指定页码和每页数量的会员信息列表，以及总记录数。
     *
     * @param dtsMemberManagement 会员信息实体类，用于传递查询条件。
     * @param pageNum             请求的页码，默认为1。
     * @param pageSize            每页的数量，默认为10。
     * @return 返回一个Map对象，包含分页信息和会员信息列表。
     *         其中，"data" 键对应的值为总记录数，
     *         "dtsRegions" 键对应的值为会员信息列表。
     */
    @ApiOperation(value = "分页查询会员信息")
    @GetMapping("/selectDtsMemberManagementByPage")
    public Map<String, Object> selectDtsMemberManagementByPage(DtsMemberManagement dtsMemberManagement, @RequestParam(defaultValue = "1") Integer pageNum,
                                                               @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();

        // 初始化分页插件，开始分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法，查询符合过滤条件的会员信息列表
        // 调用服务层方法查询行政区划信息
        List<DtsMemberManagement> dtsRegions = dtsMemberManagementService.selectDtsMemberManagementByType(dtsMemberManagement);

        // 创建PageInfo对象，用于包装分页信息
        // 创建PageInfo对象，用于包装分页信息
        PageInfo<Object> pageInfo = new PageInfo<>(dtsRegions);

        // 将总记录数和会员信息列表放入返回的Map中
        // 将总记录数和行政区划列表放入返回的Map中
        map.put("data", pageInfo.getTotal());
        map.put("dtsRegions", dtsRegions);

        // 返回包含分页信息和会员信息列表的Map
        // 返回包含分页信息和行政区划列表的Map
        return map;
    }

    /**
     * 导出会员管理数据为Excel文件。
     *
     * @param response HTTP响应，用于将生成的Excel文件发送给客户端。
     * @param dtsMemberManagement 会员管理实体，其中包含了用于筛选导出数据的条件。
     *
     * 此方法首先根据传入的条件查询会员管理数据，然后使用ExcelUtil工具类将查询结果导出为Excel文件，
     * 最后将生成的Excel文件通过HTTP响应发送给客户端。
     */
    @ApiOperation(value = "导出数据")
    @GetMapping("/export")
    public void export(HttpServletResponse response, DtsMemberManagement dtsMemberManagement) {
        // 根据条件查询会员管理数据
        List<DtsMemberManagement> list = dtsMemberManagementService.selectDtsMemberManagementByType(dtsMemberManagement);

        // 使用ExcelUtil工具类导出查询结果为Excel文件
        // 使用自定义的ExcelUtil工具类导出Excel
        ExcelUtil<DtsMemberManagement> util = new ExcelUtil<>(DtsMemberManagement.class);
        util.exportExcel(response, list, "会员信息", "会员信息"); // 指定后缀为xlsx

        // 如果ExcelUtil类中没有指定后缀的方法，可以直接在方法中进行处理
        // util.exportExcel(response, list, "会员信息.xlsx");
    }
}
