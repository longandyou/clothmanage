package com.ccsu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccsu.entity.Product;
import com.ccsu.mapper.ProductMapper;
import com.ccsu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Override
    public Product checkProduct(String productid) {
        return productMapper.checkProduct(productid);
    }
}
