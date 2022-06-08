package com.ccsu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data

public class InBound implements Serializable {
    //主键ID
    private Integer id;

    //入库单号
    private String inboundid;

    //所入仓库
    private String warehouse;

    //入库时间
//    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime inboundtime;

    //经办人
    private String transactor;

    //入库来源
    private String origin;

    //备注
    private String remark;

    //删除标记位
    private Integer isdelete;

}
