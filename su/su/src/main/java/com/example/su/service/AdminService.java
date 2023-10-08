package com.example.su.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.su.entity.Admin;
import com.example.su.mapper.AdminMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends ServiceImpl<AdminMapper, Admin> {
}
