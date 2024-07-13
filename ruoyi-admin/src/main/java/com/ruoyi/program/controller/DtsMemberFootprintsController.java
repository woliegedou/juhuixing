package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.program.entity.DtsCommissionManagement;
import com.ruoyi.program.entity.DtsMemberCollection;
import com.ruoyi.program.entity.DtsMemberFootprints;
import com.ruoyi.program.service.DtsMemberFootprintsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "会员足迹")
@RequestMapping("memberFootprints")
public class DtsMemberFootprintsController {

    @Autowired
    private DtsMemberFootprintsService dtsMemberFootprintsService;


    /**
     * 通过POST请求插入会员足迹信息。
     * <p>
     * 该接口用于接收会员足迹数据，并将其插入到数据库中。使用@RequestBody注解将请求体中的数据绑定到DtsMemberFootprints对象上，
     * 然后通过服务层进行插入操作。如果插入成功，返回状态码200和成功消息；如果插入失败，返回状态码500和错误消息。
     *
     * @param dtsMemberFootprints 会员足迹数据对象，包含需要插入的足迹信息。
     * @return ResponseEntity 对象，根据插入操作的结果返回不同的状态码和消息。
     */
    @ApiOperation(value = "插入会员足迹")
    @PostMapping("/insertDtsMemberCollection")
    public ResponseEntity<?> insertDtsMemberCollection(@RequestBody DtsMemberFootprints dtsMemberFootprints) {
        try {
            // 调用服务层方法插入会员足迹信息
            dtsMemberFootprintsService.insertDtsMemberCollection(dtsMemberFootprints);
            // 插入成功，返回状态码200和成功消息
            return ResponseEntity.ok("插入成功");
        } catch (Exception e) {
            // 插入失败，返回状态码500和错误消息
            return ResponseEntity.status(500).body("插入失败: " + e.getMessage());
        }
    }

    /**
     * 修改会员足迹信息。
     * 通过POST请求调用此接口，使用@RequestBody注解将DtsMemberFootprints对象绑定到请求体中，
     * 以便从中提取数据更新会员的足迹信息。
     *
     * @param dtsMemberFootprints 包含会员足迹更新信息的实体类。
     * @return ResponseEntity<?> 返回一个响应实体，其中包含更新操作的结果信息。
     * 如果更新成功，返回状态码200和消息"更新成功"；
     * 如果更新失败，返回状态码500和失败详情消息。
     */
    @ApiOperation(value = "修改会员足迹")
    @PostMapping("/updateMemberCollection")
    public ResponseEntity<?> updateMemberCollection(@RequestBody DtsMemberFootprints dtsMemberFootprints) {
        try {
            // 调用服务层方法更新会员足迹信息
            dtsMemberFootprintsService.updateMemberCollection(dtsMemberFootprints);
            // 更新成功，返回状态码200和成功消息
            return ResponseEntity.ok("更新成功");
        } catch (Exception e) {
            // 更新失败，返回状态码500和错误消息
            return ResponseEntity.status(500).body("更新失败: " + e.getMessage());
        }
    }

    /**
     * 通过POST请求查询全部会员足迹。
     * <p>
     * 本接口提供查询会员足迹的功能，可以根据传入的DtsMemberFootprints对象的条件来筛选查询结果。
     * 查询结果以List<DtsMemberFootprints>的形式返回。
     *
     * @param dtsMemberFootprints 查询条件对象，包含可能的过滤条件和排序规则等。
     * @return 返回包含会员足迹信息的响应体，响应体中的数据类型为List<DtsMemberFootprints>。
     */
    @ApiOperation(value = "查询全部会员足迹")
    @GetMapping("/selectDtsMemberCollection")
    public ResponseEntity<List<DtsMemberFootprints>> selectDtsMemberCollection(DtsMemberFootprints dtsMemberFootprints) {
        // 调用服务层方法查询会员足迹信息
        List<DtsMemberFootprints> footprints = dtsMemberFootprintsService.selectDtsMemberCollection(dtsMemberFootprints);
        // 返回查询结果
        return ResponseEntity.ok(footprints);
    }

    /**
     * 分页查询会员足迹信息。
     *
     * @param dtsMemberFootprints 会员足迹实体类，包含查询条件。
     * @param pageNum             当前页码，默认为1。
     * @param pageSize            每页显示数量，默认为10。
     * @return 返回一个Map对象，包含分页信息和查询结果。
     * "data" 键存储总记录数，"dtsRegions" 键存储查询到的会员足迹列表。
     * @ApiOperation 注解用于API文档，描述该接口的功能。
     * @GetMapping 注解用于定义GET请求的URL路径。
     */
    @ApiOperation(value = "分页查询会员足迹")
    @GetMapping("/selectDtsMemberCollectionByPage")
    public Map<String, Object> selectDtsMemberCollectionByPage(DtsMemberFootprints dtsMemberFootprints, @RequestParam(defaultValue = "1") Integer pageNum,
                                                               @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回数据的Map对象
        HashMap<String, Object> map = new HashMap<>();

        // 启动PageHelper分页插件，设置当前页码和每页显示的记录数
        // 使用PageHelper进行分页，启动分页插件
        // 初始化分页插件，开始分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法，查询符合过滤条件的会员足迹列表
        // 调用服务层方法，查询符合过滤条件的佣金管理信息列表
        // 调用服务层方法，查询符合过滤条件的会员信息列表
        // 调用服务层方法查询行政区划信息
        List<DtsMemberFootprints> dtsRegions = dtsMemberFootprintsService.selectDtsMemberCollection(dtsMemberFootprints);

        // 创建PageInfo对象，用于包装分页信息
        // 创建PageInfo对象，用于包装分页信息
        PageInfo<Object> pageInfo = new PageInfo<>(dtsRegions);

        // 将总记录数和查询结果分别放入Map对象中
        // 将查询结果的总条数和分页后的佣金管理信息列表放入返回的Map对象中
        // 将总记录数和会员信息列表放入返回的Map中
        // 将总记录数和行政区划列表放入返回的Map中
        map.put("data", pageInfo.getTotal());
        map.put("dtsRegions", dtsRegions);

        // 返回包含分页信息和查询结果的Map对象
        // 返回包含分页信息和佣金管理信息列表的Map对象
        // 返回包含分页信息和会员信息列表的Map
        // 返回包含分页信息和行政区划列表的Map
        return map;
    }

    /**
     * 导出会员足迹数据为Excel文件。
     *
     * @param response            HTTP响应，用于将生成的Excel文件发送给客户端。
     * @param dtsMemberFootprints 会员足迹实体，包含查询条件。
     *                            通过这个实体可以定制化地查询需要导出的数据。
     * @apiNote 该方法使用了Swagger的ApiOperation注解，用于说明该接口的功能。
     * @GetMapping注解指定了该方法处理GET请求，路径为/export。 主要业务逻辑是查询会员足迹数据，然后使用ExcelUtil工具类将数据导出为Excel文件，
     * 最后将Excel文件通过HTTP响应发送给客户端。
     */
    @ApiOperation(value = "导出数据")
    @GetMapping("/export")
    public void export(HttpServletResponse response, DtsMemberFootprints dtsMemberFootprints) {
        // 根据查询条件查询会员足迹数据
        // 根据查询条件查询会员收藏列表
        // 根据条件查询会员管理数据
        List<DtsMemberFootprints> list = dtsMemberFootprintsService.selectDtsMemberCollection(dtsMemberFootprints);

        // 初始化ExcelUtil工具类，用于导出Excel
        // 使用ExcelUtil工具类，将查询结果导出为Excel文件
        // 使用ExcelUtil工具类导出查询结果为Excel文件
        // 使用自定义的ExcelUtil工具类导出Excel
        ExcelUtil<DtsMemberFootprints> util = new ExcelUtil<>(DtsMemberFootprints.class);

        // 调用ExcelUtil的exportExcel方法，将查询结果导出为Excel文件，
        // 并通过HTTP响应发送给客户端。
        // 参数response用于设置HTTP响应头信息，list为要导出的数据，
        // "会员信息"和"会员收藏"分别为Excel文件的Sheet名称。
        util.exportExcel(response, list, "会员信息", "会员收藏"); // 指定后缀为xlsx
    }

}
