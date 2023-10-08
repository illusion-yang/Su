package com.example.su.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("collect")
public class Collect {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "type")
    private Integer type;
    @TableField(value = "file_id")
    private String fileId;
}
