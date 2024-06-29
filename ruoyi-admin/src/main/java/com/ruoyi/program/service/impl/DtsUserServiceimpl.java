package com.ruoyi.program.service.impl;


import com.ruoyi.program.entity.DtsUser;
import com.ruoyi.program.mapper.DtsUserMapper;
import com.ruoyi.program.service.DtsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtsUserServiceimpl implements DtsUserService {

    @Autowired
    private DtsUserMapper dtsUserMapper;

    @Override
    public DtsUser queryById(Integer id) {
        return dtsUserMapper.queryById(id);
    }

    @Override
    public List<DtsUser> queryByPage(DtsUser dtsUser) {
        return dtsUserMapper.queryAllByLimit(dtsUser);
    }


    @Override
    public int insert(DtsUser dtsUser) {
        return dtsUserMapper.insert(dtsUser);
    }

    @Override
    public int update(DtsUser dtsUser) {
        return dtsUserMapper.update(dtsUser);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dtsUserMapper.deleteById(id) > 0;
    }
}
