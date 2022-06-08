package com.ccsu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccsu.entity.OutBound;
import com.ccsu.mapper.OutBoundMapper;
import com.ccsu.service.OutBoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutBoundServiceImpl extends ServiceImpl<OutBoundMapper, OutBound> implements OutBoundService {

    @Autowired
    OutBoundMapper outBoundMapper;

    @Override
    public OutBound checkOutBound(String outboundid) {
        return outBoundMapper.selectByoutboundid(outboundid);
    }
}
