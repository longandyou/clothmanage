package com.ccsu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccsu.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    //查询货号
    @Select("select * from product where productid = #{productid}")
    Product checkProduct(String productid);
}
