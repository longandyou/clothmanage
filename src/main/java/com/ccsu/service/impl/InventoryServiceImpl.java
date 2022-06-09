package com.ccsu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccsu.entity.Inventory;
import com.ccsu.mapper.InventoryMapper;
import com.ccsu.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    @Autowired
    InventoryMapper inventoryMapper;

    /**
     * 对库存中已存在的货号进行数量的增加
     * @param productid
     * @param number
     */
    @Override
    public void addInventory(String productid, Integer number) {
        inventoryMapper.addInventory(productid,number);
    }

    /**
     * 根据货号对相应的库存进行修改
     * @param productid
     * @param sum
     * @return
     */
    @Override
    public int updateInventory(String productid, int sum) {
        return inventoryMapper.updateInventory(productid,sum);
    }

    /**
     * 根据productid获得相应库存
     * @param productid
     * @return
     */
    @Override
    public Inventory getInventory(String productid) {
        return inventoryMapper.getInventory(productid);
    }

}
