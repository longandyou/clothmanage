package com.ccsu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.InBound;

import java.util.List;

public interface InBoundService extends IService<InBound> {

    //查询入库信息
    InBound checkInBound(String inboundid);

    //新增入库信息
    int addinBound(InBound inBound);

    //逻辑删除入库信息
    int delete(int id);

    //修改入库基本信息
    int updateinBound(InBound inBound);

    //模糊查询入库信息
    List<InBound> inBoundList(String inboundid, String warehouse, String transactor);
}
