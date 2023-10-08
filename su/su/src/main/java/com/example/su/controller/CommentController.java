package com.example.su.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.su.common.Result;
import com.example.su.entity.Comment;
import com.example.su.entity.Discuss;
import com.example.su.entity.Music;
import com.example.su.entity.User;
import com.example.su.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;

    @Resource
    private DiscussService discussService;

    @PostMapping("/save")
    public Result save(@RequestBody Comment comment){
        comment.setCreateTime(LocalDateTime.now());
        Discuss discuss = discussService.getById(comment.getEntityId());
        discuss.setCommentCounts(discuss.getCommentCounts()+1);
        discussService.updateById(discuss);
        return Result.success("评论成功",commentService.save(comment));
    }

    @GetMapping("/one/{entityId}")
    public Object All(@PathVariable Integer entityId){
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entity_id",entityId);
        List<Comment> list = commentService.list(queryWrapper);
        List<Map<String,Object>> comment = new ArrayList<>();
        if(comment != null){
            for(Comment com : list){
                Map<String,Object> map = new HashMap<>();
                map.put("com",com);
                User user = userService.getById(com.getUserId());
                map.put("user",user);
                comment.add(map);
            }
        }
        return comment;
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success("删除成功",commentService.removeById(id));
    }

}
