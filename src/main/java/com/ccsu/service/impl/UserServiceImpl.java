package com.ccsu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccsu.entity.User;
import com.ccsu.mapper.UserMapper;
import com.ccsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    //注入userMapper
    @Autowired(required = false)
    private UserMapper userMapper;
    /**
     * 用户登录
     */
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    /**
     * 用户新增
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    /**
     * 用户删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(int id) {
        return userMapper.deleteByUserId(id);
    }

    /**
     * 用户更新
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 管理员授权给用户
     * @param id
     * @param flag
     * @return
     */
    @Override
    public int topower(int id, int flag) {
        return userMapper.topower(id,flag);
    }

    /**
     * 查询用户
     * @param name
     * @return
     */
    @Override
    public User checkUser(String name) {
        return userMapper.checkUser(name);
    }
}
