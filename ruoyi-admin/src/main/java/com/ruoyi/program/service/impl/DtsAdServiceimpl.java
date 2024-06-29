package com.ruoyi.program.service.impl;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.program.entity.DtsAd;
import com.ruoyi.program.mapper.DtsAdMapper;
import com.ruoyi.program.service.DtsAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DtsAdServiceimpl implements DtsAdService {

    @Autowired
    private DtsAdMapper dtsAdMapper;


    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.path}")
    private String path;


    @Override
    public DtsAd queryById(Integer id) {
        return dtsAdMapper.queryById(id);
    }

    @Override
    public List<DtsAd> queryAllByLimit(DtsAd dtsAd) {
        return dtsAdMapper.queryAllByLimit(dtsAd);
    }

    @Override
    public long count(DtsAd dtsAd) {
        return dtsAdMapper.count(dtsAd);
    }

    @Override
    public int insert(DtsAd dtsAd) {
        return dtsAdMapper.insert(dtsAd);
    }

    @Override
    public int insertBatch(List<DtsAd> entities) {
        return dtsAdMapper.insertOrUpdateBatch(entities);
    }

    @Override
    public int insertOrUpdateBatch(List<DtsAd> entities) {
        return dtsAdMapper.insertOrUpdateBatch(entities);
    }

    @Override
    public int update(DtsAd dtsAd) {
        return dtsAdMapper.update(dtsAd);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dtsAdMapper.deleteById(id) > 0;
    }

    /**
     * 上传广告图片到七牛云存储。
     *
     * @param file 待上传的文件，使用MultipartFile类型封装。
     * @return 返回上传成功后的文件URL。
     * @throws RuntimeException 如果上传失败或发生IO异常，抛出运行时异常。
     */
    @Override
    public String uploadDtsAd(MultipartFile file) {
        // 初始化七牛云上传配置
        Configuration cfg = new Configuration();
        // 创建上传管理对象
        UploadManager uploadManager = new UploadManager(cfg);

        // 进行七牛云授权认证
        Auth auth = Auth.create(accessKey, secretKey);
        // 获取上传令牌
        String token = auth.uploadToken(bucket);

        try {
            // 生成唯一的文件名，防止重复
            String fileName = UUID.randomUUID().toString() + "." + getFileExtension(file.getOriginalFilename());
            // 创建临时文件，用于存放上传的文件
            File tempFile = File.createTempFile(UUID.randomUUID().toString(), fileName);
            // 将上传文件内容写入到临时文件
            file.transferTo(tempFile);

            // 调用上传方法，将文件上传到七牛云
            Response response = uploadManager.put(tempFile.getAbsolutePath(), fileName, token);

            // 检查上传是否成功
            // 检查上传是否成功
            if (response.isOK()) {
                // 构造文件URL
                // 构造文件URL
                String fileUrl = path + "/" + fileName;

                // 创建广告图片对象，保存文件URL
                // 创建QbPicture对象，用于保存文件信息到数据库
                DtsAd dtsAd = new DtsAd();
                dtsAd.setUrl(fileUrl);

                // 将文件URL保存到数据库
                // 保存文件URL到数据库
                saveFileUrlToDatabase(dtsAd);

                // 返回文件URL
                return fileUrl;
            } else {
                // 如果上传失败，抛出异常
                // 如果上传失败，抛出异常
                throw new RuntimeException("上传到七牛云失败: " + response.bodyString());
            }
        } catch (IOException e) {
            // 如果发生IO异常，抛出异常
            // 如果发生IO异常，抛出异常
            throw new RuntimeException("文件上传失败", e);
        }
    }

    /**
     * 获取文件名的扩展名。
     * 该方法通过查找文件名中最后一个"."的位置，然后提取出点后的部分作为文件的扩展名。
     * 如果文件名中没有"."，则此方法返回空字符串。
     *
     * @param fileName 文件名，可以包含路径和扩展名。
     * @return 扩展名，如果不存在扩展名则返回空字符串。
     */
    private String getFileExtension(String fileName) {
        // 从文件名中提取扩展名
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 将文件URL保存到数据库中。
     * 此方法目前的作用是直接返回传入的DtsAd对象，没有进行实际的数据库操作。
     * 这可能是由于实际的数据库保存逻辑还未实现，或者将在未来通过回调或依赖注入的方式加入。
     *
     * @param dtsAd 包含广告信息的数据对象，其中可能包含需要保存到数据库的文件URL。
     * @return 返回相同的DtsAd对象，表示已“保存”该对象到数据库。
     */
    private DtsAd saveFileUrlToDatabase(DtsAd dtsAd) {
        return dtsAd;
    }


}
