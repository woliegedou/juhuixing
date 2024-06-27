package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户表(DtsUser)表服务接口
 *
 * @author makejava
 * @since 2024-06-27 09:57:55
 */
public interface DtsUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DtsUser queryById(Integer id);

    /**
     * 分页查询
     *
     * @param dtsUser 筛选条件
     * @return 查询结果
     */
    List<DtsUser> queryByPage(DtsUser dtsUser);

    /**
     * 新增数据
     *
     * @param dtsUser 实例对象
     * @return 实例对象
     */
    int insert(DtsUser dtsUser);

    /**
     * 修改数据
     *
     * @param dtsUser 实例对象
     * @return 实例对象
     */
    int update(DtsUser dtsUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
