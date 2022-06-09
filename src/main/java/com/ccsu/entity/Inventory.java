package com.ccsu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Inventory implements Serializable {
    //主键ID
    private Integer id;

    //货号
    private String productid;

    //货号
    private Integer number;

    //逻辑删除位
    private Integer isdelete;
}
