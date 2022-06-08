package com.ccsu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.InBound;

public interface InBoundService extends IService<InBound> {

    InBound checkInBound(String inboundid);
}
