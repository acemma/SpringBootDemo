package com.example.demo.mybatis.controller;

import com.example.demo.common.share.JsonMessage;
import com.example.demo.common.share.JsonMessageDefine;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.service.I18nMessageLocaleSource;
import com.example.demo.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    protected I18nMessageLocaleSource i18nMessageLocaleSource;

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

    @RequestMapping(value = "/userList", method = {RequestMethod.POST})
    @ApiOperation(value = "新增用户")
    @ApiImplicitParam(name = "user",value = "用户对象",dataType = "User",paramType = "body")
    public JsonMessage<Integer> add(@RequestBody User user){
        JsonMessage<Integer> jsonMessage = null;
        if (11 != user.getPhone().length()){
            jsonMessage = new JsonMessage<>(JsonMessageDefine.PHONE_NUMBER_ERROR);
            jsonMessage.setMessage(JsonMessageDefine.getMessage(JsonMessageDefine.PHONE_NUMBER_ERROR, i18nMessageLocaleSource));
            return jsonMessage;
        }
        return jsonMessage;
    }

}
