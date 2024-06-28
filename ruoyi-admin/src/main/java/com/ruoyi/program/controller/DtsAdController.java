package com.ruoyi.program.controller;

import com.ruoyi.program.entity.DtsAd;
import com.ruoyi.program.service.DtsAdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"广告管理"})
@RestController
@RequestMapping("/adStatement")
public class DtsAdController {
    @Autowired
    private DtsAdService dtsAdService;

    private static final Logger logger = LoggerFactory.getLogger(DtsUserController.class);


    @ApiOperation(value = "插入广告")
    @PostMapping("/insertDtsAd")
    public ResponseEntity<?> insertDtsAd(@RequestParam("url") MultipartFile file) {
        // 验证主文件是否为空
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("主文件不能为空");
        }

        Date date = new Date();
        // 创建广告对象
        DtsAd dtsAd = new DtsAd();

        // 上传主文件并获取文件路径
        String fileUrl = dtsAdService.uploadDtsAd(file);

        // 判断主文件类型，并保存到相应字段
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        dtsAd.setUrl(fileUrl);

        dtsAd.setName(fileExtension);
        dtsAd.setLink(fileExtension);
        dtsAd.setContent(fileExtension);
        dtsAd.setPosition(1);
        dtsAd.setStartTime(date);
        dtsAd.setEndTime(date);
        dtsAd.setEnabled(1);

        // 保存广告信息到数据库
        dtsAdService.insert(dtsAd);
        return ResponseEntity.ok("上传成功");
    }


}
