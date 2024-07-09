package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.program.entity.DtsArticle;
import com.ruoyi.program.entity.DtsCategory;
import com.ruoyi.program.service.DtsCategoryService;
import com.ruoyi.program.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dtsCategory")
@Api(tags = "类目表")
public class DtsCategoryController {

    @Autowired
    private DtsCategoryService dtsCategoryService;


    /**
     * 添加分类并上传图片
     * 通过此接口，可以向系统中添加一个新的分类，并同时上传分类的图标和图片。
     *
     * @param iconFile  分类图标文件，用于上传图标图像。
     * @param picFile   分类图片文件，用于上传分类的展示图片。
     * @param name      分类名称，用于标识分类。
     * @param pid       分类的父ID，用于建立分类的层级关系。
     * @param level     分类的级别，用于表示分类在层级结构中的位置。
     * @param sortOrder 分类的排序顺序，用于控制分类的显示顺序。
     * @param desc      分类的描述，用于提供关于分类的详细信息。
     * @param keywords  分类的关键字，用于搜索和索引。
     * @return 添加分类的结果，成功返回成功信息，失败返回错误信息。
     */
    @ApiOperation(value = "添加类目并上传图片")
    @PostMapping("/uploadDtsCategory")
    public AjaxResult uploadDtsCategory(@RequestParam("iconUrl") MultipartFile iconFile,
                                        @RequestParam("picUrl") MultipartFile picFile,
                                        @RequestParam("name") String name,
                                        @RequestParam("pid") Long pid,
                                        @RequestParam("level") String level,
                                        @RequestParam("sortOrder") Integer sortOrder,
                                        @RequestParam("desc") String desc,
                                        @RequestParam("keywords") String keywords
    ) {
        try {
            // 上传图标文件并获取上传后的URL
            String iconUrl = dtsCategoryService.uploadDtsCategory(iconFile);
            // 上传图片文件并获取上传后的URL
            String picUrl = dtsCategoryService.uploadDtsCategory(picFile);

            // 创建新的分类实例
            DtsCategory dtsCategory = new DtsCategory();
            // 设置当前时间作为分类的添加时间
            Date date = new Date();
            // 设置分类的图标、图片、添加时间等基本信息
            dtsCategory.setIconUrl(iconUrl);
            dtsCategory.setPicUrl(picUrl);
            dtsCategory.setAddTime(date);
            dtsCategory.setName(name);
            dtsCategory.setKeywords(keywords);
            dtsCategory.setPid(pid);
            dtsCategory.setLevel(level);
            dtsCategory.setSortOrder(sortOrder);
            dtsCategory.setDesc(desc);

            // 插入新的分类到数据库
            dtsCategoryService.insertDtsCategory(dtsCategory);

            // 返回添加成功的提示信息
            return AjaxResult.success("Category added successfully.");
        } catch (Exception e) {
            // 捕获添加分类过程中可能出现的异常，返回错误信息
            return AjaxResult.error("Failed to add category: " + e.getMessage());
        }
    }

    /**
     * 修改分类信息并上传图标及图片。
     * 使用POST请求方法，对应URL路径为/updateDtsCategory。
     * 接收多个参数以更新分类的属性，包括上传的图标和图片文件，以及分类的其他信息。
     *
     * @param iconFile  分类图标文件，用于更新分类的图标URL。
     * @param picFile   分类图片文件，用于更新分类的图片URL。
     * @param id        分类ID，用于定位要更新的分类。
     * @param name      分类名称，可选，用于更新分类的名称。
     * @param keywords  分类关键词，可选，用于更新分类的关键词。
     * @param pid       父分类ID，可选，用于更新分类的父分类ID。
     * @param level     分类级别，可选，用于更新分类的级别。
     * @param sortOrder 分类排序顺序，可选，用于更新分类的排序顺序。
     * @param desc      分类描述，可选，用于更新分类的描述。
     * @return AjaxResult 对象，包含操作结果的信息，成功时包含成功消息，失败时包含错误消息。
     */
    @ApiOperation(value = "修改类目并上传图片")
    @PostMapping("/updateDtsCategory")
    public AjaxResult updateDtsCategory(
            @RequestParam("iconUrl") MultipartFile iconFile,
            @RequestParam("picUrl") MultipartFile picFile,
            @RequestParam("id") Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "keywords", required = false) String keywords,
            @RequestParam(value = "pid", required = false) Long pid,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "sortOrder", required = false) Integer sortOrder,
            @RequestParam(value = "desc", required = false) String desc
    ) {
        try {
            // 根据ID查询现有分类信息
            // 获取现有的分类信息
            DtsCategory existingCategory = dtsCategoryService.selectDtsCategoryid(id);
            // 如果分类不存在，返回错误信息
            if (existingCategory == null) {
                return AjaxResult.error("Category not found.");
            }

            // 如果传入了图标文件，上传图标并更新分类的图标URL
            // 上传图标文件并获取上传后的URL
            if (iconFile != null && !iconFile.isEmpty()) {
                String iconUrl = dtsCategoryService.uploadDtsCategory(iconFile);
                existingCategory.setIconUrl(iconUrl);
            }

            // 如果传入了图片文件，上传图片并更新分类的图片URL
            // 上传图片文件并获取上传后的URL
            if (picFile != null && !picFile.isEmpty()) {
                String picUrl = dtsCategoryService.uploadDtsCategory(picFile);
                existingCategory.setPicUrl(picUrl);
            }

            // 更新分类的其他可选信息，如果参数不为空
            // 更新分类的其他信息，只有当参数不为null时才更新
            if (name != null) existingCategory.setName(name);
            if (keywords != null) existingCategory.setKeywords(keywords);
            if (pid != null) existingCategory.setPid(pid);
            if (level != null) existingCategory.setLevel(level);
            if (sortOrder != null) existingCategory.setSortOrder(sortOrder);
            if (desc != null) existingCategory.setDesc(desc);

            // 更新分类的更新时间
            // 更新分类的时间戳
            existingCategory.setUpdateTime(new Date());

            // 更新数据库中的分类信息
            // 更新数据库中的分类信息
            dtsCategoryService.updateDtsCategory(existingCategory);

            // 返回成功消息，指示分类更新成功
            return AjaxResult.success("Category updated successfully.");
        } catch (Exception e) {
            // 捕获异常，返回错误消息，包含异常信息
            return AjaxResult.error("Failed to update category: " + e.getMessage());
        }
    }

    /**
     * 通过GET请求获取所有DtsCategory对象的列表。
     * <p>
     * 此方法不接受任何参数，返回所有DtsCategory实例的列表。这允许客户端应用程序
     * 在不指定特定条件的情况下检索所有类别信息，适用于例如显示所有类别的列表场景。
     *
     * @return ResponseEntity<List < DtsCategory>> 包含所有DtsCategory对象的响应体。响应体
     * 使用HTTP状态码200（OK）表示请求成功，并包含所有DtsCategory实例的列表。
     */
    @ApiOperation("查询全部")
    @GetMapping("/selectDtsCategoryall")
    public ResponseEntity<List<DtsCategory>> selectDtsCategoryall() {
        // 调用服务层方法查询所有DtsCategory对象
        List<DtsCategory> dtsCategorys = dtsCategoryService.selectDtsCategoryall();
        // 返回HTTP状态码200和查询结果列表
        return ResponseEntity.ok(dtsCategorys);
    }

    /**
     * 删除分类操作接口。
     * 通过POST请求调用，用于删除指定ID的分类。
     *
     * @param id 分类的唯一标识ID。
     * @return AjaxResult 对象，根据删除操作的成功与否，包含不同的消息。
     * @ApiOperation 注解用于说明该接口的操作含义。
     * @PostMapping 注解用于指定该方法处理的HTTP请求类型和路径。
     */
    @ApiOperation(value = "删除类目")
    @PostMapping("/deleteDtsCategory")
    public AjaxResult deleteDtsCategory(@RequestParam("id") Long id) {
        // 调用服务层方法，尝试删除指定ID的分类
        // 调用服务层方法删除指定ID的DtsCategory对象
        boolean result = dtsCategoryService.deleteDtsCategory(id);

        // 根据删除操作的结果，返回相应的AjaxResult对象
        // 根据删除结果返回不同的响应消息
        if (result) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败");
        }
    }

    @ApiOperation(value = "查询类目名称")
    @PostMapping("/selectDtsCategoryByNameLike")
    public ResponseEntity<List<DtsCategory>> selectDtsCategoryByNameLike(@RequestParam("name") String name) {
        // 调用服务层方法，根据名称模糊查询分类
        List<DtsCategory> dtsCategorys = dtsCategoryService.selectDtsCategoryByNameLike(name);
        // 返回HTTP状态码200和查询结果列表
        return ResponseEntity.ok(dtsCategorys);
    }

    /**
     * 分页查询文章类别信息。
     * 使用Swagger注解ApiOperation指明该方法的作用是分页查询类目。
     *
     * @param dtsCategory 查询条件，文章类别对象，可能包含分类ID等条件。
     * @param pageNum     请求的页码，默认为1。
     * @param pageSize    每页显示的记录数，默认为10。
     * @return 返回一个Map对象，包含数据总数和数据列表。
     * "data" 键对应的值是数据总数，"list" 键对应的值是数据列表。
     */
    @ApiOperation(value = "分页查询类目")
    @GetMapping("/selectArticleByPage")
    public Map<String, Object> selectArticleByPage(DtsCategory dtsCategory,
                                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();

        // 初始化分页插件，设置当前页码和每页记录数
        // 开始分页查询，这里使用了PageHelper插件来实现分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用服务层方法查询文章类别信息，根据dtsCategory中的条件进行查询
        // 调用服务层方法查询广告信息，根据dtsAd中的条件进行查询
        List<DtsCategory> list = dtsCategoryService.DtsRegionid(dtsCategory);

        // 使用PageInfo对查询结果进行包装，以获取分页信息
        // 使用PageInfo对查询结果进行包装，获取分页信息
        PageInfo<DtsCategory> info = new PageInfo<>(list);

        // 将数据总数和数据列表分别放入返回的Map中
        // 将查询结果的总条数和广告列表分别放入返回的Map中
        map.put("data", info.getTotal());
        map.put("list", list);

        return map;

    }


}
