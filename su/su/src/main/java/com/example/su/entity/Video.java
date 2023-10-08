package com.example.su.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "video")
public class Video {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "fileName")
    private String fileName;
    @TableField(value = "path")
    private String path;
    @TableField(value = "size")
    private String size;
    @TableField(value = "type")
    private String type;
    @TableField(value = "downcounts")
    private Integer downcounts;
    @TableField(value = "createTime")
    private LocalDateTime createTime;
}
