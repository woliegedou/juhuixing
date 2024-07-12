package com.ruoyi.program.entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@ApiModel(description = "DTO for file upload request")
public class DtsAdDTO {

    private int id;
    private String name;
    private String link;
    private String content;
    private int position;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private int enabled;

    /**
     * 接受上传图片
     */
    @ApiModelProperty(value = "File to upload", required = true)
    private MultipartFile file;
    /**
     * 图片路径
     */
    private String url;
}
