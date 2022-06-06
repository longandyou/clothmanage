package com.ccsu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.Product;


public interface ProductService extends IService<Product> {
    Product checkProduct(String productid);
}
