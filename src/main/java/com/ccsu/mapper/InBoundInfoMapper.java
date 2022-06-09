package com.ccsu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccsu.entity.InBoundInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InBoundInfoMapper extends BaseMapper<InBoundInfo> {

    //模糊查询入库明细
    @Select("select * from in_bound_info where productname like concat('%', #{productname}, '%') " +
            "and productid like concat('%', #{productid}, '%')")
    List<InBoundInfo> inBoundInfoList(String productname, String productid);
}
