package com.ccsu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccsu.entity.OutBound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OutBoundMapper extends BaseMapper<OutBound> {
    @Select("select * from out_bound where outboundid = #{outboundid}")
    OutBound selectByoutboundid(String outboundid);
}
