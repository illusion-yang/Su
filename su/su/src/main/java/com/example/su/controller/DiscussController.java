package com.example.su.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.su.common.Result;
import com.example.su.entity.Discuss;
import com.example.su.entity.User;
import com.example.su.service.DiscussService;
import com.example.su.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discuss")
public class DiscussController {
    @Resource
    private DiscussService discussService;
    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody Discuss discuss){
        discuss.setCreateTime(LocalDateTime.now());
        discuss.setCommentCounts(0);
        discuss.setScore(0);
        discuss.setStatus(1);
        discuss.setType(1);
        return Result.success("发布成功",discussService.save(discuss));
    }

    @PostMapping("/update")
    public Result save(@RequestBody Discuss discuss){
        return Result.success("保存成功",discussService.updateById(discuss));
    }

    @GetMapping("/files")
    public Result findAll(){
        return Result.success("查询成功",discussService.list());
    }

    @GetMapping("/all")
    public Object find(){
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Discuss> list = discussService.list(queryWrapper);
        List<Map<String,Object>> discuss = new ArrayList<>();
        if(list != null){
            for(Discuss dis : list){
                Map<String,Object> map = new HashMap<>();
                map.put("dis",dis);
                User user = userService.getById(dis.getUserId());
                map.put("user",user);
                discuss.add(map);
            }
        }
        return discuss;
    }

    @GetMapping("/hot")
    public Object findHot(){
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("score");
        List<Discuss> list = discussService.list(queryWrapper);
        List<Map<String,Object>> discuss = new ArrayList<>();
        if(list != null){
            for(Discuss dis : list){
                Map<String,Object> map = new HashMap<>();
                map.put("dis",dis);
                User user = userService.getById(dis.getUserId());
                map.put("user",user);
                discuss.add(map);
            }
        }
        return discuss;
    }

    @GetMapping("{id}")
    public Result findById(@PathVariable Integer id){
        Map<String,Object> map = new HashMap<>();
        Discuss discuss = discussService.getById(id);
        map.put("discuss",discuss);
        User user = userService.getById(discuss.getUserId());
        map.put("user",user);
        if(map != null){
            return Result.success("查询成功",map);
        }else{
            return Result.error(404,"没有查询到对应的信息");
        }
    }
}
