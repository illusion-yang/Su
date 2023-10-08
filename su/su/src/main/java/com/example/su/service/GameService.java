package com.example.su.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.su.entity.Game;
import com.example.su.mapper.GameMapper;
import org.springframework.stereotype.Service;

@Service
public class GameService extends ServiceImpl<GameMapper, Game> {
}
