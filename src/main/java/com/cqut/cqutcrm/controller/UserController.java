package com.cqut.cqutcrm.controller;

import com.cqut.cqutcrm.entity.User;
import com.cqut.cqutcrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController//设置类的类型是Controller并且请求的返回值设置为json对象
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("login")//http://localhost:8080/login
    //1.登陆的请求
    public Map<String, Object> login(User user){
        //请求参数的名字和实体类User的属性名一致，框架就会把请求参数封装到User对象
        User user1 = userService.findByUnameAndPassword(user);
        Map<String, Object> map = new HashMap<>();
        if (user1 == null){
            map.put("msg",false);//错误
        }else{
            map.put("msg",true);//正确
            user1.setuPassword("");
            map.put("user",user1);
        }
        return map;
    }

    @RequestMapping("managers")
    //2.查询所有的客户经理
    public List<User> findManager(){
        List<User> managers = userService.findByRid();
        return managers;
    }
}
