package com.ccsu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccsu.entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    //查询货号
    @Select("select * from product where productid = #{productid}")
    Product checkProduct(String productid);

    //新增货号
    @Insert("insert into product(productid,productname,color,size) " +
            "values (#{productid},#{productname},#{color},#{size})")
    int addProduct(Product product);

    //逻辑删除货号
    @Update("update product set isdelete = 0 where productid = #{productid}")
    int deleteProduct(String productid);

    //修改货号信息
    @Update("update product set productname = #{productname},color = #{color}," +
            "size = #{size} where productid = #{productid}")
    int updateProduct(Product product);

    //模糊查询货号
    @Select("select * from product where productname like concat('%', #{productname}, '%') " +
            "and productid like concat('%', #{productid}, '%')")
    List<Product> productList(String productname, String productid);
}
