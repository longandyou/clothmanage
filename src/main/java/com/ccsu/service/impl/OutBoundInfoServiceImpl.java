package com.ccsu.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccsu.entity.Inventory;
import com.ccsu.entity.OutBoundInfo;
import com.ccsu.mapper.OutBoundInfoMapper;
import com.ccsu.service.InventoryService;
import com.ccsu.service.OutBoundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OutBoundInfoServiceImpl extends ServiceImpl<OutBoundInfoMapper, OutBoundInfo> implements OutBoundInfoService {

    @Autowired
    OutBoundInfoMapper outBoundInfoMapper;

    @Autowired
    InventoryService inventoryService;
    /**
     * 根据ID查询出库明细
     * @param id
     * @return
     */
    @Override
    public OutBoundInfo checkOutBoundInfo(int id) {
        return outBoundInfoMapper.selectById(id);
    }

    /**
     * 新增出库明细的时候同时减少库存
     * @param outBoundInfo
     * @param updateWrapper
     */
    @Override
    //添加事务管理，要么同时成功，要么同时失败，保证了事务的一致性
    @Transactional(rollbackFor = Exception.class)
    public void saveoutInfoAndSubinventory(OutBoundInfo outBoundInfo, UpdateWrapper<Inventory> updateWrapper) {
        this.save(outBoundInfo);
        inventoryService.update(null,updateWrapper);
    }

    /**
     * 修改出库明细，同时修改库存量
     * @param outBoundInfo
     * @param sum
     */
    @Override
    //添加事务管理，要么同时成功，要么同时失败，保证了事务的一致性
    @Transactional(rollbackFor = Exception.class)
    public void updateAndInventory(OutBoundInfo outBoundInfo, int sum) {
        this.updateById(outBoundInfo);
        inventoryService.updateInventory(outBoundInfo.getProductid(),sum);
    }
}
