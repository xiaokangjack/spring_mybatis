package com.kang.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kang.mybatis.bean.EasyUIResult;
import com.kang.mybatis.mapper.UserMapper;
import com.kang.mybatis.pojo.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public EasyUIResult queryUserList(Integer page, Integer rows) {
        // 设置分页参数
        PageHelper.startPage(page, rows);
        List<User> users = this.userMapper.queryUserList();
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

}
