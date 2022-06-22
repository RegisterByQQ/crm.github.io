package com.cqut.cqutcrm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqut.cqutcrm.dao.ContactDao;
import com.cqut.cqutcrm.entity.Contact;
import com.cqut.cqutcrm.entity.Customer;
import com.cqut.cqutcrm.service.ContactService;
import com.cqut.cqutcrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("con")
public class ContactController {
    @Autowired
    ContactService contactService;

    @RequestMapping("list")
    //查询某个客户的相关联系人
    public Map<String,Object> list(Integer page, Integer limit, Integer cusId){
        Map<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");

        //查询到一页的数据
        IPage<Contact> onepage = contactService.getOnePage(page, limit,cusId);
        map.put("count", onepage.getTotal());//表的总行数
        map.put("data", onepage.getRecords());//当前页的数据

        return map;
    }

    @RequestMapping("add")
    //添加联系人
    public Map<String,Object> add(Contact contact,Integer cusId){
        Map<String, Object> map = new HashMap<>();
        contact.setCusId(cusId);
        boolean flag = contactService.add(contact);
        map.put("msg",flag);

        return map;
    }

    @RequestMapping("update")
    //更新
    public Map<String,Object> update(Contact contact){
        HashMap<String, Object> map = new HashMap<>();
        boolean flag = contactService.update(contact);
        map.put("msg",flag);
        return map;
    }

    @RequestMapping("del")
    //删除
    public Map<String,Object> del(Integer conId){
        HashMap<String, Object> map = new HashMap<>();
        boolean flag = contactService.del(conId);
        map.put("msg",flag);
        return map;
    }
}
