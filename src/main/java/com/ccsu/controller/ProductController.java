package com.ccsu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ccsu.common.R;
import com.ccsu.entity.Product;
import com.ccsu.entity.User;
import com.ccsu.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

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
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam int id){
        log.info("删除货号,id为：{}",id);
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("isdelete",0).eq("id",id);
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
}
