package com.ccsu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccsu.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {

    //库存增加
    @Update("update inventory set number = number + #{number} where productid = #{productid}")
    void addInventory(String productid, Integer number);

    //库存更新
    @Update("update inventory set number = #{sum} where productid = #{productid}")
    int updateInventory(String productid, int sum);

    //根据货号获得库存信息
    @Select("select * from inventory where productid = #{productid}")
    Inventory getInventory(String productid);
}
