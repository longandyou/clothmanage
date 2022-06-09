package com.ccsu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    //用户登录方法
    User login(User user);

    //用户新增方法
    Integer addUser(User user);

    //用户删除方法
    int deleteById(int id);

    //用户更新方法
    int updateUser(User user);

    //管理员给用户授权方法
    int topower(int id, int flag);

    //根据姓名查询用户
    User checkUser(String name);

    //获取当前登录用户的信息
    User getInfo(String token);

    //修改密码
    Integer updatePassword(Object object);

    //模糊查询用户信息
    List<User> userList(String name, String account);
}
