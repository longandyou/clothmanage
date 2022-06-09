package com.ccsu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OutBound implements Serializable {
    //主键ID
    private Integer id;

    //出库单号
    private String outboundid;

    //所入仓库
    private String warehouse;

    //出库时间
//    @TableField(fill = FieldFill.INSERT)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime outboundtime;

    //经办人
    private String transactor;

    //出库去向
    private String whereabouts;

    //备注
    private String remark;

    //删除标记位
    private Integer isdelete;
}
