package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsRole;
import com.ruoyi.program.mapper.DtsRoleMapper;
import com.ruoyi.program.service.DtsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DtsRoleServiceimpl implements DtsRoleService {

    @Autowired
    private DtsRoleMapper dtsRoleMapper;

    /**
     * 插入角色信息到数据库。
     * <p>
     * 本方法通过调用dtsRoleMapper的insertRole方法，将dtsRole对象插入到数据库中。
     * 主要用于新增角色操作，通过传递DtsRole对象来完成数据的插入。
     *
     * @param dtsRole 待插入的角色对象，包含角色的详细信息。
     * @return 返回插入操作的影响行数，通常情况下，如果插入成功，返回1；如果插入失败，返回0。
     */
    @Override
    public int insertRole(DtsRole dtsRole) {
        // 调用dtsRoleMapper的insertRole方法插入角色信息
        int insertRole = dtsRoleMapper.insertRole(dtsRole);
        // 返回插入操作的影响行数
        return insertRole;
    }

    /**
     * 更新角色信息。
     * <p>
     * 本方法通过调用DtsRoleMapper的updateRole方法，来更新数据库中的角色信息。
     * 具体更新的内容取决于传入的DtsRole对象的属性值。
     *
     * @param dtsRole 包含待更新角色信息的DtsRole对象。
     * @return 返回更新操作影响的行数。
     */
    @Override
    public int updateRole(DtsRole dtsRole) {
        // 调用DtsRoleMapper的updateRole方法更新角色信息
        int updateRole = dtsRoleMapper.updateRole(dtsRole);
        // 返回更新操作影响的行数
        return updateRole;
    }

    /**
     * 查询角色列表
     * <p>
     * 本方法通过调用dtsRoleMapper的selectRoleList方法，获取角色列表。
     * 它不接受任何参数，返回一个DtsRole类型的列表。
     * 这个方法是对上级接口方法的实现，旨在提供查询所有角色的功能。
     *
     * @return List<DtsRole> 返回角色列表
     */
    @Override
    public List<DtsRole> selectRoleList() {
        // 调用dtsRoleMapper的selectRoleList方法查询角色列表
        List<DtsRole> dtsRoles = dtsRoleMapper.selectRoleList();
        // 返回查询结果
        return dtsRoles;
    }

    /**
     * 根据角色名进行模糊查询，返回匹配的角色列表。
     *
     * @param name 角色名的查询条件，支持模糊查询。
     * @return 匹配的角色列表。
     */
    @Override
    public List<DtsRole> selectRoleListFuzzyQuery(String name) {
        // 调用dtsRoleMapper的selectRoleListFuzzyQuery方法，传入查询条件name，返回模糊查询的结果列表
        List<DtsRole> dtsRoles = dtsRoleMapper.selectRoleListFuzzyQuery(name);
        return dtsRoles;
    }

    /**
     * 删除角色。
     * <p>
     * 本方法用于根据传入的角色对象删除数据库中的相应角色记录。
     * 它通过调用DtsRoleMapper的deleteRole方法来实现实际的删除操作。
     * 删除操作的成功与否取决于是否删除了至少一条记录，因此返回值是删除记录的数量是否大于0。
     *
     * @param dtsRole 要删除的角色对象，包含角色的详细信息。
     * @return 如果删除成功（即删除了至少一条记录），返回true；否则返回false。
     */
    @Override
    public boolean deleteRole(DtsRole dtsRole) {
        // 调用DtsRoleMapper的deleteRole方法删除角色，并检查删除的记录数是否大于0
        return dtsRoleMapper.deleteRole(dtsRole) > 0;
    }
}
