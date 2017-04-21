package com.kang.mybatis.mapper;

import java.util.List;

import com.kang.mybatis.pojo.User;

public interface UserMapper {

    List<User> queryUserList();

}
