package com.example.su.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.omg.CORBA.INTERNAL;

import java.time.LocalDateTime;

@Data
@TableName(value="comment")
public class Comment {
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    @TableField(value="user_id")
    private Integer userId;
    @TableField(value="entity_id")
    private Integer entityId;
    @TableField(value="pid")
    private Integer pId;
    @TableField(value="target_id")
    private Integer targetId;
    @TableField(value="count")
    private String count;
    @TableField(value="status")
    private Integer status;
    @TableField(value="createTime")
    private LocalDateTime createTime;
}
