package com.ccsu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccsu.common.R;
import com.ccsu.entity.*;
import com.ccsu.service.InBoundInfoService;
import com.ccsu.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/inboundinfo")
public class InBoundInfoController {
    @Autowired
    InBoundInfoService inBoundInfoService;

    @Autowired(required = false)
    InventoryService inventoryService;

    /**
     * 新增入库明细
     * @param inBoundInfo
     * @return
     */
    @PostMapping("/addinboundinfo")
    public R<String> save(@RequestBody InBoundInfo inBoundInfo){
        //定义一个标记位，判断是否对库存进行新增操作，还是对已有的库存进行增加
        int flag = 1;
        log.info("新增入库明细，入库明细信息为：{}",inBoundInfo.toString());
//        LambdaQueryWrapper<Inventory> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Inventory::getProductid,inBoundInfo.getProductid());
//        Inventory inventory = inventoryService.getOne(queryWrapper);
        //新增入库明细时，判断货号是否已存在于库存中，如果存在就增加相应的库存
        Inventory inventory = inventoryService.getInventory(inBoundInfo.getProductid());
        if (inventory != null) {
            flag = 0;
            inventoryService.addInventory(inventory.getProductid(),inBoundInfo.getNumber());
        }
        //新增入库明细的时候，同时判断是否对库存进行新增，flag=1就新增，flag=0,就不进行新增操作
        int msg = inBoundInfoService.saveInBoundInfoAndInventory(inBoundInfo,flag);
        if (msg == 1){
            return R.success("新增入库明细成功");
        }
        return R.error("新增入库明细失败！");
    }

    /**
     * 删除入库明细
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam int id){
        log.info("删除入库明细信息,id为：{}",id);
        UpdateWrapper<InBoundInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("isdelete",0).eq("id",id);
        boolean msg = inBoundInfoService.update(null,updateWrapper);
        if (!msg){
            return R.error("入库明细删除失败");
        }
        return R.success("入库明细删除成功");
    }

    /**
     * 修改入库明细
     * @param inBoundInfo
     * @return
     */
    @PutMapping("/update")
    public R<String> update(@RequestBody InBoundInfo inBoundInfo){
        log.info("修改入库明细，入库明细信息：{}",inBoundInfo.toString());
        //获取当前修改的数量
        int number1 = inBoundInfo.getNumber();
        //构造InBoundInfo对象，获取修改之前的数量
        LambdaQueryWrapper<InBoundInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(InBoundInfo::getId,inBoundInfo.getId());
        InBoundInfo inBoundInfo1 = inBoundInfoService.getOne(queryWrapper1);
        int number2 = inBoundInfo1.getNumber();
        //构造Inventory对象，获取库存的数量
        LambdaQueryWrapper<Inventory> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Inventory::getProductid,inBoundInfo.getProductid());
        Inventory inventory = inventoryService.getOne(queryWrapper2);
        int number3 = inventory.getNumber();

        //判断对入库明细进行修改的话，库存中的相应货号数量是否小于0
        int sum = number3 - number2 + number1;
        System.out.println(sum);
        if (sum < 0){
            return R.error("修改失败，库存量不足！");
        }
        //修改入库明细的时候同时修改库存量
        int msg = inBoundInfoService.updateAndInventory(inBoundInfo,sum);
        if (msg == 0){
            return R.error("入库明细修改失败");
        }
        return R.success("入库明细修改成功");
    }

    /**
     * 查询入库明细
     * @param id
     * @return
     */
    @GetMapping("/check")
    public R<String> check(@RequestParam int id){
        log.info("查询入库明细,入库明细ID为：{}",id);
        InBoundInfo inBoundInfo = inBoundInfoService.checkInBoundInfo(id);
        System.out.println(inBoundInfo);
        if (inBoundInfo == null){
            return R.success("入库明细查询失败");
        }
        return R.success("入库明细查询成功");
    }

    /**
     * 模糊查询入库明细
     * @param productname
     * @param productid
     * @return
     */
    @GetMapping("/checkinBoundInfo")
    public R<List<InBoundInfo>> checkinBoundInfo(@RequestParam String productname, @RequestParam String productid){
        log.info("模糊查询");
        List<InBoundInfo> list = inBoundInfoService.inBoundInfoList(productname,productid);
        if (list.size() == 0){
            return R.error("无相关信息");
        }
        log.info(String.valueOf(list));
        return R.success(list);
    }

    /**
     * 分页查询入库明细
     * @param page
     * @param pageSize
     * @param inboundid
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize,String productname,String productid, String inboundid){
        log.info("page = {},pageSize = {},inboundid = {},productname = {},productid = {}"
                ,page,pageSize,inboundid,productname,productid);

        Page pageInfo = new Page(page,pageSize);

        LambdaQueryWrapper<InBoundInfo> queryWrapper = new LambdaQueryWrapper();

        //模糊查询
        queryWrapper.like(StringUtils.isNotEmpty(productname),InBoundInfo::getProductname,productname);
        queryWrapper.like(StringUtils.isNotEmpty(productid),InBoundInfo::getProductid,productid);

        queryWrapper.eq(InBoundInfo::getIsdelete,1);
        queryWrapper.eq(InBoundInfo::getInboundid,inboundid);

        queryWrapper.orderByDesc(InBoundInfo::getId);

        inBoundInfoService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
}
