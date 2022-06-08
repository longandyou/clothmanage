package com.ccsu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.Inventory;

public interface InventoryService extends IService<Inventory> {
    //对库存中已有的货号库存量进行增加操作
    void addInventory(String productid, Integer number);

    //对库存中已有的货号库存量进行修改操作
    void updateInventory(String productid, int sum);
}
