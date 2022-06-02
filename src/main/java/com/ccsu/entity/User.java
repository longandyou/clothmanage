package com.ccsu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体
 */
@Data
public class User implements Serializable {
    //用户ID
    private Integer id;//用户ID
    //用户姓名
    private String name;
    //用户账号
    private String account;
    //用户密码
    private String password;
    //权限标记位
    private String ispower;
    //删除标记位
    private Integer isdelete;
    //用户携带的token
    @TableField(exist = false)
    private String token;
}
