package com.ccsu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccsu.entity.InBound;
import com.ccsu.mapper.InBoundMapper;
import com.ccsu.service.InBoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InBoundServiceImpl extends ServiceImpl<InBoundMapper,InBound> implements InBoundService {
    @Autowired
    InBoundMapper inBoundMapper;

    @Override
    public InBound checkInBound(String inboundid) {
        return inBoundMapper.checkInBound(inboundid);
    }
}
