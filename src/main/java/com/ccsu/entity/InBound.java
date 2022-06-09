package com.ccsu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
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
