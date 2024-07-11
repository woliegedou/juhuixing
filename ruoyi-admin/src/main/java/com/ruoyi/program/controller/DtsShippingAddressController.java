package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.program.entity.DtsMemberManagement;
import com.ruoyi.program.entity.DtsRole;
import com.ruoyi.program.entity.DtsShippingAddress;
import com.ruoyi.program.service.DtsShippingAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "收货地址")
@RequestMapping("/dtsshippingaddress")
public class DtsShippingAddressController {

    @Autowired
    private DtsShippingAddressService dtsShippingAddressService;

    /**
     * 插入收货地址信息到数据库。
     * 通过@ApiOperation注解指明该方法的API说明，@PostMapping注解指明该方法处理的HTTP请求方法和请求路径。
     *
     * @param dtsShippingAddress 收货地址对象，包含收货地址的详细信息。
     * @return 返回 ResponseEntity 对象，其中包含操作结果的信息。
     * 如果插入成功，返回状态码201和插入成功的消息；
     * 如果插入失败，返回状态码500和插入失败的消息；
     * 如果出现IllegalArgumentException异常，返回状态码400和异常信息。
     */
    @ApiOperation(value = "插入收货地址")
    @PostMapping("/insertDtsShippingAddress")
    public ResponseEntity<String> insertDtsShippingAddress(@RequestBody DtsShippingAddress dtsShippingAddress) {
        try {
            // 设置收货地址的创建时间
            dtsShippingAddress.setCreatedTime(new Date());
            // 调用服务层方法插入收货地址
            int result = dtsShippingAddressService.insertDtsShippingAddress(dtsShippingAddress);
            // 根据插入结果返回不同的响应
            if (result > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("收货地址插入成功");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("收货地址插入失败");
            }
        } catch (IllegalArgumentException e) {
            // 如果发生IllegalArgumentException异常，返回状态码400和异常信息
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 修改收货地址接口。
     * 通过@ApiOperation注解说明该接口的作用是修改收货地址。
     * 使用@PostMapping注解指定该方法处理POST请求，请求路径为/updateDtsShippingAddress。
     *
     * @param dtsShippingAddress 通过@RequestBody注解说明该参数是从请求体中获取的，是一个DtsShippingAddress对象，包含了需要修改的收货地址信息。
     * @return 返回一个ResponseEntity<String>对象，其中body字段是操作结果的字符串描述，状态码表示操作的成功与否。
     */
    @ApiOperation(value = "修改收货地址")
    @PostMapping("/updateDtsShippingAddress")
    public ResponseEntity<String> updateDtsShippingAddress(@RequestBody DtsShippingAddress dtsShippingAddress) {
        try {
            // 更新收货地址的修改时间
            // 设置收货地址的更新时间
            dtsShippingAddress.setUpdatedTime(new Date());
            // 调用服务层方法更新收货地址信息
            // 调用服务层方法更新收货地址
            int result = dtsShippingAddressService.updateDtsShippingAddress(dtsShippingAddress);
            // 根据更新结果返回不同的响应
            // 根据更新结果返回不同的响应
            if (result > 0) {
                // 更新成功
                return ResponseEntity.status(HttpStatus.OK).body("收货地址更新成功");
            } else {
                // 更新失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("收货地址更新失败");
            }
        } catch (Exception e) {
            // 更新过程中发生异常
            // 记录异常日志
            e.printStackTrace();
            // 返回错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("收货地址更新过程中发生错误");
        }
    }

    /**
     * 通过API查询收货地址。
     *
     * @param dtsShippingAddress 查询条件，包含可能的收货地址过滤参数。
     * @return ResponseEntity<?> 返回查询结果，如果查询成功，返回状态码200和地址列表；如果查询失败，返回状态码404和错误信息。
     * @ApiOperation 注解用于说明该方法的作用，包括操作的名称和价值。
     * @GetMapping 注解用于定义GET请求的URL路径。
     */
    @ApiOperation(value = "查询收货地址")
    @GetMapping("/selectDtsShippingAddress")
    public ResponseEntity<?> selectDtsShippingAddress(DtsShippingAddress dtsShippingAddress) {
        // 调用服务层方法，根据传入的条件查询收货地址
        // 调用服务层方法查询收货地址
        List<DtsShippingAddress> dtsShippingAddresses = dtsShippingAddressService.selectDtsShippingAddress(dtsShippingAddress);

        // 判断查询结果是否为空，如果不为空，则返回查询结果；如果为空，则返回查询失败的信息。
        // 根据查询结果返回不同的响应
        if (dtsShippingAddresses != null && !dtsShippingAddresses.isEmpty()) {
            // 查询成功
            return ResponseEntity.status(HttpStatus.OK).body(dtsShippingAddresses);
        } else {
            // 查询失败
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("查询失败");
        }
    }

    /**
     * 模糊查询收货地址列表。
     * 通过接收DtsShippingAddress对象作为参数，查询满足条件的收货地址列表。
     *
     * @param dtsShippingAddress 查询条件，包含可能的收货地址信息如收货人姓名、电话、地址等。
     * @return 返回查询结果的 ResponseEntity 对象。如果查询成功，返回状态码200和查询结果列表；
     * 如果查询结果为空，同样返回状态码200但列表为空；如果查询过程中发生异常，返回状态码500和错误信息。
     */
    @ApiOperation(value = "模糊查询收货地址列表")
    @GetMapping("/selectDtsShippingAddressFuzzyQuery")
    public ResponseEntity<?> selectDtsShippingAddressFuzzyQuery(DtsShippingAddress dtsShippingAddress) {
        try {
            // 调用服务层方法进行模糊查询
            // 调用服务层方法，根据传入的条件模糊查询收货地址
            List<DtsShippingAddress> dtsShippingAddresses = dtsShippingAddressService.selectDtsShippingAddress(dtsShippingAddress);

            // 根据查询结果是否为空，返回相应的 ResponseEntity
            // 如果查询结果为空列表，返回200状态码和空列表
            if (dtsShippingAddresses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(dtsShippingAddresses);
            } else {
                // 查询成功
                return ResponseEntity.status(HttpStatus.OK).body(dtsShippingAddresses);
            }
        } catch (Exception e) {
            // 查询失败时，返回500状态码和错误信息
            // 查询失败，返回500状态码和错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("查询失败: " + e.getMessage());
        }
    }

    /**
     * 分页查询收货地址信息。
     * 通过此接口可以获取指定条件下的收货地址列表，并包含分页信息。
     *
     * @param dtsShippingAddress 查询条件，包含收货地址的详细信息。
     * @param pageNum            当前页码，默认为1。
     * @param pageSize           每页显示的数量，默认为10。
     * @return 返回一个Map，包含收货地址列表和总记录数。
     */
    @ApiOperation(value = "分页查询收货地址")
    @GetMapping("/selectDtsShippingAddressByPage")
    public Map<String, Object> selectDtsShippingAddressByPage(DtsShippingAddress dtsShippingAddress,
                                                              @RequestParam(defaultValue = "1") Integer pageNum,
                                                              @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();
        // 初始化分页插件，开始分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法查询收货地址列表
        // 调用服务层方法查询行政区划信息
        List<DtsShippingAddress> dtsRegions = dtsShippingAddressService.selectDtsShippingAddress(dtsShippingAddress);

        // 创建PageInfo对象，用于包装分页信息
        PageInfo<Object> pageInfo = new PageInfo<>(dtsRegions);
        // 将总记录数和收货地址列表放入返回的Map中
        // 将总记录数和行政区划列表放入返回的Map中
        map.put("data", pageInfo.getTotal());
        map.put("dtsRegions", dtsRegions);
        // 返回包含分页信息和收货地址列表的Map
        // 返回包含分页信息和行政区划列表的Map
        return map;
    }

    /**
     * 导出收货地址信息为Excel文件。
     * 使用Swagger注解ApiOperation指定该方法的用途为导出收货地址。
     * 使用@GetMapping注解指定该方法处理GET请求，并定义请求的路径为/export。
     *
     * @param response           HttpServletResponse对象，用于将导出的Excel文件响应给客户端。
     * @param dtsShippingAddress DtsShippingAddress对象，包含过滤收货地址的条件。
     *                           可用于指定导出的地址范围，如按地区、用户等条件筛选。
     */
    @ApiOperation(value = "导出收货地址")
    @GetMapping("/export")
    public void export(HttpServletResponse response, DtsShippingAddress dtsShippingAddress) {
        // 调用服务层方法，根据条件查询收货地址列表。
        List<DtsShippingAddress> list = dtsShippingAddressService.selectDtsShippingAddress(dtsShippingAddress);

        // 使用ExcelUtil工具类导出查询到的收货地址列表为Excel文件。
        // ExcelUtil是通用的Excel导出工具类，通过传入DtsShippingAddress类的Class对象，
        // 可以自动将列表数据映射为Excel表格。
        // 使用自定义的ExcelUtil工具类导出Excel
        ExcelUtil<DtsShippingAddress> util = new ExcelUtil<>(DtsShippingAddress.class);
        // 这里指定了导出的Excel文件名称和工作表名称，便于用户识别和管理导出的文件。
        util.exportExcel(response, list, "收货地址", "收货地址"); // 指定后缀为xlsx

        // 如果ExcelUtil类中没有指定后缀的方法，可以直接在方法中进行处理
        // util.exportExcel(response, list, "会员信息.xlsx");
    }
}

