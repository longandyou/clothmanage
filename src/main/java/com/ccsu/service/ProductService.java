package com.ccsu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.Product;

import java.util.List;


public interface ProductService extends IService<Product> {
    //根据货号查询信息
    Product checkProduct(String productid);

    //新增货号信息
    int addProduct(Product product);

    //根据货号，逻辑删除
    int delete(String productid);

    //修改货号信息
    int updateProduct(Product product);

    //模糊查询货号
    List<Product> productList(String productname, String productid);
}
