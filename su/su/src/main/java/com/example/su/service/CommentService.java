package com.example.su.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.su.entity.Comment;
import com.example.su.mapper.CommentMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {
}
