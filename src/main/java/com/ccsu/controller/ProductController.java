package com.ccsu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccsu.common.R;
import com.ccsu.entity.Inventory;
import com.ccsu.entity.Product;
import com.ccsu.entity.User;
import com.ccsu.service.InventoryService;
import com.ccsu.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    InventoryService inventoryService;

    /**
     * 新增货号
     * @param product
     * @return
     */
    @PostMapping("/addProduct")
    public R<String> sava(@RequestBody Product product){
        log.info("新增货号，货号信息为：{}",product.toString());
        productService.save(product);
        return R.success("新增货号成功！");
    }

    /**
     * 货号删除操作
     * @param productid
     * @return
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam String productid){
        log.info("删除货号,货号为：{}",productid);
        //判断要删除的货号，是否在库存中还有数量
        LambdaQueryWrapper<Inventory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Inventory::getProductid,productid);
        Inventory inventory = inventoryService.getOne(queryWrapper);
        if (inventory.getNumber() > 0){
            return R.error("货号删除失败，该货号在库存中的数量不为0");
        }

        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("isdelete",0).eq("productid",productid);
        productService.update(null,updateWrapper);
        return R.success("货号删除成功");
    }

    /**
     * 货号更新操作
     * @param product
     * @return
     */
    @PutMapping("/update")
    public R<String> update(@RequestBody Product product){
        log.info("修改货号信息，货号信息：{}",product.toString());
        productService.updateById(product);
        return R.success("货号信息修改成功");
    }


    /**
     * 货号查询
     * @param productid
     * @return
     */
    @GetMapping("/check")
    public R<String> check(@RequestParam String productid){
        log.info("查询货号信息,货号为：{}",productid);

        Product product = productService.checkProduct(productid);
        System.out.println(product);
        return R.success("货号查询成功");
    }

    /**
     * 分页查询货号信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {},pageSize = {},name = {}",page,pageSize,name);

        Page pageInfo = new Page(page,pageSize);

        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.isNotEmpty(name),Product::getProductname,name);
        queryWrapper.eq(Product::getIsdelete,1);

        queryWrapper.orderByDesc(Product::getId);

        productService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 返回库存展示
     * @return
     */
    @GetMapping("/list")
    public R<List<Inventory>> list(){
        //条件构造器
        LambdaQueryWrapper<Inventory> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(Inventory::getIsdelete,1);

        List<Inventory> list = inventoryService.list(queryWrapper);
        return R.success(list);
    }
}
