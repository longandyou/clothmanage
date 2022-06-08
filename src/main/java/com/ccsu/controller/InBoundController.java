package com.ccsu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccsu.common.R;
import com.ccsu.entity.InBound;
import com.ccsu.entity.Product;
import com.ccsu.entity.User;
import com.ccsu.service.InBoundService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/inbound")
public class InBoundController {
    @Autowired
    InBoundService inBoundService;

    /**
     * 新增入库基本信息
     * @param inBound
     * @return
     */
    @PostMapping("/addInbound")
    public R<String> sava(@RequestBody InBound inBound){
        log.info("新增入库基本信息，基本入库信息为：{}",inBound.toString());
        inBoundService.save(inBound);
        return R.success("新增入库基本信息成功！");
    }

    /**
     * 删除入库基本信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam int id){
        log.info("删除入库基本信息,id为：{}",id);
        UpdateWrapper<InBound> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("isdelete",0).eq("id",id);
        inBoundService.update(null,updateWrapper);
        return R.success("入库基本信息删除成功");
    }

    /**
     * 修改入库基本信息
     * @param inBound
     * @return
     */
    @PutMapping("/update")
    public R<String> update(@RequestBody InBound inBound){
        log.info("修改入库基本信息，入库基本信息：{}",inBound.toString());
        inBoundService.updateById(inBound);
        return R.success("入库基本信息修改成功");
    }

    /**
     * 查询入库基本信息
     * @param inboundid
     * @return
     */
    @GetMapping("/check")
    public R<String> check(@RequestParam String inboundid){
        log.info("查询入库基本信息,入库单为：{}",inboundid);

        InBound inBound = inBoundService.checkInBound(inboundid);
        System.out.println(inBound);
        return R.success("入库基本信息查询成功");
    }

    /**
     * 分页查询入库信息
     * @param page
     * @param pageSize
     * @param inboundid
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String inboundid){
        log.info("page = {},pageSize = {},inboundid = {}",page,pageSize,inboundid);

        Page pageInfo = new Page(page,pageSize);

        LambdaQueryWrapper<InBound> queryWrapper = new LambdaQueryWrapper();

//        queryWrapper.like(StringUtils.isNotEmpty(inboundid),InBound::getInboundid,inboundid);
        queryWrapper.eq(InBound::getIsdelete,1);

        queryWrapper.orderByDesc(InBound::getInboundtime);

        inBoundService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
}
