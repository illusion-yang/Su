package com.example.su.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "discuss")
public class Discuss {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "title")
    private String title;
    @TableField(value = "content")
    private String content;
    @TableField(value = "type")
    private Integer type;
    @TableField(value = "status")
    private Integer status;
    @TableField(value = "createTime")
    private LocalDateTime createTime;
    @TableField(value = "commentCounts")
    private Integer commentCounts;
    @TableField(value = "score")
    private Integer score;
}
