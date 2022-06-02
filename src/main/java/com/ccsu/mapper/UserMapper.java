package com.ccsu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccsu.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    //根据账号和密码查找用户
    @Select("select * from user where account=#{account} AND password=#{password} AND isdelete!='0'")
//    @Results(id = "userMap",value = {
//            @Result(id = true,column = "id",property = "id"),
//            @Result(column = "name",property = "name"),
//            @Result(column = "account",property = "account"),
//            @Result(column = "user_email",property = "email"),
//            @Result(column = "user_role",property = "role"),
//            @Result(column = "user_status",property = "status")
//    })
    User login(User user);
    //新增用户
    @Insert("insert into user(name,account,password) value (#{name},#{account},#{password})")
    Integer addUser(User user);

    //逻辑删除用户
    @Update("update user set isdelete = 0 where id = #{id}")
    int deleteByUserId(int id);

    //更新用户信息
    @Update("update user set name = #{name},account = #{account},password = #{password} where id = #{id}")
    int updateUser(User user);

    //管理员授权
    @Update("update user set ispower = #{flag} where id = #{id}")
    int topower(int id, int flag);

    //查询用户
    @Select("select * from user where name = #{name}")
    User checkUser(String name);
}
