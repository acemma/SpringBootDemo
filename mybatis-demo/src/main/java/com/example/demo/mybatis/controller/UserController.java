package com.example.demo.mybatis.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.share.JsonMessage;
import com.example.demo.share.JsonMessageDefine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
@RestController
@RequestMapping("/api/user")
@Api(description = "用户模块API")
public class UserController {

    private static final Logger logger  = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/userList", method = {RequestMethod.GET})
    @ApiOperation(value = "查询用户列表")
    public JsonMessage<List<User>> userList() {
        JsonMessage<List<User>> jsonMessage = null;

        // 执行操作
        try {
            List<User> list = userService.findAll();
            jsonMessage = new JsonMessage<>(JsonMessageDefine.SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("userList exception," + e.getMessage());
            jsonMessage = new JsonMessage<>(JsonMessageDefine.OP_FAILED);
            jsonMessage.setMessage(e.getMessage());
        }
        return jsonMessage;
    }

}
