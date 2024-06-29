package com.ruoyi.program.controller;

import com.ruoyi.program.entity.DtsAd;
import com.ruoyi.program.service.DtsAdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(tags = {"广告管理"})
@RestController
@RequestMapping("/adStatement")
public class DtsAdController {
    @Autowired
    private DtsAdService dtsAdService;

    private static final Logger logger = LoggerFactory.getLogger(DtsUserController.class);


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
}
