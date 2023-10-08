package com.example.su.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.su.common.Result;
import com.example.su.entity.Admin;
import com.example.su.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        //校验
        String username = admin.getUsername();
        String password = admin.getPassword();
        Integer id = admin.getId();
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);
 
        if(adminService.getOne(wrapper)==null){
            return Result.error(404,"用户名或密码错误");
        }else{
            return Result.success("登录成功",adminService.getOne(wrapper));
        }
    }
}
