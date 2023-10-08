package com.example.su.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.su.entity.Model;
import com.example.su.mapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ModelService extends ServiceImpl<ModelMapper, Model> {
}
