package com.example.su.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="file")
public class UserFile {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "user_id")
    private Integer userId;
    @TableField(value = "oldFileName")
    private String oldFileName;
    @TableField(value = "newFileName")
    private String newFileName;
    @TableField(value = "ext")
    private String ext;
    @TableField(value = "path")
    private String path;
    @TableField(value = "size")
    private String size;
    @TableField(value = "type")
    private String type;
    @TableField(value = "isImg")
    private String isImg;
    @TableField(value = "downcounts")
    private Integer downcounts;
}
