package com.example.su.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.su.common.Result;
import com.example.su.entity.User;
import com.example.su.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${su.avatar}")
    private String avatarpath;

    @Resource
    private UserService userService;
    @GetMapping("/all")
    public Result findAll(){
        return Result.success("查询成功",userService.list());
    }
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        User user = userService.getById(id);

        if(user != null){
            return Result.success("查询成功",user);
        }else{
            return Result.error(404,"没有查询到对应的员工信息");
        }
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success("删除成功",userService.removeById(id));
    }
    @PostMapping("/update")
    public Result save(@RequestBody User user){
        Integer id = user.getId();
        String username = user.getUsername();
        QueryWrapper<User> q1 = new QueryWrapper<>();
        QueryWrapper<User> q2 = new QueryWrapper<>();
        q1.eq("username",username);
        q2.eq("username",username).eq("id",id);
        User u1 = userService.getOne(q1);
        User u2 = userService.getOne(q2);
        if(u2 != null){
            return Result.success("保存成功",userService.updateById(user));
        }else if(u1 != null){
            return Result.error(405,"用户名存在");
        }else{
            return Result.success("保存成功",userService.updateById(user));
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        //校验
        String username = user.getUsername();
        String password = user.getPassword();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);

        User u = userService.getOne(wrapper);

        if(u==null){
            return Result.error(404,"用户名或密码错误");
        }else if(u.getStatus() == 0){
            return Result.error(405,"账号已被禁用");
        }else{
            return Result.success("登录成功",userService.getOne(wrapper));
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);

        user.setCreatTime(LocalDateTime.now());
        user.setAvatar("/avatar/user.jpg");

        if(userService.getOne(queryWrapper)!=null){
            return Result.error(405,"用户名已存在,请重新注册");
        }else{
            return Result.success("注册成功",userService.save(user));
        }
    }

    @PostMapping("/img")
    public Result saveAvatar(@PathParam("file") MultipartFile file, @PathParam("id") Integer id){

        String FileName = System.currentTimeMillis()+file.getOriginalFilename();



        String imgPath = "/avatar/"+FileName;

        File dir = new File(avatarpath);
        //判断当前目录是否存在
        if(!dir.exists()){
            dir.mkdirs();
        }

        try{
            //将临时文件转存到指定位置
            file.transferTo(new File(avatarpath+FileName));

        }catch (IOException e){
            e.printStackTrace();
        }

        User user = userService.getById(id);
        user.setAvatar(imgPath);

        return Result.success("上传成功",userService.updateById(user));
    }
}
