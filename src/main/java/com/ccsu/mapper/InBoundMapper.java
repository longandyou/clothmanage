package com.ccsu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccsu.entity.InBound;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface InBoundMapper extends BaseMapper<InBound> {
    //查询入库信息
    @Select("select * from in_bound where inboundid = #{inboundid}")
    InBound checkInBound(String inboundid);

    //新增入库信息
    @Insert("insert into in_bound(inboundid,inboundtime,warehouse,transactor,origin,remark)" +
            "values (#{inboundid},#{inboundtime},#{warehouse},#{transactor},#{origin},#{remark})")
    int addinBound(InBound inBound);

    //逻辑删除入库信息
    @Update("update in_bound set isdelete = 0 where id = #{id}")
    int deleteinBound(int id);

    //修改入库基本信息
    @Update("update in_bound set warehouse = #{warehouse},transactor = #{transactor}," +
            "origin = #{origin},inboundtime = #{inboundtime},remark = #{remark} where inboundid = #{inboundid}")
    int updateinBound(InBound inBound);

    //模糊查询
    @Select("select * from in_bound where inboundid like concat('%', #{inboundid}, '%') " +
            "and warehouse like concat('%', #{warehouse}, '%') " +
            "and transactor like concat('%', #{transactor}, '%')")
    List<InBound> inBoundList(String inboundid, String warehouse, String transactor);
}
