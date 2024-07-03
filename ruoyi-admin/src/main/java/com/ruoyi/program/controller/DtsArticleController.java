package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.program.entity.DtsAddress;
import com.ruoyi.program.entity.DtsArticle;
import com.ruoyi.program.service.DtsArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "文章信息表")
@RequestMapping("/article")
public class DtsArticleController {

    @Autowired
    private DtsArticleService dtsArticleService;


    /**
     * 通过POST请求插入文章信息。
     * <p>
     * 该接口用于接收来自前端的Article对象，并将其插入到数据库中。
     * 接口使用@ApiOperation注解来说明其功能和使用方法。
     *
     * @param dtsArticle 待插入的文章数据，包含文章的各种信息。
     * @return 返回一个ResponseEntity对象，其中包含操作结果的信息。
     * 如果文章插入成功，返回状态码200和消息"文章插入成功"。
     * 如果文章插入失败，返回状态码500和相应的错误消息。
     * 如果出现异常，同样返回状态码500和异常消息。
     * @PostMapping("/insertArticle")
     */
    @ApiOperation(value = "插入文章信息", notes = "插入一篇新的文章信息")
    @PostMapping("/insertArticle")
    public ResponseEntity<String> insertArticle(@RequestBody DtsArticle dtsArticle) {
        try {
            // 设置文章的添加时间
            dtsArticle.setAddTime(new Date());
            // 调用服务层方法插入文章，返回插入结果
            int result = dtsArticleService.insertArticle(dtsArticle);
            // 根据插入结果返回相应的响应
            if (result > 0) {
                return ResponseEntity.ok("文章插入成功");
            } else {
                return ResponseEntity.status(500).body("文章插入失败");
            }
        } catch (Exception e) {
            // 捕获异常，返回服务器错误信息
            return ResponseEntity.status(500).body("服务器内部错误: " + e.getMessage());
        }
    }

    @ApiOperation(value = "更新文章信息", notes = "更新一篇文章的信息")
    @PostMapping("/updateArticle")
    public ResponseEntity<String> updateArticle(@RequestBody DtsArticle dtsArticle) {
        // 设置文章的更新时间
        dtsArticle.setUpdateTime(new Date());

        // 调用服务层方法更新文章，返回更新结果
        int result = dtsArticleService.updateArticle(dtsArticle);

        // 根据更新结果返回相应的响应
        if (result > 0) {
            return ResponseEntity.ok("文章更新成功");
        } else {
            return ResponseEntity.status(500).body("文章更新失败");
        }
    }

    @ApiOperation(value = "查询文章列表", notes = "查询所有的文章信息")
    @GetMapping("/selectArticleList")
    public ResponseEntity<List<DtsArticle>> selectArticleList() {
        // 调用服务层方法查询文章列表，返回查询结果
        List<DtsArticle> articles = dtsArticleService.selectArticleList();

        // 根据查询结果返回相应的响应
        if (articles != null && !articles.isEmpty()) {
            return ResponseEntity.ok(articles);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @ApiOperation(value = "模糊查询文章", notes = "根据标题模糊查询文章")
    @GetMapping("/selectArticleListFuzzyQuery")
    public ResponseEntity<List<DtsArticle>> selectArticleListFuzzyQuery(String title) {
        // 调用服务层方法模糊查询文章，返回查询结果
        List<DtsArticle> articles = dtsArticleService.selectArticleListFuzzyQuery(title);

        // 根据查询结果返回相应的响应
        if (articles != null && !articles.isEmpty()) {
            return ResponseEntity.ok(articles);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @ApiOperation(value = "删除文章", notes = "根据文章ID删除文章")
    @PostMapping("/deleteArticle")
    public ResponseEntity<String> deleteArticle(@RequestBody DtsArticle dtsArticle) {
        // 调用服务层方法删除文章，返回删除结果
        boolean result = dtsArticleService.deleteArticle(dtsArticle.getId());

        // 根据删除结果返回相应的响应
        if (result) {
            return ResponseEntity.ok("文章删除成功");
        } else {
            return ResponseEntity.status(500).body("文章删除失败");
        }
    }

    @ApiOperation(value = "分页查询文章")
    @GetMapping("/selectArticleByPage")
    public Map<String, Object> selectArticleByPage(DtsArticle dtsArticle,
                                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回的数据Map
        HashMap<String, Object> map = new HashMap<>();
        // 开始分页查询，这里使用了PageHelper插件来实现分页
        PageHelper.startPage(pageNum, pageSize);
        // 调用服务层方法查询广告信息，根据dtsAd中的条件进行查询
        List<DtsArticle> list = dtsArticleService.selectArticleList();
        // 使用PageInfo对查询结果进行包装，获取分页信息
        PageInfo<DtsArticle> info = new PageInfo<>(list);
        // 将查询结果的总条数和广告列表分别放入返回的Map中
        map.put("data", info.getTotal());
        map.put("list", list);

        return map;

    }
}

