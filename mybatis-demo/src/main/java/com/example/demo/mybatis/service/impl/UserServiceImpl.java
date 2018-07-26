package com.example.demo.mybatis.service.impl;

import com.example.demo.mybatis.dao.UserMapper;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author acemma
 * @date 2018/1/30 15:44
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
