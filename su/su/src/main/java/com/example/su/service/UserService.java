package com.example.su.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.su.entity.User;
import com.example.su.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
