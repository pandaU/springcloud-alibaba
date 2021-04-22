package com.pandau.cloud.controller;

import com.pandau.cloud.model.UserInfo;
import com.pandau.cloud.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/list")
    Object list(){
        return userService.getUserInfos();
    }

    @PostMapping("/add")
    Object add(@RequestBody UserInfo info){
        userService.addUser(info);
        return "ok";
    }
}
