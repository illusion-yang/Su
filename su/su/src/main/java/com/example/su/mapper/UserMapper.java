package com.example.su.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.su.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
