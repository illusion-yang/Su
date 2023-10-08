package com.example.su.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.su.entity.Game;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameMapper extends BaseMapper<Game> {
}
