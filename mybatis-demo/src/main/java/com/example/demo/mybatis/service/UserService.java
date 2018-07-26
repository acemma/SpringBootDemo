package com.example.demo.mybatis.service;

import com.example.demo.mybatis.entity.User;

import java.util.List;

/**
 * @author acemma
 * @date 2018/1/30 15:43
 * @Description
 */
public interface UserService {

    List<User> findAll();
}
