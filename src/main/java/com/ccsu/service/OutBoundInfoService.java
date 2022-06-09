package com.ccsu.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.Inventory;
import com.ccsu.entity.OutBoundInfo;

import java.util.List;

public interface OutBoundInfoService extends IService<OutBoundInfo> {
    //根据ID查询出库明细
    OutBoundInfo checkOutBoundInfo(int id);
    //新增出库明细的时候同时减少库存
    void saveoutInfoAndSubinventory(OutBoundInfo outBoundInfo, UpdateWrapper<Inventory> updateWrapper);

    //修改出库明细，同时修改库存量
    void updateAndInventory(OutBoundInfo outBoundInfo, int sum);

    //模糊查询出库明细
    List<OutBoundInfo> outBoundInfoList(String productname, String productid);
}
