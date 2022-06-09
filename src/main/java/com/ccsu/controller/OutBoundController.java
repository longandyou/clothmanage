package com.ccsu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccsu.common.R;
import com.ccsu.entity.InBound;
import com.ccsu.entity.OutBound;
import com.ccsu.service.OutBoundService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/outbound")
public class OutBoundController {

    @Autowired
    OutBoundService outBoundService;

    /**
     * 新增出库基本信息
     * @param outBound
     * @return
     */
    @PostMapping("/addOutbound")
    public R<String> sava(@RequestBody OutBound outBound){
        log.info("新增出库基本信息，基本出库信息为：{}",outBound.toString());
        outBoundService.save(outBound);
        return R.success("新增出库基本信息成功！");
    }

    /**
     * 删除出库基本信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam int id){
        log.info("删除出库基本信息,id为：{}",id);
        UpdateWrapper<OutBound> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("isdelete",0).eq("id",id);
        outBoundService.update(null,updateWrapper);
        return R.success("出库基本信息删除成功");
    }

    /**
     * 修改出库基本信息
     * @param outBound
     * @return
     */
    @PutMapping("/update")
    public R<String> update(@RequestBody OutBound outBound){
        log.info("修改出库基本信息，出库基本信息：{}",outBound.toString());
        outBoundService.updateById(outBound);
        return R.success("出库基本信息修改成功");
    }

    /**
     * 查询出库基本信息
     * @param outboundid
     * @return
     */
    @GetMapping("/check")
    public R<String> check(@RequestParam String outboundid){
        log.info("查询出库基本信息,出库单号为：{}",outboundid);

        OutBound outBound = outBoundService.checkOutBound(outboundid);
        System.out.println(outBound);
        return R.success("出库基本信息查询成功");
    }

    /**
     * 模糊查询出库信息
     * @param outboundid
     * @param warehouse
     * @param transactor
     * @return
     */
    @GetMapping("/checkOutbound")
    public R<List<OutBound>> checkInbound(@RequestParam String outboundid,
                                         @RequestParam String warehouse,
                                         @RequestParam String transactor){
        log.info("模糊查询");
        List<OutBound> list = outBoundService.outBoundList(outboundid,warehouse,transactor);
        if (list.size() == 0){
            return R.error("无相关信息");
        }
        log.info(String.valueOf(list));
        return R.success(list);
    }

    /**
     * 分页查询出库基本信息
     * @param page
     * @param pageSize
     * @param outboundid
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String outboundid,String warehouse,String transactor){
        log.info("page = {},pageSize = {},outboundid = {},warehouse = {},transactor = {}"
                ,page,pageSize,outboundid,warehouse,transactor);

        Page pageInfo = new Page(page,pageSize);

        LambdaQueryWrapper<OutBound> queryWrapper = new LambdaQueryWrapper();
        //模糊查询
        queryWrapper.like(StringUtils.isNotEmpty(outboundid),OutBound::getOutboundid,outboundid);
        queryWrapper.like(StringUtils.isNotEmpty(warehouse),OutBound::getWarehouse,warehouse);
        queryWrapper.like(StringUtils.isNotEmpty(transactor),OutBound::getTransactor,transactor);

        queryWrapper.eq(OutBound::getIsdelete,1);

        queryWrapper.orderByDesc(OutBound::getOutboundtime);

        outBoundService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
}
