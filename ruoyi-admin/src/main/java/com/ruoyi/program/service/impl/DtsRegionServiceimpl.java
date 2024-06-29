package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsRegion;
import com.ruoyi.program.mapper.DtsRegionMapper;
import com.ruoyi.program.service.DtsRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DtsRegionServiceimpl implements DtsRegionService {

    @Autowired
    private DtsRegionMapper dtsRegionMapper;

    @Override
    public List<DtsRegion> DtsRegionid(Integer id) {
        return dtsRegionMapper.DtsRegionid(id);
    }

    /**
     * 根据地区ID查询所有的DtsRegion，并构建它们的树结构。
     *
     * @return 包含所有DtsRegion的树结构列表。
     */
    @Override
    public List<DtsRegion> dtsRegionids() {
        // 从数据库查询所有DtsRegion信息
        List<DtsRegion> queriedAll = dtsRegionMapper.dtsRegionids();
        // 以根节点ID为0构建地区树结构
        return buildTree(queriedAll, 0L);
    }

    /**
     * 递归构建DtsRegion的树结构。
     *
     * @param queriedAll 所有查询到的DtsRegion列表。
     * @param pid        父ID，用于找到当前父节点的所有子节点。
     * @return 构建完成的DtsRegion子树列表。
     */
    private List<DtsRegion> buildTree(List<DtsRegion> queriedAll, Long pid) {
        // 使用流式编程过滤出父ID为指定值的DtsRegion项，并构建树结构
        return queriedAll.stream()
                .filter(dtsRegion -> {
                    Long dtsRegionPid = dtsRegion.getPid();
                    // 过滤出父ID不为空且等于指定值的DtsRegion
                    return dtsRegionPid != null && dtsRegionPid.equals(pid);
                })
                .map(dtsRegion -> {
                    // 递归构建当前DtsRegion项的子树，并设置到当前DtsRegion项的children属性中
                    dtsRegion.setChildren(buildTree(queriedAll, dtsRegion.getId()));
                    return dtsRegion;
                })
                .collect(Collectors.toList());
    }


    @Override
    public int insertdtsRegion(DtsRegion dtsRegion) {
        return dtsRegionMapper.insertdtsRegion(dtsRegion);
    }

    @Override
    public int updatedtsRegion(DtsRegion dtsRegion) {
        return dtsRegionMapper.updatedtsRegion(dtsRegion);
    }

    @Override
    public List<DtsRegion> queryAllByLimit(DtsRegion dtsRegion) {
        return dtsRegionMapper.queryAllByLimit(dtsRegion);
    }

    @Override
    public boolean deletedtsRegion(Long id) {
        return dtsRegionMapper.deletedtsRegion(id) > 0;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param qbMajors 专业列表，包含各个专业的信息，其中含有父节点ID用于构建树结构关系。
     * @return 返回构建好的树结构列表，每个专业节点可能包含多个子专业节点。
     */
    public List<DtsRegion> buildDeptTree(List<DtsRegion> qbMajors) {
        // 初始化返回的树结构列表
        List<DtsRegion> returnList = new ArrayList<>();
        // 提取专业列表中的所有ID，用于后续判断节点是否为顶级节点
        List<Long> tempList = qbMajors.stream().map(DtsRegion::getId).collect(Collectors.toList());
        // 遍历专业列表，寻找并构建所有顶级节点及其子节点
        for (DtsRegion dtsRegion : qbMajors) {
            // 判断当前节点是否为顶级节点（即没有父节点）
            if (!tempList.contains(dtsRegion.getPid())) {
                // 递归构建当前顶级节点的子节点
                recursionFn(qbMajors, dtsRegion);
                // 将构建好的顶级节点及其子节点加入到返回的树结构列表中
                returnList.add(dtsRegion);
            }
        }
        // 如果没有找到顶级节点，则直接返回原始专业列表
        if (returnList.isEmpty()) {
            returnList = qbMajors;
        }
        return returnList;
    }

    /**
     * 递归函数，用于构建指定节点的子节点树
     *
     * @param list 专业列表，用于从中寻找子节点。
     * @param t    当前正在处理的节点。
     */
    private void recursionFn(List<DtsRegion> list, DtsRegion t) {
        // 获取当前节点的子节点列表
        List<DtsRegion> childList = getChildList(list, t);
        t.setChildren(childList);
        // 对每个子节点，若有其自身的子节点，则递归构建
        for (DtsRegion tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 根据父节点ID，获取子节点列表
     *
     * @param list 专业列表，用于从中筛选出子节点。
     * @param t    父节点。
     * @return 返回匹配的子节点列表。
     */
    private List<DtsRegion> getChildList(List<DtsRegion> list, DtsRegion t) {
        return list.stream().filter(dtsRegion -> dtsRegion.getPid().equals(t.getId())).collect(Collectors.toList());
    }

    /**
     * 检查指定节点是否有子节点
     *
     * @param list 专业列表，用于检查子节点的存在。
     * @param t    要检查是否有子节点的节点。
     * @return 如果指定节点有子节点，则返回true，否则返回false。
     */
    private boolean hasChild(List<DtsRegion> list, DtsRegion t) {
        // 通过获取子节点列表的大小来判断是否有子节点
        return getChildList(list, t).size() > 0;
    }

}
