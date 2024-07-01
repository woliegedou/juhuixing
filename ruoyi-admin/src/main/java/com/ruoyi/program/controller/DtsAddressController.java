package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.program.entity.DtsAd;
import com.ruoyi.program.entity.DtsAddress;
import com.ruoyi.program.service.DtsAddressService;
import com.ruoyi.program.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "收货地址表")
@RequestMapping("/shippingaddress")
public class DtsAddressController {

    @Autowired
    private DtsAddressService dtsAddressService;


    /**
     * 添加收货地址
     *
     * @param dtsAddress 收货地址对象，包含地址的详细信息
     * @return 如果地址添加成功，返回包含成功消息和地址详情的AjaxResult；如果添加失败，返回包含错误消息的AjaxResult。
     */
    @ApiOperation(value = "添加收货地址")
    @PostMapping("/addAddress")
    public AjaxResult indexAddresses(@Valid @RequestBody DtsAddress dtsAddress) {
        // 调用服务层方法添加收货地址
        int insertdtsAddress = dtsAddressService.insertdtsAddress(dtsAddress);
        // 根据添加结果返回相应的AjaxResult
        if (insertdtsAddress > 0) {
            return AjaxResult.success("地址添加成功");
        } else {
            return AjaxResult.error("地址添加失败");
        }
    }

    /**
     * 通过该接口查询用户的收货地址列表。
     * <p>
     * 本接口旨在为前端提供用户的收货地址信息，以便用户在购物过程中选择和管理收货地址。
     * 返回的数据包括所有用户的收货地址详情，每个地址包含详细的收货人信息、地址、联系方式等。
     *
     * @return AjaxResult 包含查询结果的响应体，成功时包含收货地址列表，失败时包含错误信息。
     */
    @ApiOperation(value = "查询收货地址")
    @GetMapping("/queryAddress")
    public AjaxResult queryAddress() {
        // 调用服务层方法查询所有收货地址
        List<DtsAddress> dtsAddresses = dtsAddressService.selectdtsAddress();
        // 返回查询结果，成功返回包含地址列表的AjaxResult
        return AjaxResult.success(dtsAddresses);
    }

    /**
     * 修改用户收货地址
     *
     * @param dtsAddress 用户收货地址信息
     * @return 修改结果的AjaxResult对象
     * @ApiOperation 注解说明该方法的API功能为修改用户收货地址
     * @PostMapping 注解说明该方法处理的HTTP请求方法为POST，请求路径为/updateAddress
     */
    @ApiOperation(value = "修改用户收货地址")
    @PostMapping("/updateAddress")
    public AjaxResult updateAddress(@RequestBody DtsAddress dtsAddress) {
        // 调用服务层方法更新收货地址信息
        // 调用服务层方法更新收货地址
        int updatedtsAddress = dtsAddressService.updatedtsAddress(dtsAddress);
        // 根据更新结果返回相应的AjaxResult，成功或失败
        // 根据更新结果返回相应的AjaxResult
        if (updatedtsAddress > 0) {
            return AjaxResult.success("地址修改成功");
        } else {
            return AjaxResult.error("地址修改失败");
        }
    }

    /**
     * 删除用户收货地址
     *
     * @api {post} /deleteAddress 删除用户收货地址
     * @apiDescription 通过传递地址ID来删除用户的特定收货地址。
     * @apiGroup Address
     * @apiParam {Integer} id 地址ID，用于唯一标识要删除的地址。
     * @apiSuccess {String} message 删除操作的结果消息，成功时为"地址删除成功"，失败时为"地址删除失败"。
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "code": 200,
     * "message": "地址删除成功",
     * "success": true
     * }
     * @apiErrorExample {json} Error-Response:
     * HTTP/1.1 500 Internal Server Error
     * {
     * "code": 500,
     * "message": "地址删除失败",
     * "success": false
     * }
     */
    @ApiOperation(value = "删除用户收货地址")
    @PostMapping("/deleteAddress")
    public AjaxResult deleteAddress(@RequestParam Integer id) {
        // 调用服务层方法删除指定ID的收货地址
        // 调用服务层方法删除收货地址
        boolean deleteDtsAddress = dtsAddressService.deleteDtsAddress(id);
        // 根据删除操作的结果返回相应的AjaxResult对象
        // 根据删除结果返回相应的AjaxResult，成功或失败
        if (deleteDtsAddress) {
            return AjaxResult.success("地址删除成功");
        } else {
            return AjaxResult.error("地址删除失败");
        }
    }

    /**
     * 通过模糊查询用户收货地址。
     * <p>
     * 该接口使用GET方法，并通过@RequestParam注解接收可选的name参数，用于模糊搜索用户收货地址。
     * 如果提供了name参数，则搜索结果将仅包括名称与之匹配的地址；如果未提供name参数，则返回所有地址。
     * </p>
     *
     * @param name 收货地址的名称，可选参数。用于模糊搜索地址。
     * @return ResponseEntity封装的List<DtsAddress>对象，包含搜索结果。
     * @see DtsAddress 用于表示收货地址的实体类。
     * @see DtsAddressService 实现收货地址业务逻辑的服务类。
     */
    @ApiOperation(value = "模糊用户收货地址")
    @GetMapping("/queryAddressByLike")
    public ResponseEntity<List<DtsAddress>> queryAddressByLike(@RequestParam(value = "name", required = false) String name) {
        // 创建一个新的DtsAddress实例，用于设置查询条件。
        DtsAddress dtsAddress = new DtsAddress();
        dtsAddress.setName(name);
        // 调用dtsAddressService的selectdtsAddressed方法，执行模糊查询，并返回查询结果。
        List<DtsAddress> dtsAddresses = dtsAddressService.selectdtsAddressed(dtsAddress);
        // 返回包含查询结果的ResponseEntity对象。
        return ResponseEntity.ok(dtsAddresses);
    }

    @ApiOperation(value = "分页查询收货地址")
    @GetMapping("/selectDtsAddressByPage")
    public Map<String, Object> selectDtsAddressByPage(DtsAddress dtsAddress,
                                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();
        // 开始分页查询，这里使用了PageHelper插件来实现分页
        PageHelper.startPage(pageNum, pageSize);
        // 调用服务层方法查询广告信息，根据dtsAd中的条件进行查询
        List<DtsAddress> list = dtsAddressService.selectdtsAddressed(dtsAddress);
        // 使用PageInfo对查询结果进行包装，获取分页信息
        PageInfo<DtsAddress> info = new PageInfo<>(list);
        // 将查询结果的总条数和广告列表分别放入返回的Map中
        map.put("data", info.getTotal());
        map.put("list", list);

        return map;
    }

}
