package com.ccsu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccsu.entity.InBound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InBoundMapper extends BaseMapper<InBound> {
    @Select("select * from in_bound where inboundid = #{inboundid}")
    InBound checkInBound(String inboundid);
}
