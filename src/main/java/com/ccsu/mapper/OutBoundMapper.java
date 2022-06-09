package com.ccsu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccsu.entity.OutBound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OutBoundMapper extends BaseMapper<OutBound> {
    @Select("select * from out_bound where outboundid = #{outboundid}")
    OutBound selectByoutboundid(String outboundid);

    //模糊查询出库信息
    @Select("select * from out_bound where outboundid like concat('%', #{outboundid}, '%') " +
            "and warehouse like concat('%', #{warehouse}, '%') " +
            "and transactor like concat('%', #{transactor}, '%')")
    List<OutBound> outBoundList(String outboundid, String warehouse, String transactor);
}
