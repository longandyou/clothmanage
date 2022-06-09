package com.ccsu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccsu.entity.Product;
import com.ccsu.mapper.ProductMapper;
import com.ccsu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;

    /**
     * 根据货号查询信息
     * @param productid
     * @return
     */
    @Override
    public Product checkProduct(String productid) {
        return productMapper.checkProduct(productid);
    }

    /**
     * 新增货号信息
     * @param product
     * @return
     */
    @Override
    public int addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    /**
     * 根据货号逻辑删除
     * @param productid
     * @return
     */
    @Override
    public int delete(String productid) {
        return productMapper.deleteProduct(productid);
    }

    /**
     * 修改货号信息
     * @param product
     * @return
     */
    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public List<Product> productList(String productname, String productid) {
        return productMapper.productList(productname,productid);
    }
}
