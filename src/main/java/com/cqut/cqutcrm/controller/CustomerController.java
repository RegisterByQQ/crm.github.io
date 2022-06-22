package com.cqut.cqutcrm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqut.cqutcrm.entity.Customer;
import com.cqut.cqutcrm.service.BusinessRecordService;
import com.cqut.cqutcrm.service.ContactService;
import com.cqut.cqutcrm.service.CustomerService;
import com.cqut.cqutcrm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("cus")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("list")//http://localhost:8080/cus/list
    public HashMap<String, Object> query(Integer page, Integer limit,Integer uId) {
        //1.前端界面需要的数据格式
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        //查询到一页的数据
        IPage<Customer> onepage = customerService.getOnePage(page, limit,uId);
        map.put("count", onepage.getTotal());//表的总行数
        map.put("data", onepage.getRecords());//当前页的数据


        return map;
    }

    //新增客户
    @RequestMapping("add")
    public HashMap<String,Object> add(Customer customer,Integer uId){
        Customer cusName = customerService.findByCusName(customer.getCusName());

        HashMap<String, Object> map = new HashMap<>();
        if(cusName==null){
            customer.setUserId(uId);
            boolean flag = customerService.add(customer);
            map.put("msg",flag);
        }else{
            map.put("msg",false);
        }
        return map;

    }

    @RequestMapping("update")
    //修改用户
    public Map<String,Object> updateCus(Customer customer){
        Map<String, Object> map = new HashMap<>();
        boolean flag = customerService.updateCus(customer);
        map.put("msg",flag);
        return map;
    }

    @Autowired
    OrdersService ordersService;
    @Autowired
    ContactService contactService;
    @Autowired
    BusinessRecordService recordService;

    @RequestMapping("del")
    //删除客户
    public Map<String,Object> del(String[] cusIds){
        Map<String, Object> map = new HashMap<>();
        List ids = new ArrayList();
        for (int i = 0; i < cusIds.length; i++) {
//            try{
//                contactService.delByCusId(Integer.valueOf(cusIds[i]));
//                recordService.delByCusId(Integer.valueOf(cusIds[i]));
//                ordersService.delByCusId(Integer.valueOf(cusIds[i]));
//            }catch (Exception e){
//                System.out.println(e.getMessage());
//            }

            ids.add(cusIds[i]);
        }


        boolean flag =  customerService.del(ids);
        map.put("msg", flag);

        return map;
    }


    //客户报表统计部分


}
