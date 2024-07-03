package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsAdmin;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DtsAdminService {

    /**
     * 添加管理员
     */
    Long insertadmin(DtsAdmin dtsAdmin);

    /**
     * 查询全部管理员
     */
    List<DtsAdmin> selectadmin();

    /**
     * 根据id查询管理员
     */
    DtsAdmin getAdminById(Integer id);

    /**
     * 修改管理员
     */
    int updateadmin(DtsAdmin dtsAdmin);

    /**
     * 上传头像
     */
    String uploadDtsAdmin(MultipartFile file);

    /**
     * 删除管理员
     */
    boolean deleteById(Integer id);

    /**
     * 根据管理名称查询管理员
     */
    List<DtsAdmin> getAdminByNameFuzzy(String username);
}
