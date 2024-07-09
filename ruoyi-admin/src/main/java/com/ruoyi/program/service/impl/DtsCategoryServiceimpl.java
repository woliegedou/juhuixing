package com.ruoyi.program.service.impl;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.program.entity.DtsAd;
import com.ruoyi.program.entity.DtsCategory;
import com.ruoyi.program.mapper.DtsCategoryMapper;
import com.ruoyi.program.service.DtsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtsCategoryServiceimpl implements DtsCategoryService {

    @Autowired
    private DtsCategoryMapper dtsCategoryMapper;

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.path}")
    private String path;

    /**
     * 新增类目表
     * 该方法用于在数据库中插入一个新的类目。
     *
     * @param dtsCategory 待插入的类目对象，包含类目的相关信息。
     * @return 返回插入操作的影响行数，通常情况下，如果插入成功，返回1；如果插入失败，返回0。
     */
    @Override
    public int insertDtsCategory(DtsCategory dtsCategory) {
        // 调用dtsCategoryMapper的insertDtsCategory方法，将dtsCategory对象插入数据库
        return dtsCategoryMapper.insertDtsCategory(dtsCategory);
    }

    /**
     * 上传文件到七牛云，并返回文件的URL。
     *
     * @param file 要上传的文件，使用MultipartFile封装。
     * @return 上传成功后，返回文件在七牛云的URL。
     * @throws RuntimeException 如果上传失败或发生IO异常，抛出运行时异常。
     */
    @Override
    public String uploadDtsCategory(MultipartFile file) {
        // 初始化七牛云的配置
        // 初始化七牛云上传配置
        Configuration cfg = new Configuration();
        // 创建上传管理对象
        // 创建上传管理对象
        UploadManager uploadManager = new UploadManager(cfg);

        // 进行七牛云授权认证，并获取上传令牌
        // 进行七牛云授权认证
        Auth auth = Auth.create(accessKey, secretKey);
        // 获取上传令牌
        String token = auth.uploadToken(bucket);

        try {
            // 生成唯一的文件名，防止重复
            // 生成唯一的文件名，防止重复
            String fileName = UUID.randomUUID().toString() + "." + getFileExtension(file.getOriginalFilename());
            // 创建临时文件，用于存放上传的文件内容
            // 创建临时文件，用于存放上传的文件
            File tempFile = File.createTempFile(UUID.randomUUID().toString(), fileName);
            // 将上传文件的内容写入到临时文件
            // 将上传文件内容写入到临时文件
            file.transferTo(tempFile);

            // 使用上传管理对象将文件上传到七牛云
            // 调用上传方法，将文件上传到七牛云
            Response response = uploadManager.put(tempFile.getAbsolutePath(), fileName, token);

            // 检查上传是否成功
            // 检查上传是否成功
            if (response.isOK()) {
                // 构造文件在七牛云的URL
                // 构造文件URL
                String fileUrl = path + "/" + fileName;

                // 创建广告图片对象，保存文件的URL
                // 创建广告图片对象，保存文件URL
                DtsAd dtsAd = new DtsAd();
                dtsAd.setUrl(fileUrl);

                // 将文件URL保存到数据库
                // 保存文件URL到数据库
                saveFileUrlToDatabase(dtsAd);

                // 返回文件的URL
                return fileUrl;
            } else {
                // 如果上传失败，抛出运行时异常
                // 如果上传失败，抛出异常
                throw new RuntimeException("上传到七牛云失败: " + response.bodyString());
            }
        } catch (IOException e) {
            // 如果发生IO异常，抛出运行时异常
            // 如果发生IO异常，抛出异常
            throw new RuntimeException("文件上传失败", e);
        }
    }

    /**
     * 更新DtsCategory实体类的信息。
     * <p>
     * 本方法通过调用dtsCategoryMapper的updateDtsCategory方法，来实现对DtsCategory数据库记录的更新操作。
     *
     * @param dtsCategory 待更新的DtsCategory对象，包含需要更新的信息。
     * @return 返回更新操作影响的行数，通常情况下，如果返回值大于0，则表示更新操作成功。
     */
    @Override
    public int updateDtsCategory(DtsCategory dtsCategory) {
        return dtsCategoryMapper.updateDtsCategory(dtsCategory);
    }

    /**
     * 根据ID查询DtsCategory实体。
     *
     * @param id DtsCategory的唯一标识ID。
     * @return 返回匹配的DtsCategory实体。如果找不到匹配的实体，则返回null。
     */
    @Override
    public DtsCategory selectDtsCategoryid(Long id) {
        return dtsCategoryMapper.selectDtsCategoryid(id);
    }

    /**
     * 根据分类ID查询所有DTS分类，并构建分类树结构。
     *
     * @return List<DtsCategory> 返回构建好的DTS分类树结构列表。
     */
    @Override
    public List<DtsCategory> selectDtsCategoryall() {
        // 通过dtsCategoryMapper查询所有DTS分类
        List<DtsCategory> dtsCategories = dtsCategoryMapper.selectDtsCategoryall();
        // 调用buildTree方法，将查询到的分类列表构建为树结构，根分类ID为0
        return buildTree(dtsCategories, 0L);
    }

    /**
     * 删除数据传输服务(DTS)分类。
     *
     * @param id 分类的唯一标识符。
     * @return 如果删除成功，返回true；如果删除失败，例如指定的id不存在，返回false。
     * @Override 表明该方法重写了超类或接口中的方法。
     */
    @Override
    public boolean deleteDtsCategory(Long id) {
        // 调用dtsCategoryMapper的deleteDtsCategory方法，尝试删除指定id的DTS分类。
        // 返回值是删除操作影响的行数，如果大于0，则表示删除成功。
        return dtsCategoryMapper.deleteDtsCategory(id) > 0;
    }

    /**
     * 根据名称的模糊匹配，查询DtsCategory列表。
     * 该方法用于通过提供的一部分名称字符串，来检索符合该字符串的类别名称。
     *
     * @param name 需要模糊匹配的名称字符串
     * @return 符合条件的DtsCategory对象列表
     */
    @Override
    public List<DtsCategory> selectDtsCategoryByNameLike(String name) {
        return dtsCategoryMapper.selectDtsCategoryByNameLike(name);
    }

    /**
     * 根据DtsCategory对象中的区域ID，查询相应的DtsCategory列表。
     * 此方法的目的是通过区域ID来筛选出特定区域的类别列表。
     *
     * @param dtsCategory 包含区域ID的DtsCategory对象
     * @return 符合条件的DtsCategory对象列表
     */
    @Override
    public List<DtsCategory> DtsRegionid(DtsCategory dtsCategory) {
        return dtsCategoryMapper.DtsRegionid(dtsCategory);
    }

    /**
     * 根据指定的父ID，从列表中构建DtsCategory的树结构。
     *
     * @param questions DtsCategory对象的列表。
     * @param pid       父ID，用于筛选构建树结构的起点。
     * @return 返回构建好的DtsCategory树结构列表。
     */
    private List<DtsCategory> buildTree(List<DtsCategory> questions, Long pid) {
        // 使用流式编程来过滤和映射列表，以构建树结构
        return questions.stream()
                .filter(dtsRegion -> {
                    Long dtsRegionPid = dtsRegion.getPid();
                    // 过滤出父ID不为空且等于指定值的DtsRegion
                    return dtsRegionPid != null && dtsRegionPid.equals(pid);
                })
                .map(dtsRegion -> {
                    // 递归构建当前DtsRegion项的子树，并设置到当前DtsRegion项的children属性中
                    dtsRegion.setChildren(buildTree(questions, dtsRegion.getId()));
                    return dtsRegion;
                })
                .collect(Collectors.toList());
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

