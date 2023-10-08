package com.example.su.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.su.entity.Music;
import com.example.su.mapper.MusicMapper;
import org.springframework.stereotype.Service;

@Service
public class MusicService extends ServiceImpl<MusicMapper, Music> {
}
