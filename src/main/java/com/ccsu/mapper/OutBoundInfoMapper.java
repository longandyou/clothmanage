package com.ccsu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccsu.entity.OutBoundInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OutBoundInfoMapper extends BaseMapper<OutBoundInfo> {
    //模糊查询出库明细
    @Select("select * from out_bound_info where productname like concat('%', #{productname}, '%') " +
            "and productid like concat('%', #{productid}, '%')")
    List<OutBoundInfo> outBoundInfoList(String productname, String productid);
}
