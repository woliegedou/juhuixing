package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.program.entity.DtsMemberCollection;

import com.ruoyi.program.service.DtsMemberCollectionService;
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
@RequestMapping("/memberCollection")
@Api(tags = "会员收藏")
public class DtsMemberCollectionController {

    @Autowired
    private DtsMemberCollectionService dtsMemberCollectionService;


    /**
     * 通过POST请求插入会员收藏。
     * <p>
     * 该接口用于接收会员收藏对象DtsMemberCollection，并将其插入到数据库中。
     * 如果插入成功，返回状态码200和成功消息；如果插入失败，返回状态码500和失败详情。
     *
     * @param dtsMemberCollection 会员收藏对象，包含需要插入的收藏信息。
     * @return 如果插入成功，返回包含"插入成功"消息的ResponseEntity；如果插入失败，返回包含错误消息的ResponseEntity。
     */
    @ApiOperation(value = "插入会员收藏")
    @PostMapping("/insertMemberCollection")
    public ResponseEntity<?> insertMemberCollection(@RequestBody DtsMemberCollection dtsMemberCollection) {
        try {
            // 调用服务层方法插入会员收藏信息
            dtsMemberCollectionService.insertDtsMemberCollection(dtsMemberCollection);
            // 返回成功响应
            return ResponseEntity.ok("插入成功");
        } catch (Exception e) {
            // 捕获异常，返回失败响应
            return ResponseEntity.status(500).body("插入失败: " + e.getMessage());
        }
    }

    /**
     * 修改会员收藏信息。
     * <p>
     * 通过接收{@link DtsMemberCollection}对象，更新会员的收藏状态或信息。
     * 此接口提供了对会员收藏数据进行修改的能力，支持对特定收藏项进行增加或删除操作。
     * </p>
     *
     * @param dtsMemberCollection 包含会员收藏信息的数据传输对象，用于更新数据库中的收藏记录。
     * @return 如果更新成功，返回带有成功消息的ResponseEntity；如果更新失败，返回带有错误消息的ResponseEntity。
     * @see DtsMemberCollection 会员收藏数据传输对象，包含收藏的相关信息。
     * @see ResponseEntity 用于表示HTTP响应的类，可以包含响应体和状态码等信息。
     */
    @ApiOperation(value = "修改会员收藏")
    @PostMapping("/updateMemberCollection")
    public ResponseEntity<?> updateMemberCollection(@RequestBody DtsMemberCollection dtsMemberCollection) {
        try {
            // 调用服务层方法，更新会员收藏信息。
            // 调用服务层方法更新会员收藏信息
            dtsMemberCollectionService.updateMemberCollection(dtsMemberCollection);
            // 返回HTTP响应，表明更新成功。
            // 返回成功响应
            return ResponseEntity.ok("更新成功");
        } catch (Exception e) {
            // 捕获任何异常，返回HTTP响应，表明更新失败，并附带错误消息。
            // 捕获异常，返回失败响应
            return ResponseEntity.status(500).body("更新失败: " + e.getMessage());
        }
    }

    /**
     * 通过GET请求查询全部会员收藏信息。
     * <p>
     * 本接口提供了一个方法来查询所有的会员收藏项。它不接受任何参数，
     * 并返回一个包含所有收藏项的列表。如果没有任何收藏项，将返回一个空列表。
     * </p>
     *
     * @return ResponseEntity<List < DtsMemberCollection>> - 包含所有会员收藏项的响应体。
     * 如果没有收藏项，则返回一个空内容的响应体。
     */
    @ApiOperation(value = "查询全部会员收藏")
    @GetMapping("/selectDtsMemberCollection")
    public ResponseEntity<List<DtsMemberCollection>> selectDtsMemberCollection(DtsMemberCollection dtsMemberCollection) {
        try {
            // 调用服务层方法查询全部会员收藏信息
            // 调用服务层方法，查询全部会员收藏信息。
            List<DtsMemberCollection> dtsMemberCollections = dtsMemberCollectionService.selectDtsMemberCollection(dtsMemberCollection);
            // 判断查询结果是否为空，如果为空则返回noContent状态码
            if (dtsMemberCollections == null || dtsMemberCollections.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            // 返回查询结果
            // 返回HTTP响应，包含查询结果。
            return ResponseEntity.ok(dtsMemberCollections);
        } catch (Exception e) {
            // 捕获任何异常，并返回500状态码
            // 捕获异常并返回错误响应。
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * 分页查询会员收藏信息。
     * 使用GET方法，通过传递页码和每页记录数来获取特定页的会员收藏列表。
     *
     * @param dtsMemberCollection 查询条件对象，包含会员收藏的过滤条件。
     * @param pageNum             当前页码，默认为1。
     * @param pageSize            每页记录数，默认为10。
     * @return 包含总记录数和收藏列表的Map对象。
     */
    @ApiOperation(value = "分页查询会员收藏")
    @GetMapping("/selectDtsMemberCollectionByPage")
    public Map<String, Object> selectDtsMemberCollectionByPage(DtsMemberCollection dtsMemberCollection, @RequestParam(defaultValue = "1") Integer pageNum,
                                                               @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回数据的Map
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();

        // 初始化分页插件，开始分页
        // 初始化分页插件，设置当前页码和每页记录数
        // 开始分页查询，这里使用了PageHelper插件来实现分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法，查询符合条件的会员收藏列表
        // 调用服务层方法查询文章类别信息，根据dtsCategory中的条件进行查询
        // 调用服务层方法查询广告信息，根据dtsAd中的条件进行查询
        List<DtsMemberCollection> list = dtsMemberCollectionService.selectDtsMemberCollection(dtsMemberCollection);

        // 使用PageInfo对查询结果进行包装，获取分页信息
        // 使用PageInfo对查询结果进行包装，以获取分页信息
        PageInfo<DtsMemberCollection> info = new PageInfo<>(list);

        // 将总记录数和收藏列表分别放入返回的Map中
        // 将数据总数和数据列表分别放入返回的Map中
        // 将查询结果的总条数和广告列表分别放入返回的Map中
        map.put("data", info.getTotal());
        map.put("list", list);

        // 返回包含分页信息的Map
        return map;
    }

    /**
     * 导出会员收藏信息为Excel文件。
     * 使用GET方法，通过传递查询条件对象来导出符合条件的会员收藏列表。
     *
     * @param response            HTTP响应对象，用于将导出的Excel文件发送给客户端。
     * @param dtsMemberCollection 查询条件对象，包含会员收藏的过滤条件。
     */
    @ApiOperation(value = "导出数据")
    @GetMapping("/export")
    public void export(HttpServletResponse response, DtsMemberCollection dtsMemberCollection) {
        // 根据查询条件查询会员收藏列表
        // 根据条件查询会员管理数据
        List<DtsMemberCollection> list = dtsMemberCollectionService.selectDtsMemberCollection(dtsMemberCollection);

        // 使用ExcelUtil工具类，将查询结果导出为Excel文件
        // 使用ExcelUtil工具类导出查询结果为Excel文件
        // 使用自定义的ExcelUtil工具类导出Excel
        ExcelUtil<DtsMemberCollection> util = new ExcelUtil<>(DtsMemberCollection.class);
        util.exportExcel(response, list, "会员信息", "会员收藏"); // 指定后缀为xlsx

        // 如果ExcelUtil类中没有指定后缀的方法，可以直接在方法中进行处理
        // util.exportExcel(response, list, "会员信息.xlsx");
    }


}
