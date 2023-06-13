package com.example.warehouse.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.warehouse.entity.User;
import com.example.warehouse.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    UserMapper userMapper;

    public User getByUserName(String userName) {
        return userMapper.getByUserName(userName);
    }

    public List<User> getUserByType(String type){return userMapper.getUserByType(type);}
}

