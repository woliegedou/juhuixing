package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.program.entity.DtsAd;
import com.ruoyi.program.mapper.DtsAdMapper;
import com.ruoyi.program.service.DtsAdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"广告管理"})
@RestController
@RequestMapping("/adStatement")
public class DtsAdController {
    @Autowired
    private DtsAdService dtsAdService;

    private static final Logger logger = LoggerFactory.getLogger(DtsUserController.class);
    @Autowired
    private DtsAdMapper dtsAdMapper;


    /**
     * 插入广告信息。
     * 通过此接口可以将广告的各个属性包括广告图片上传至系统。
     *
     * @param url          广告图片文件，通过MultipartFile接收。
     * @param name         广告名称。
     * @param link         广告链接。
     * @param content      广告内容。
     * @param position     广告位置。
     * @param startTimeStr 广告开始时间字符串，格式为yyyy-MM-dd。
     * @param endTimeStr   广告结束时间字符串，格式为yyyy-MM-dd。
     * @param enabled      广告是否启用。
     * @return 返回上传结果信息。
     */
    @ApiOperation(value = "插入广告")
    @PostMapping("/insertDtsAd")
    public ResponseEntity<?> insertDtsAd(
            @RequestParam("url") MultipartFile url,
            @RequestParam("name") String name,
            @RequestParam("link") String link,
            @RequestParam("content") String content,
            @RequestParam("position") int position,
            @RequestParam("startTimeStr") @DateTimeFormat(pattern = "yyyy-MM-dd") String startTimeStr,
            @RequestParam("endTimeStr") @DateTimeFormat(pattern = "yyyy-MM-dd") String endTimeStr,
            @RequestParam("enabled") int enabled
    ) {
        // 检查上传的文件是否为空
        // 验证主文件是否为空
        if (url.isEmpty()) {
            return ResponseEntity.badRequest().body("主文件不能为空");
        }

        try {
            // 上传广告图片文件并获取文件URL
            // 上传主文件并获取文件路径
            String fileUrl = dtsAdService.uploadDtsAd(url);

            // 格式化日期字符串为Date类型
            // 解析日期字符串
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startTime = dateFormat.parse(startTimeStr);
            Date endTime = dateFormat.parse(endTimeStr);

            // 创建广告对象并设置属性
            // 创建广告对象
            DtsAd dtsAd = new DtsAd();
            dtsAd.setName(name);
            dtsAd.setLink(link);
            dtsAd.setUrl(fileUrl);
            dtsAd.setPosition(position);
            dtsAd.setContent(content);
            dtsAd.setStartTime(startTime);
            dtsAd.setEndTime(endTime);
            dtsAd.setAddTime(new Date()); // 设置创建时间
            dtsAd.setUpdateTime(null); // 设置更新时间为null，表示尚未更新

            // 插入广告信息到数据库
            // 保存广告信息到数据库
            dtsAdService.insert(dtsAd);

            // 返回上传成功信息
            return ResponseEntity.ok("上传成功");
        } catch (ParseException e) {
            // 返回日期格式错误信息
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("日期格式错误: " + e.getMessage());
        } catch (Exception e) {
            // 返回上传失败信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上传失败: " + e.getMessage());
        }
    }

    /**
     * 修改广告信息接口。
     * 通过此接口可以更新广告的各个属性，包括广告图片（如果提供了新的图片）。
     * 广告的开始和结束时间需要符合指定的日期格式。
     *
     * @param id           广告的唯一标识符。
     * @param url          上传的广告图片文件，非必填。
     * @param name         广告的名称。
     * @param link         广告的链接。
     * @param content      广告的内容描述。
     * @param position     广告的位置。
     * @param startTimeStr 广告的开始时间，字符串格式，需要符合指定的日期格式。
     * @param endTimeStr   广告的结束时间，字符串格式，需要符合指定的日期格式。
     * @param enabled      广告的启用状态。
     * @return 修改结果的响应体，包含修改成功与否的信息。
     */
    @ApiOperation(value = "修改广告")
    @PostMapping("/updateDtsAd")
    public ResponseEntity<?> updateDtsAd(
            @RequestParam("id") int id,
            @RequestParam("url") MultipartFile url,
            @RequestParam("name") String name,
            @RequestParam("link") String link,
            @RequestParam("content") String content,
            @RequestParam("position") int position,
            @RequestParam("startTimeStr") @DateTimeFormat(pattern = "yyyy-MM-dd") String startTimeStr,
            @RequestParam("endTimeStr") @DateTimeFormat(pattern = "yyyy-MM-dd") String endTimeStr,
            @RequestParam("enabled") int enabled
    ) {
        try {
            // 根据id查询广告是否存在
            // 查找现有广告对象
            DtsAd dtsAd = dtsAdService.queryById(id);
            if (dtsAd == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("广告不存在");
            }

            // 如果上传了新的图片，则处理图片上传并更新广告的图片链接
            // 如果有新的文件上传，处理文件上传并获取文件URL
            if (url != null && !url.isEmpty()) {
                String fileUrl = dtsAdService.uploadDtsAd(url);
                dtsAd.setUrl(fileUrl);
            }

            // 将日期字符串转换为Date对象
            // 格式化日期字符串为Date类型
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startTime = dateFormat.parse(startTimeStr);
            Date endTime = dateFormat.parse(endTimeStr);

            // 更新广告的各项属性
            // 更新广告对象属性
            dtsAd.setName(name);
            dtsAd.setLink(link);
            dtsAd.setPosition(position);
            dtsAd.setContent(content);
            dtsAd.setStartTime(startTime);
            dtsAd.setEndTime(endTime);
            dtsAd.setUpdateTime(new Date()); // 设置更新时间

            // 保存更新后的广告信息到数据库
            // 更新广告信息到数据库
            dtsAdService.update(dtsAd);

            // 返回更新成功的响应
            // 返回更新成功信息
            return ResponseEntity.ok("更新成功");
        } catch (ParseException e) {
            // 返回日期格式错误的响应
            // 返回日期格式错误信息
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("日期格式错误: " + e.getMessage());
        } catch (Exception e) {
            // 返回更新失败的响应
            // 返回更新失败信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失败: " + e.getMessage());
        }
    }

    /**
     * 通过GET请求查询所有广告信息。
     * <p>
     * 本接口提供了一个方法来检索系统的全部广告数据。通过传入DtsAd对象，可以基于特定条件查询广告。
     * 如果不传入任何条件，则返回所有广告记录。这对于前端展示广告列表或进行广告管理非常有用。
     *
     * @param dtsAd 广告实体类，用于传递查询条件和返回结果。
     * @return 返回匹配条件的广告列表。如果没有任何匹配的广告，则返回空列表。
     */
    @ApiOperation(value = "查询广告")
    @GetMapping("/selectDtsAdAll")
    public List<DtsAd> selectDtsAdAll(DtsAd dtsAd) {
        return dtsAdService.queryAllByLimit(dtsAd);
    }

    /**
     * 通过API操作删除广告。
     * 使用@ApiOperation注解来描述该API的操作，value属性指明操作的名称。
     * 使用@PostMapping注解来指定该方法处理的HTTP请求方法和请求的URL路径。
     * 请求体中的DtsAd对象包含需要删除的广告的信息。
     * 方法返回一个ResponseEntity<String>对象，其中包含操作的结果信息。
     *
     * @param record 包含待删除广告信息的DtsAd对象。
     * @return 如果广告删除成功，返回包含"删除成功"信息的ResponseEntity；如果删除失败，返回包含"删除失败"信息的ResponseEntity，且状态码为500。
     */
    @ApiOperation(value = "删除广告")
    @PostMapping("/deleteDtsAd")
    public ResponseEntity<String> deleteDtsAd(@RequestBody DtsAd record) {
        // 判断是否成功删除广告
        if (dtsAdService.deleteById(record.getId())) {
            return ResponseEntity.ok("删除成功");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除失败");
        }
    }

    /**
     * 分页查询广告信息。
     * 通过此接口可以实现广告的分页查询，支持根据广告的一些条件进行筛选。
     *
     * @param dtsAd    广告实体类，其中可能包含用于查询的条件。
     * @param pageNum  当前页码，用于指定查询的页数，默认为1。
     * @param pageSize 每页显示的广告数量，用于控制分页大小，默认为10。
     * @return 返回一个Map对象，其中包含查询到的广告列表和总条数。
     * "data" 键对应的值为广告总条数，"list" 键对应的值为广告列表。
     */
    @ApiOperation(value = "分页查询广告")
    @GetMapping("/selectDtsAdByPage")
    public Map<String, Object> selectDtsAd(DtsAd dtsAd,
                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();
        // 开始分页查询，这里使用了PageHelper插件来实现分页
        PageHelper.startPage(pageNum, pageSize);
        // 调用服务层方法查询广告信息，根据dtsAd中的条件进行查询
        List<DtsAd> list = dtsAdService.queryAllByLimit(dtsAd);
        // 使用PageInfo对查询结果进行包装，获取分页信息
        PageInfo<DtsAd> info = new PageInfo<>(list);
        // 将查询结果的总条数和广告列表分别放入返回的Map中
        map.put("data", info.getTotal());
        map.put("list", list);

        return map;
    }

    /**
     * 模糊查询广告信息。
     * 通过GET请求和可选的name参数来查询广告数据。
     * 如果查询结果为空，则返回404状态码和空响应体。
     * 如果查询结果不为空，则返回200状态码和查询结果列表。
     *
     * @param name 广告名称，可选参数，用于模糊查询广告。
     * @return ResponseEntity 包含查询结果的响应体，如果结果为空，则状态码为404。
     */
    @ApiOperation(value = "模糊查询")
    @GetMapping("/selectDtsAdByLike")
    public ResponseEntity<List<DtsAd>> selectDtsAdByLike(@RequestParam(required = false) String name) {
        // 创建一个新的DtsAd对象，并设置查询条件
        DtsAd dtsAd = new DtsAd();
        dtsAd.setName(name);

        // 执行模糊查询，获取所有匹配的广告信息
        List<DtsAd> dtsAds = dtsAdMapper.queryAllByLimit(dtsAd);

        // 如果查询结果为空，返回404状态码和空响应体
        if (dtsAds.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            // 如果查询结果不为空，返回200状态码和查询结果列表
            // 如果查询结果不为空，返回查询结果
            return ResponseEntity.ok(dtsAds);

        }
    }

}


