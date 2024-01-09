package com.example.work_demo1.controller;

import com.alibaba.fastjson.JSON;
import com.example.work_demo1.pojo.SystemUser;

import com.example.work_demo1.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SystemUserController {
    @Autowired
    SystemUserService systemUserService;

    @RequestMapping("/systemUser/get")
    public List<SystemUser> getAllUsers() {
        return systemUserService.getAllUsers();
    }

    @RequestMapping("/systemUser/add")
    public String addUser(@RequestBody SystemUser user) {
        System.out.println(user);
        SystemUser addUser = systemUserService.createUser(user);
        return JSON.toJSONString(addUser);
    }
}
