package com.ccsu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.InBoundInfo;

import java.util.List;

public interface InBoundInfoService extends IService<InBoundInfo> {

    //查询入库明细
    InBoundInfo checkInBoundInfo(int id);

    //新增入库明细的时候同时新增库存
    int saveInBoundInfoAndInventory(InBoundInfo inBoundInfo,int flag);
    //修改入库明细的时候同时修改库存量
    int updateAndInventory(InBoundInfo inBoundInfo, int sum);

    //模糊查询入库明细
    List<InBoundInfo> inBoundInfoList(String productname, String productid);
}
