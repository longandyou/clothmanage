package com.ccsu.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    //主键ID
    private Integer id;

    //品名
    private String productname;

    //色号
    private String color;

    //尺寸
    private String size;

    //货号
    private String productid;

    //逻辑删除位
    private Integer isdelete;
}
