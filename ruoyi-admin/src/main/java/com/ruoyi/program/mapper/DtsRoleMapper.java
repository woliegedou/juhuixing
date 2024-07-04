package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtsRoleMapper {
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
    int deleteRole(DtsRole dtsRole);
}
