package com.cqut.cqutcrm.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqut.cqutcrm.dao.UserDao;
import com.cqut.cqutcrm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//注解功能：spring框架创建对象
public class UserService {

    @Autowired//自动装配
    UserDao userDao;

    //1定义一个方法，根据用户名和密码进行查询user
    public User findByUnameAndPassword(User user){
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("u_name",user.getuName());
        objectQueryWrapper.eq("u_password",user.getuPassword());
        List<User> users = userDao.selectList(objectQueryWrapper);
        if (users!=null && users.size()>0) {
            return users.get(0);
        }
        return null;
        }
    //2查询所有的客户经理的信息（rid=2是客户经理）
    public List<User> findByRid(){
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("r_id",2);
        return userDao.selectList(objectQueryWrapper);
    }

    //3根据用户的名字找到user
    public User findByUname(String uName){
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.like("u_name",uName);
        List<User> users = userDao.selectList(objectQueryWrapper);
        if(users!=null&& users.size()>0){
            return users.get(0);
        }else{
           return null;
        }
    }

}
