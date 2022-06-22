package com.cqut.cqutcrm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqut.cqutcrm.entity.Orders;
import com.cqut.cqutcrm.entity.SaleChance;
import com.cqut.cqutcrm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("order")
@CrossOrigin
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    //分页查询
    @RequestMapping("list")//http://localhost:8080/sc/list
    public HashMap<String,Object> query(Integer page, Integer limit){
        //1.前端界面需要的数据格式
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        //查询到一页的数据
        IPage<Orders> onepage = ordersService.getOnePage(page,limit);
        map.put("count",onepage.getTotal());//表的总行数
        map.put("data",onepage.getRecords());//当前页的数据


        return map;
    }
}
