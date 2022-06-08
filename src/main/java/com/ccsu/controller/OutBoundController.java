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
     * 分页查询出库基本信息
     * @param page
     * @param pageSize
     * @param outboundid
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String outboundid){
        log.info("page = {},pageSize = {},outboundid = {}",page,pageSize,outboundid);

        Page pageInfo = new Page(page,pageSize);

        LambdaQueryWrapper<OutBound> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.isNotEmpty(outboundid),OutBound::getOutboundid,outboundid);
        queryWrapper.eq(OutBound::getIsdelete,1);

        queryWrapper.orderByDesc(OutBound::getOutboundtime);

        outBoundService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
}
