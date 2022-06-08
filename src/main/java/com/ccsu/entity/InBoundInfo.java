package com.ccsu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
//@TableName(value = "inboundinfo")
public class InBoundInfo implements Serializable {
    //主键ID
    private Integer id;

    //货号
    private String productid;

    //入库单号
    private String inboundid;

    //品名
    private String productname;

    //色号
    private String color;

    //尺码
    private String size;

    //数量
    private Integer number;

    //逻辑删除位
    private Integer isdelete;

}
