package com.kang.mybatis.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kang.mybatis.bean.EasyUIResult;
import com.kang.mybatis.mapper.NewUserMapper;
import com.kang.mybatis.pojo.User;

@Service
public class NewUserService {

    @Autowired
    private NewUserMapper newUserMapper;

    public EasyUIResult queryUserList(Integer page, Integer rows) {
        // 设置分页参数
        PageHelper.startPage(page, rows);

        // 设置查询条件
        Example example = new Example(User.class);
        example.setOrderByClause("created DESC"); // 设置排序条件
        List<User> users = this.newUserMapper.selectByExample(example);

        PageInfo<User> pageInfo = new PageInfo<User>(users);
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    public User queryUserById(Long id) {
        return this.newUserMapper.selectByPrimaryKey(id);
    }

    public void saveUser(User user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        this.newUserMapper.insertSelective(user);
    }

    public void updateUser(User user) {
        this.newUserMapper.updateByPrimaryKeySelective(user);
    }

    public void deleteUserById(Long id) {
        this.newUserMapper.deleteByPrimaryKey(id);
    }

}
