package com.ccsu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccsu.entity.InBound;
import com.ccsu.mapper.InBoundMapper;
import com.ccsu.service.InBoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InBoundServiceImpl extends ServiceImpl<InBoundMapper,InBound> implements InBoundService {
    @Autowired
    InBoundMapper inBoundMapper;

    @Override
    public InBound checkInBound(String inboundid) {
        return inBoundMapper.checkInBound(inboundid);
    }

    /**
     * 新增入库信息
     * @param inBound
     * @return
     */
    @Override
    public int addinBound(InBound inBound) {
        return inBoundMapper.addinBound(inBound);
    }

    /**
     * 逻辑删除入库信息
     * @param id
     * @return
     */
    @Override
    public int delete(int id) {
        return inBoundMapper.deleteinBound(id);
    }

    /**
     * 修改入库基本信息
     * @param inBound
     */
    @Override
    public int updateinBound(InBound inBound) {
        return inBoundMapper.updateinBound(inBound);
    }

    /**
     * 模糊查询入库信息
     * @param inboundid
     * @param warehouse
     * @param transactor
     * @return
     */
    @Override
    public List<InBound> inBoundList(String inboundid, String warehouse, String transactor) {
        return inBoundMapper.inBoundList(inboundid,warehouse,transactor);
    }
}
