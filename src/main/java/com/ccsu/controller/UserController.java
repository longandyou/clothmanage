package com.ccsu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccsu.common.R;
import com.ccsu.entity.User;
import com.ccsu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired(required = false)
    private UserService userService;

    /**
     * 用户登录操作
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request,@RequestBody User user){
//        //1、获取传递过来的用户密码
//        String  password = user.getPassword();
//        //2、根据页面提交的用户账号account查询数据库
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getAccount,user.getAccount());
//        User u = userService.getOne(queryWrapper);
//        //3、如果没有查询到用户则返回登录失败结果
//        if(u == null){
//            return R.error("登录失败，用户不存在");
//        }
//        //4、密码比对，如果不一致则返回登录失败结果
//        if (!u.getPassword().equals(password)){
//            return R.error("登录失败,密码错误");
//        }
//        //5、查看员工状态，看是否被删除
//        if (u.getIsdelete() == 0){
//            return R.error("登录失败,用户已被删除");
//        }
//        //6、登录成功，将员工id存入Session并返回登录成功结果
////        request.getSession().setAttribute("user",u.getId());
//        String msg = RandomStringUtils.randomAlphabetic(17);
//        u.setToken(msg);
//        System.out.println(user);
//        return R.success(u);
        User u=userService.login(user);
        if(u == null){
            return R.error("登录失败");
        }
        u.setToken(u.getAccount());
        return R.success(u);
    }

    /**
     * 管理员授权
     * @param id
     * @param ispower
     * @return
     */
    @PutMapping("/topower")
    public R<String> topower(@RequestParam int id,@RequestParam int ispower){
        log.info("给用户授权");
        String message1 = "授权成功!";
        String message2 = "已收回权限!";
        String message = ispower == 0 ? message1 : message2;
        int flag = ispower == 0 ? 1 : 0;
//        userService.updateById(user);
        int msg = userService.topower(id,flag);
        if(msg != 1){
            return R.error("授权失败");
        }
        return R.success(message);
    }
    /**
     * 用户新增操作
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public R<String> save(@RequestBody User user){
        log.info("新增用户，用户信息为：{}",user.toString());
//        userService.save(user);
//        return R.success("新增用户成功！");
        Integer msg=userService.addUser(user);
        if(msg != 1){
            return R.error("登录失败");
        }
        return R.success("新增用户成功");
    }

    /**
     * 用户删除操作
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam int id){
        log.info("删除用户,id为：{}",id);
//        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.set("isdelete",0).eq("id",id);
//        userService.update(null,updateWrapper);
        int msg=userService.deleteById(id);
        if(msg != 1){
            return R.error("删除失败");
        }
        return R.success("用户删除成功");
    }

    /**
     * 用户更新操作
     * @param user
     * @return
     */
    @PutMapping("/update")
    public R<String> update(@RequestBody User user){
        log.info("修改用户信息");
//        userService.updateById(user);
        int msg = userService.updateUser(user);
        if(msg != 1){
            return R.error("修改失败");
        }
        return R.success("用户信息修改成功");
    }

    /**
     * 根据查询用户信息
     * @param name
     * @return
     */
    @GetMapping("/check")
    public R<String> check(@RequestParam String name){
        log.info("查询用户信息");
        User u = userService.checkUser(name);
        System.out.println(u);
        return R.success("用户查询成功");
    }

    /**
     * 模糊查询用户
     * @param name
     * @param account
     * @return
     */
    @GetMapping("/checkUser")
    public R<List<User>> checkUser(@RequestParam String name,@RequestParam String account){
        log.info("根据姓名和账号模糊查询用户信息");
        List<User> list = userService.userList(name,account);
        if (list.size() == 0){
            return R.error("无相关信息");
        }
        log.info(String.valueOf(list));
        return R.success(list);
    }

    /**
     * 获取当前登录用户信息
     * @param token
     * @return
     */
    @GetMapping("/info")
    public R<User> getInfo(@RequestParam String token){
        User user = userService.getInfo(token);
        if(user == null){
            return R.error("失败");
        }
        user.setToken(user.getAccount());
        return R.success(user);
    }

    /**
     * 修改用户密码
     * @param object
     * @return
     */
    @PostMapping("/updatePassword")
    public R<String> updatePassword(@RequestBody Object object){
        System.out.println(object);
        Integer user = userService.updatePassword(object);
        if(user==null){
            return  R.error("失败");
        }
        return R.success("user");
    }

    /**
     * 分页查询用户信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name,String account){
        log.info("page = {},pageSize = {},name = {},account = {}",page,pageSize,name,account);
        //分页构造器
        Page pageInfo = new Page(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        //模糊查询
        queryWrapper.like(StringUtils.isNotEmpty(name),User::getName,name);
        queryWrapper.like(StringUtils.isNotEmpty(account),User::getAccount,account);
        //匹配没被删除的条件
        queryWrapper.eq(User::getIsdelete,1);
        //按照ID排序
        queryWrapper.orderByDesc(User::getId);

        userService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
}
