package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DtsCategoryService {
    /**
     * 添加方法
     *
     * @param dtsCategory
     * @return
     */
    int insertDtsCategory(DtsCategory dtsCategory);


    /**
     * 上传方法
     *
     * @param file
     * @return
     */
    String uploadDtsCategory(MultipartFile file);


    /**
     * 修改方法
     *
     * @param dtsCategory
     * @return
     */
    int updateDtsCategory(DtsCategory dtsCategory);

    /**
     * 通过主键进行查询
     *
     * @param id
     * @return
     */
    DtsCategory selectDtsCategoryid(Long id);

    /**
     * 查询全部
     *
     * @param
     * @return
     */
    List<DtsCategory> selectDtsCategoryall();

    /**
     * 删除方法
     * @param id
     * @return
     */
    boolean deleteDtsCategory(Long id);

    /**
     * 模糊查询
     * @param name
     * @return
     */
    List<DtsCategory> selectDtsCategoryByNameLike(String name);


    /**
     * 查询指定
     * @param dtsCategory
     * @return
     */
    List<DtsCategory> DtsRegionid(DtsCategory dtsCategory);

}
