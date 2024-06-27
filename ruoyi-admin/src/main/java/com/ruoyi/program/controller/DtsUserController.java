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

    @ApiOperation(value = "分页查询用户")
    @GetMapping("/selectDtsUser")
    public Map<String, Object> selectDtsUser(DtsUser dtsUser,
                                             @RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "10") Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<DtsUser> list = dtsUserMapper.queryAllByLimit(dtsUser);
        PageInfo<DtsUser> info = new PageInfo<>(list);
        map.put("data", info.getTotal());
        map.put("list", list);
        return map;
    }
}
