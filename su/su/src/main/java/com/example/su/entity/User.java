package com.example.su.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "user")
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "email")
    private String email;
    @TableField(value = "avatar")
    private String avatar;
    @TableField(value = "status")
    private Integer status;
    @TableField(value = "creatTime")
    private LocalDateTime creatTime;
}
