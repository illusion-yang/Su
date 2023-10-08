package com.example.su.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.su.common.Result;
import com.example.su.entity.Collect;
import com.example.su.entity.Music;
import com.example.su.service.CollectService;
import com.example.su.service.MusicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private CollectService collectService;

    @Resource
    private MusicService musicService;

    @PostMapping("/add")
    public Result add(@RequestBody Collect collect){
        
        return Result.success("收藏成功",collectService.save(collect));
    }

    @GetMapping("/one/{id}")
    public Object getOne(@PathVariable Integer id){
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        List<Collect> list = collectService.list(queryWrapper);
        List<Map<String,Object>> collect = new ArrayList<>();
        if(collect != null){
            for(Collect col : list){
                Map<String,Object> map = new HashMap<>();
                map.put("col",col);
                Music music = musicService.getById(col.getFileId());
                map.put("music",music);
                collect.add(map);
            }
        }
        return collect;
    }
}
