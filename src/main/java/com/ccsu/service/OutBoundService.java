package com.ccsu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.OutBound;

public interface OutBoundService extends IService<OutBound> {
    OutBound checkOutBound(String outboundid);
}
