package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtsCategoryMapper {

    /**
     * 添加方法
     * @param dtsCategory
     * @return
     */
    int insertDtsCategory(DtsCategory dtsCategory);
    /**
     * 修改方法
     * @param dtsCategory
     * @return
     */
    int updateDtsCategory(DtsCategory dtsCategory);

    /**
     * 通过主键进行查询
     * @param id
     * @return
     */
    DtsCategory selectDtsCategoryid(Long id);

    /**
     * 查询全部
     * @param
     * @return
     */
    List<DtsCategory> selectDtsCategoryall();

    /**
     * 删除方法
     * @param id
     * @return
     */
    int deleteDtsCategory(Long id);

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
