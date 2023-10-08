package com.example.su.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value="music")
public class Music {
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
