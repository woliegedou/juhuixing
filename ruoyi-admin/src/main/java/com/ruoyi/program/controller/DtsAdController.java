package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.program.entity.DTO.DtsAdDTO;
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

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ruoyi.common.utils.DateUtils.parseDate;

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
     * 该接口用于接收广告相关的数据传输对象（DTO），并将广告图片上传至服务器，
     * 同时插入新的广告记录到数据库。
     *
     * @param dtsAdDTO 包含广告信息的数据传输对象，包括广告图片文件和其他广告细节。
     * @return ResponseEntity<?> 返回一个响应实体，包含操作的结果信息。
     */
    @ApiOperation(value = "插入广告", notes = "上传广告图片并插入广告信息")
    @PostMapping("/insertDtsAd")
    public ResponseEntity<?> insertDtsAd(@ModelAttribute DtsAdDTO dtsAdDTO) {
        // 检查上传的文件是否为空
        // 检查文件是否为空
        if (dtsAdDTO.getFile() == null || dtsAdDTO.getFile().isEmpty()) {
            return ResponseEntity.badRequest().body("主文件不能为空");
        }

        try {
            // 上传广告图片并获取文件名
            // 上传广告图片文件并获取文件URL
            String fileName = dtsAdService.uploadDtsAd(dtsAdDTO.getFile());


            // 设置广告的开始时间和结束时间
            // 设置广告对象的属性
            dtsAdDTO.setStartTime(new Date());
            dtsAdDTO.setEndTime(new Date(3));
            // 设置广告图片的URL
            dtsAdDTO.setUrl(fileName);
            // 插入新的广告记录
            // 保存广告对象
            dtsAdService.insert(dtsAdDTO);

            return ResponseEntity.ok("上传成功");
        } catch (Exception e) {
            // 返回上传失败的响应，包含错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上传失败: " + e.getMessage());
        }
    }

    /**
     * 更新广告信息。
     * 该接口用于接收广告相关的数据传输对象（DTO），并将广告图片上传至服务器，
     * 同时更新数据库中的广告记录。
     *
     * @param dtsAdDTO 包含广告信息的数据传输对象，包括广告图片文件和其他广告细节。
     * @return ResponseEntity<?> 返回一个响应实体，包含操作的结果信息。
     */
    @ApiOperation(value = "更新广告", notes = "上传广告图片并更新广告信息")
    @PostMapping("/updateDtsAd")
    public ResponseEntity<?> updateInsertDtsAd(@ModelAttribute DtsAdDTO dtsAdDTO) {
        // 检查上传的文件是否为空
        if (dtsAdDTO.getFile() == null || dtsAdDTO.getFile().isEmpty()) {
            return ResponseEntity.badRequest().body("主文件不能为空");
        }

        try {
            // 上传广告图片文件并获取文件名
            String fileName = dtsAdService.uploadDtsAd(dtsAdDTO.getFile());

            // 获取当前时间
            Date now = new Date();

            // 设置广告的开始时间为当前时间
            dtsAdDTO.setStartTime(now);

            // 设置广告的结束时间为当前时间后7天
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            dtsAdDTO.setEndTime(calendar.getTime());

            // 设置广告图片的URL
            dtsAdDTO.setUrl(fileName);

            // 更新现有广告记录
            dtsAdService.update(dtsAdDTO);

            return ResponseEntity.ok("更新成功");
        } catch (Exception e) {
            // 返回上传失败的响应，包含错误信息
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


