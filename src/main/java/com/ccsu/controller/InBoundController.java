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

import java.time.LocalDateTime;
import java.util.List;

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
//        inBoundService.save(inBound);
        int msg = inBoundService.addinBound(inBound);
        if (msg == 0){
            return R.error("入库基本信息新增失败");
        }
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
//        UpdateWrapper<InBound> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.set("isdelete",0).eq("id",id);
//        inBoundService.update(null,updateWrapper);
        int msg = inBoundService.delete(id);
        if (msg == 0){
            return R.error("入库基本信息删除失败");
        }
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
//        inBoundService.updateById(inBound);
        int msg = inBoundService.updateinBound(inBound);
        if (msg == 0){
            return R.error("入库基本信息修改失败");
        }
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
        if (inBound == null){
            return R.error("入库基本信息查询失败");
        }
        System.out.println(inBound);
        return R.success("入库基本信息查询成功");
    }

    /**
     * 模糊查询入库信息
     * @param inboundid
     * @param warehouse
     * @param transactor
     * @return
     */
    @GetMapping("/checkInbound")
    public R<List<InBound>> checkInbound(@RequestParam String inboundid,
                                         @RequestParam String warehouse,
                                         @RequestParam String transactor){
        log.info("模糊查询");
        List<InBound> list = inBoundService.inBoundList(inboundid,warehouse,transactor);
        if (list.size() == 0){
            return R.error("无相关信息");
        }
        log.info(String.valueOf(list));
        return R.success(list);
    }

    /**
     * 分页查询入库信息
     * @param page
     * @param pageSize
     * @param inboundid
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String inboundid,String warehouse,String transactor){
        log.info("page = {},pageSize = {},inboundid = {},warehouse = {},transactor = {}"
                ,page,pageSize,inboundid,warehouse,transactor);

        Page pageInfo = new Page(page,pageSize);

        LambdaQueryWrapper<InBound> queryWrapper = new LambdaQueryWrapper();
        //模糊查询
        queryWrapper.like(StringUtils.isNotEmpty(inboundid),InBound::getInboundid,inboundid);
        queryWrapper.like(StringUtils.isNotEmpty(warehouse),InBound::getWarehouse,warehouse);
        queryWrapper.like(StringUtils.isNotEmpty(transactor),InBound::getTransactor,transactor);

        queryWrapper.eq(InBound::getIsdelete,1);

        queryWrapper.orderByDesc(InBound::getInboundtime);

        inBoundService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
}
