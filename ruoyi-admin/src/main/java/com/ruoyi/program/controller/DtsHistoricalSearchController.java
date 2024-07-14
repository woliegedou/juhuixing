package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.program.entity.DtsHistoricalSearch;
import com.ruoyi.program.entity.DtsMemberCollection;
import com.ruoyi.program.service.DtsHistoricalSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "历史搜索")
@RequestMapping("/historicalSearch")
public class DtsHistoricalSearchController {
    @Autowired
    private DtsHistoricalSearchService dtsHistoricalSearchService;

    /**
     * 插入历史搜索记录。
     * 通过该接口，可以将用户的历史搜索词及其相关信息插入到数据库中，以便后续的查询和分析。
     *
     * @param dtsHistoricalSearch 包含历史搜索数据的对象，包括搜索词、用户信息等。
     * @return 如果插入成功，返回带有成功消息的ResponseEntity；如果插入失败，返回带有错误消息和状态码的ResponseEntity。
     */
    @ApiOperation(value = "插入历史搜索")
    @PostMapping("/insertHistoricalSearch")
    public ResponseEntity<?> insertHistoricalSearch(@RequestBody DtsHistoricalSearch dtsHistoricalSearch) {
        // 调用服务层方法尝试插入历史搜索记录
        boolean result = dtsHistoricalSearchService.inserthistoricalsearch(dtsHistoricalSearch);

        // 根据插入操作的结果返回不同的响应
        if (result) {
            return ResponseEntity.ok("历史搜索插入成功。");
        } else {
            return ResponseEntity.status(500).body("插入历史搜索失败。");
        }
    }

    /**
     * 修改历史搜索记录。
     * 通过接收DtsHistoricalSearch对象作为请求体，调用服务层方法更新历史搜索信息。
     * 如果更新成功，返回200状态码和成功消息；如果更新失败，返回500状态码和错误消息。
     *
     * @param dtsHistoricalSearch 包含待更新的历史搜索数据的实体类。
     * @return ResponseEntity 根据更新结果返回不同的响应，包括状态码和消息。
     */
    @ApiOperation(value = "修改历史搜索")
    @PostMapping("/updateHistoricalSearch")
    public ResponseEntity<?> updateHistoricalSearch(@RequestBody DtsHistoricalSearch dtsHistoricalSearch) {
        // 调用服务层方法，尝试更新历史搜索记录
        // 调用服务层方法尝试更新历史搜索记录
        int result = dtsHistoricalSearchService.updatehistoricalsearch(dtsHistoricalSearch);

        // 根据更新操作的结果，返回不同的响应
        // 根据更新操作的结果返回不同的响应
        if (result > 0) {
            // 更新成功，返回200状态码和成功消息
            return ResponseEntity.ok("历史搜索更新成功。");
        } else {
            // 更新失败，返回500状态码和错误消息
            return ResponseEntity.status(500).body("更新历史搜索失败。");
        }
    }

    /**
     * 通过POST请求查询全部历史搜索记录。
     * <p>
     * 该接口用于根据提供的搜索条件查询DtsHistoricalSearch表中的历史搜索记录。
     * 如果查询成功，将返回搜索结果列表；如果查询结果为空，将返回404状态码和相应的错误信息；
     * 如果查询过程中发生异常，将返回500状态码和相应的错误信息。
     *
     * @param dtsHistoricalSearch 搜索条件对象，包含需要的查询条件。
     * @return ResponseEntity<?> 返回查询结果列表，或者根据查询情况返回不同的错误信息。
     * @ApiOperation 注解用于说明该接口的业务功能。
     * @PostMapping 注解用于指定该方法处理POST请求的URL路径。
     */
    @ApiOperation(value = "查询全部历史搜索")
    @PostMapping("/selectDtsHistoricalSearch")
    public ResponseEntity<?> selectDtsHistoricalSearch(DtsHistoricalSearch dtsHistoricalSearch) {
        try {
            // 调用服务层方法查询历史搜索记录
            // 调用服务层方法，根据提供的搜索条件查询历史搜索记录
            List<DtsHistoricalSearch> historicalSearchList = dtsHistoricalSearchService.selectDtsHistoricalSearch(dtsHistoricalSearch);

            // 判断查询结果是否为空，如果为空则返回404状态码和错误信息
            // 判断查询结果是否为空
            if (historicalSearchList == null || historicalSearchList.isEmpty()) {
                // 返回查询失败的响应，状态码为404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到历史搜索记录");
            }

            // 查询成功，返回200状态码和查询结果列表
            // 返回查询成功的响应，状态码为200
            return ResponseEntity.ok(historicalSearchList);
        } catch (Exception e) {
            // 查询过程中发生异常，返回500状态码和错误信息
            // 返回查询失败的响应，状态码为500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("查询过程中发生错误: " + e.getMessage());
        }
    }

    /**
     * 分页查询历史搜索记录
     *
     * @param dtsHistoricalSearch 搜索条件对象，包含必要的查询条件
     * @param pageNum             当前页码，默认为1
     * @param pageSize            每页显示的记录数，默认为10
     * @return 返回一个Map对象，包含搜索结果的总记录数和分页后的搜索结果列表
     * @ApiOperation 注解用于API文档，描述该接口的功能
     * @PostMapping 注解用于指定该方法处理POST请求的URL路径
     */
    @ApiOperation(value = "分页历史搜索")
    @PostMapping("/selectDtsHistoricalSearchByPage")
    public Map<String, Object> selectDtsHistoricalSearchByPage(DtsHistoricalSearch dtsHistoricalSearch,
                                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                                               @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回数据的Map，用于存放搜索结果的总记录数和分页后的搜索结果列表
        // 初始化返回数据的Map
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();

        // 初始化分页插件，开始分页查询
        // 初始化分页插件，开始分页
        // 初始化分页插件，设置当前页码和每页记录数
        // 开始分页查询，这里使用了PageHelper插件来实现分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法，根据搜索条件查询历史搜索记录
        // 调用服务层方法，查询符合条件的会员收藏列表
        // 调用服务层方法查询文章类别信息，根据dtsCategory中的条件进行查询
        // 调用服务层方法查询广告信息，根据dtsAd中的条件进行查询
        List<DtsHistoricalSearch> list = dtsHistoricalSearchService.selectDtsHistoricalSearch(dtsHistoricalSearch);

        // 使用PageInfo对查询结果进行包装，获取分页信息
        // 使用PageInfo对查询结果进行包装，以获取分页信息
        PageInfo<DtsHistoricalSearch> info = new PageInfo<>(list);

        // 将搜索结果的总记录数放入map的"data"键中，将分页后的搜索结果列表放入map的"list"键中
        // 将总记录数和收藏列表分别放入返回的Map中
        // 将数据总数和数据列表分别放入返回的Map中
        // 将查询结果的总条数和广告列表分别放入返回的Map中
        map.put("data", info.getTotal());
        map.put("list", list);

        // 返回包含搜索结果总记录数和分页后搜索结果列表的Map
        // 返回包含分页信息的Map
        return map;
    }

    /**
     * 导出搜索历史数据为Excel文件。
     *
     * @param response            HTTP响应，用于将生成的Excel文件发送给客户端。
     * @param dtsHistoricalSearch 搜索条件对象，用于筛选要导出的数据。
     *                            <p>
     *                            此方法首先根据提供的搜索条件查询搜索历史数据，然后使用ExcelUtil工具类将查询结果导出为Excel文件，
     *                            并通过HTTP响应将文件发送给客户端。导出的文件包含“会员信息”和“会员收藏”两个工作表。
     */
    @ApiOperation(value = "导出数据")
    @GetMapping("/export")
    public void export(HttpServletResponse response, DtsHistoricalSearch dtsHistoricalSearch) {
        // 根据查询条件查询会员收藏列表
        // 根据条件查询会员管理数据
        List<DtsHistoricalSearch> list = dtsHistoricalSearchService.selectDtsHistoricalSearch(dtsHistoricalSearch);

        // 使用ExcelUtil工具类，将查询结果导出为Excel文件
        // 使用ExcelUtil工具类导出查询结果为Excel文件
        // 使用自定义的ExcelUtil工具类导出Excel
        ExcelUtil<DtsHistoricalSearch> util = new ExcelUtil<>(DtsHistoricalSearch.class);
        util.exportExcel(response, list, "会员信息", "会员收藏"); // 指定后缀为xlsx

        // 如果ExcelUtil类中没有指定后缀的方法，可以直接在方法中进行处理
        // util.exportExcel(response, list, "会员信息.xlsx");
    }


}
