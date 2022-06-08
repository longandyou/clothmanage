package com.ccsu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccsu.common.R;
import com.ccsu.entity.Inventory;
import com.ccsu.entity.OutBoundInfo;
import com.ccsu.service.InventoryService;
import com.ccsu.service.OutBoundInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/outboundinfo")
public class OutBoundInfoController {
    @Autowired
    OutBoundInfoService outBoundInfoService;

    @Autowired
    InventoryService inventoryService;

    /**
     * 新增出库明细
     * @param outBoundInfo
     * @return
     */
    @PostMapping("/addoutboundinfo")
    public R<String> save(@RequestBody OutBoundInfo outBoundInfo){
        log.info("新增出库明细，出库明细信息为：{}",outBoundInfo.toString());
        //新增出库明细时，判断当前要出库的货号库存量是否充足
        LambdaQueryWrapper<Inventory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Inventory::getProductid,outBoundInfo.getProductid());
        Inventory inventory = inventoryService.getOne(queryWrapper);
        //如果相应的货号库存量不够的话，就返回相应信息
        if (inventory.getNumber() < outBoundInfo.getNumber()) {
            return R.error("出库失败，货号为："+outBoundInfo.getProductid()+"的库存量不足！");
        }
        //如果相应的货号库存量充足的话，就减少库存的数量
        int number = inventory.getNumber() - outBoundInfo.getNumber();
        UpdateWrapper<Inventory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("number",number).eq("productid",outBoundInfo.getProductid());
        //新增出库明细的时候同时减少库存
        outBoundInfoService.saveoutInfoAndSubinventory(outBoundInfo,updateWrapper);
        return R.success("新增出库明细成功！");
    }

    /**
     * 删除出库明细
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam int id){
        log.info("删除出库明细信息,id为：{}",id);
        UpdateWrapper<OutBoundInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("isdelete",0).eq("id",id);
        outBoundInfoService.update(null,updateWrapper);
        return R.success("出库明细删除成功");
    }

    /**
     * 修改出库明细
     * @param outBoundInfo
     * @return
     */
    @PutMapping("/update")
    public R<String> update(@RequestBody OutBoundInfo outBoundInfo){
        log.info("修改出库明细，出库明细信息：{}",outBoundInfo.toString());
        //获取当前修改的出库数量
        int number1 = outBoundInfo.getNumber();
        //获取修改之前的原出库数量
        LambdaQueryWrapper<OutBoundInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(OutBoundInfo::getId,outBoundInfo.getId());
        OutBoundInfo outBoundInfo1 = outBoundInfoService.getOne(queryWrapper1);
        int number2 = outBoundInfo1.getNumber();
        //获取相应货号的库存量
        LambdaQueryWrapper<Inventory> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Inventory::getProductid,outBoundInfo.getProductid());
        Inventory inventory = inventoryService.getOne(queryWrapper2);
        int number = inventory.getNumber();
        //判断修改出库明细之后，库存量是否充足
        int sum = number + number2 - number1;
        //如果相应的货号库存量不够的话，就返回相应信息
        if (sum < 0){
            return R.error("修改失败!货号："+outBoundInfo.getProductid()+"库存不足!");
        }
        //如果相应的货号库存量充足的话，就修改出库明细，并且修改库存的数量
        outBoundInfoService.updateAndInventory(outBoundInfo,sum);
        return R.success("出库明细修改成功");
    }

    /**
     * 查询出库明细
     * @param id
     * @return
     */
    @GetMapping("/check")
    public R<String> check(@RequestParam int id){
        log.info("查询出库明细,出库明细ID为：{}",id);
        OutBoundInfo outBoundInfo = outBoundInfoService.checkOutBoundInfo(id);
        System.out.println(outBoundInfo);
        return R.success("出库明细查询成功");
    }

    /**
     * 分页查询出库明细
     * @param page
     * @param pageSize
     * @param outboundid
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String outboundid){
        log.info("page = {},pageSize = {},productid = {}",page,pageSize,outboundid);

        Page pageInfo = new Page(page,pageSize);

        LambdaQueryWrapper<OutBoundInfo> queryWrapper = new LambdaQueryWrapper();

//        queryWrapper.like(StringUtils.isNotEmpty(outboundid),OutBoundInfo::getProductid,outboundid);

        queryWrapper.eq(OutBoundInfo::getIsdelete,1);
        queryWrapper.eq(OutBoundInfo::getOutboundid,outboundid);

        queryWrapper.orderByDesc(OutBoundInfo::getId);

        outBoundInfoService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
}
