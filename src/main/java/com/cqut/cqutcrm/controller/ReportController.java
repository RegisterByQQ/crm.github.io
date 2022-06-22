package com.cqut.cqutcrm.controller;

import com.cqut.cqutcrm.entity.ReportEntity;
import com.cqut.cqutcrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("report")
@CrossOrigin
public class ReportController {

    @Autowired
    private CustomerService service;

    @RequestMapping("customerCountByLevel")
    public Map<String,Object> customerCountByLevel(){
        List<ReportEntity> list = service.getCustomerCountByLevel();
        ArrayList<String> category = new ArrayList<>();
        ArrayList<Integer> data = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();

        for (ReportEntity report:list ) {//把查询到的数据中的条目和数量储存到集合中
            data.add(report.getNum());
            switch (report.getItem()){
                case "A":
                    category.add("重点客户");
                    break;
                case "B":
                    category.add("普通客户");
                    break;
                case "C":
                    category.add("非优先客户");
                    break;
                default:
                    System.out.println("通过客户等级生成报表错误");
                    break;
            }
        }

        map.put("data",data);//map中的key和前端的界面保持一致
        map.put("category",category);
        return map;

    }

    @RequestMapping("customerCountByRegion")
    public Map<String,Object> customerCountByRegion(){
        List<ReportEntity> list = service.getCustomerCountByRegion();
        ArrayList<String> category = new ArrayList<>();
        ArrayList<Integer> data = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();

        for (ReportEntity report:list ) {//把查询到的数据中的条目和数量储存到集合中
            data.add(report.getNum());
            switch(report.getItem()){
                case "1":
                    category.add("东北");
                    break;
                case "2":
                    category.add("华北");
                    break;
                case "3":
                    category.add("西北");
                    break;
                case "4":
                    category.add("西南");
                    break;
                case "5":
                    category.add("华南");
                    break;
                case "6":
                    category.add("华中");
                    break;
                case "7":
                    category.add("华东");
                    break;
                default:
                    System.out.println("通过地址生成报表错误");
                    break;
            }
        }

        map.put("data",data);//map中的key和前端的界面保持一致
        map.put("category",category);
        return map;

    }
}
