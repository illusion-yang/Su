package com.example.su.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.su.entity.UserFile;
import com.example.su.mapper.UserFileMapper;
import org.springframework.stereotype.Service;

@Service
public class UserFileService extends ServiceImpl<UserFileMapper, UserFile> {
}
