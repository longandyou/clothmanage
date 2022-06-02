package com.ccsu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccsu.entity.User;

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
}
