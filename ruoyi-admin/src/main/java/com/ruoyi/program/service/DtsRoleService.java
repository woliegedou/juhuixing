package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsRole;

import java.util.List;

public interface DtsRoleService {
    /**
     * 添加角色
     */
    int insertRole(DtsRole dtsRole);

    /**
     * 修改角色
     */
    int updateRole(DtsRole dtsRole);

    /**
     * 查询角色列表
     */
    List<DtsRole> selectRoleList();

    /**
     * 模糊查询
     */
    List<DtsRole> selectRoleListFuzzyQuery(String name);

    /**
     * 删除角色
     */
    boolean deleteRole(DtsRole dtsRole);
}
