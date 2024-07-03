package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtsAdminMapper {

    /**
     * 添加管理员
     */
    Long insertadmin(DtsAdmin dtsAdmin);

    /**
     * 查询全部管理员
     */
    List<DtsAdmin> selectadmin();

    /**
     * 修改管理员
     */
    int updateadmin(DtsAdmin dtsAdmin);

    /**
     * 根据id查询管理员
     */
    DtsAdmin getAdminById(Integer id);

    /**
     * 根据id删除管理员
     */
    int deleteById(Integer id);

    /**
     * 根据管理名称查询管理员
     */
    List<DtsAdmin> getAdminByNameFuzzy(String username);


}
