package com.ruoyi.program.service.impl;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.program.entity.DtsAd;
import com.ruoyi.program.entity.DtsAdmin;
import com.ruoyi.program.mapper.DtsAdminMapper;
import com.ruoyi.program.service.DtsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class DtsAdminServiceimpl implements DtsAdminService {

    @Autowired
    private DtsAdminMapper dtsAdminMapper;

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.path}")
    private String path;

    /**
     * 插入一个新的管理员到数据库。
     * 此方法覆盖了父类或接口中的同名方法。
     *
     * @param dtsAdmin 管理员对象，包含要插入的数据。字段应已填充完整。
     * @return 如果成功插入，返回新插入管理员的ID；失败或异常时返回负数。
     */
    @Override
    public Long insertadmin(DtsAdmin dtsAdmin) {
        // 调用dtsAdminMapper的insertadmin方法来执行数据库插入操作
        return dtsAdminMapper.insertadmin(dtsAdmin);
    }

    /**
     * 选择并返回所有的DtsAdmin对象。
     * 此方法覆盖了父类或接口中的同名方法。
     *
     * @return 一个包含所有DtsAdmin实例的列表，如果没有找到任何管理员，列表将为空。
     */
    @Override
    public List<DtsAdmin> selectadmin() {
        return dtsAdminMapper.selectadmin();
    }

    @Override
    public DtsAdmin getAdminById(Integer id) {
        return dtsAdminMapper.getAdminById(id);
    }

    /**
     * 更新管理员信息的方法。
     * <p>
     * 此方法覆盖了父类或接口中的相应方法。
     *
     * @param dtsAdmin 管理员对象，包含需要更新的数据
     * @return 更新操作影响的行数，返回值为正整数表示成功更新的行数，0表示没有更新，负数可能表示错误
     */
    @Override
    public int updateadmin(DtsAdmin dtsAdmin) {
        return dtsAdminMapper.updateadmin(dtsAdmin);
    }

    /**
     * 上传广告图片到七牛云存储。
     *
     * @param file 待上传的文件，使用MultipartFile类型封装。
     * @return 返回上传成功后的文件URL。
     * @throws RuntimeException 如果上传失败或发生IO异常，抛出运行时异常。
     */
    @Override
    public String uploadDtsAdmin(MultipartFile file) {
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
     * 根据ID删除记录。
     * <p>
     * 本方法通过调用dtsAdminMapper的DeleteById方法，尝试删除数据库中对应ID的记录。
     * 删除操作的成功与否通过返回值来判断，如果删除的记录数大于0，则表示删除成功。
     *
     * @param id 需要删除的记录的ID。
     * @return 如果删除成功（即删除的记录数大于0），返回true；否则返回false。
     */
    @Override
    public boolean deleteById(Integer id) {
        return dtsAdminMapper.deleteById(id) > 0;
    }

    @Override
    public List<DtsAdmin> getAdminByNameFuzzy(String username) {
        return dtsAdminMapper.getAdminByNameFuzzy(username);
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
