package com.ccsu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.OutBound;

import java.util.List;

public interface OutBoundService extends IService<OutBound> {
    OutBound checkOutBound(String outboundid);

    //模糊查询出库信息
    List<OutBound> outBoundList(String outboundid, String warehouse, String transactor);
}
