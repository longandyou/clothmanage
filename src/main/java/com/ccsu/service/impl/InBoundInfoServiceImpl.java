package com.ccsu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccsu.entity.InBoundInfo;
import com.ccsu.entity.Inventory;
import com.ccsu.mapper.InBoundInfoMapper;
import com.ccsu.service.InBoundInfoService;
import com.ccsu.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InBoundInfoServiceImpl extends ServiceImpl<InBoundInfoMapper, InBoundInfo> implements InBoundInfoService {
    @Autowired
    InBoundInfoMapper inBoundInfoMapper;

    @Autowired(required = false)
    InventoryService inventoryService;

    /**
     * 新增入库明细的时候同时新增库存
     * @param inBoundInfo
     * @return
     */
    @Override
    //添加事务管理，要么同时成功，要么同时失败，保证了事务的一致性
    @Transactional(rollbackFor=Exception.class)
    public int saveInBoundInfoAndInventory(InBoundInfo inBoundInfo,int flag) {
        this.save(inBoundInfo);
        if (flag == 1){
            Inventory inventory = new Inventory();
            inventory.setProductid(inBoundInfo.getProductid());
            inventory.setNumber(inBoundInfo.getNumber());
            log.info(String.valueOf(inventory));
            if (inventoryService.save(inventory)){
                return 1;
            }
            else return 0;
        }
        return 1;
    }

    /**
     * 修改入库明细的时候同时修改库存量
     * @param inBoundInfo
     * @param sum
     * @return
     */
    @Override
    //添加事务管理，要么同时成功，要么同时失败，保证了事务的一致性
    @Transactional(rollbackFor=Exception.class)
    public int updateAndInventory(InBoundInfo inBoundInfo, int sum) {
        this.updateById(inBoundInfo);
        return inventoryService.updateInventory(inBoundInfo.getProductid(),sum);
    }

    /**
     * 模糊查询入库明细
     * @param productname
     * @param productid
     * @return
     */
    @Override
    public List<InBoundInfo> inBoundInfoList(String productname, String productid) {
        return inBoundInfoMapper.inBoundInfoList(productname,productid);
    }


    /**
     * 根据ID查询入库明细
     * @param id
     * @return
     */
    @Override
    public InBoundInfo checkInBoundInfo(int id) {
        return inBoundInfoMapper.selectById(id);
    }
}
