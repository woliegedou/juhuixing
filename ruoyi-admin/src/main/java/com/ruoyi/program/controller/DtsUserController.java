package com.ruoyi.program.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.program.entity.DtsUser;
import com.ruoyi.program.mapper.DtsUserMapper;
import com.ruoyi.program.service.DtsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "用户管理")
@RequestMapping("/userStatement")
public class DtsUserController {

    private static final Logger logger = LoggerFactory.getLogger(DtsUserController.class);

    @Autowired
    private DtsUserMapper dtsUserMapper;


    @ApiOperation(value = "添加用户")
    @PostMapping("/insertDtsUser")
    public ResponseEntity<String> insertDtsUser(@RequestBody DtsUser record) {
        int insert = dtsUserMapper.insert(record);
        if (insert > 0) {
            logger.info("成功添加用户: {}", record.getUsername());
            return new ResponseEntity<>("用户添加成功", HttpStatus.CREATED);
        } else {
            logger.warn("用户添加失败: {}", record.getUsername());
            return new ResponseEntity<>("用户添加失败", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 分页查询用户信息。
     * 通过此接口可以实现用户信息的分页查询，支持根据用户条件进行筛选。
     * 使用PageHelper进行分页处理，返回分页后的用户列表及总条数。
     *
     * @param dtsUser  查询条件对象，包含用户信息的过滤条件。
     * @param pageNum  当前页码，默认为1。
     * @param pageSize 每页显示的记录数，默认为10。
     * @return 返回一个Map对象，包含用户列表和总记录数。
     * "data" 键存储总记录数，"list" 键存储用户列表。
     */
    @ApiOperation(value = "分页查询用户")
    @GetMapping("/selectDtsUser")
    public Map<String, Object> selectDtsUser(DtsUser dtsUser,
                                             @RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "10") Integer pageSize) {
        // 初始化返回数据的Map对象
        HashMap<String, Object> map = new HashMap<>();

        // 开始分页查询，设置当前页码和每页记录数
        PageHelper.startPage(pageNum, pageSize);

        // 根据查询条件查询所有用户信息，限制返回的结果集大小
        List<DtsUser> list = dtsUserMapper.queryAllByLimit(dtsUser);

        // 创建PageInfo对象，用于包装分页信息
        PageInfo<DtsUser> info = new PageInfo<>(list);

        // 将总记录数和用户列表分别放入map中
        map.put("data", info.getTotal());
        map.put("list", list);

        // 返回包含分页信息的Map对象
        return map;
    }

}
