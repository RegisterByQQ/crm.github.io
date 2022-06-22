package com.cqut.cqutcrm.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqut.cqutcrm.entity.SaleChance;
import com.cqut.cqutcrm.service.SaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController//注解功能：1.表示这是Controller类型。 2.设置返回值为json格式
@RequestMapping("sc")//http://localhost:8080/sc
@CrossOrigin//允许跨域访问
public class SaleChanceController {

    @Autowired
    SaleChanceService saleChanceService;
    @RequestMapping("list")//http://localhost:8080/sc/list
    public HashMap<String,Object> query(Integer page,Integer limit){
        //1.前端界面需要的数据格式
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        //查询到一页的数据
        IPage<SaleChance> onepage = saleChanceService.getOnePage(page,limit);
        map.put("count",onepage.getTotal());//表的总行数
        map.put("data",onepage.getRecords());//当前页的数据


        return map;
    }

    @RequestMapping("add")
    //2.添加销售机会信息
    public Map<String,Object> add(SaleChance sc){
        Map<String, Object> objectObjectHashMap = new HashMap<>();
        boolean flag = saleChanceService.addSc(sc);
        objectObjectHashMap.put("msg",flag);
        return objectObjectHashMap;
    }

    @RequestMapping("update")
    //3.更新销售机会信息
    public Map<String,Object> update(SaleChance sc){
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        boolean flag = saleChanceService.updateSc(sc);
        objectObjectHashMap.put("msg",flag);
        return objectObjectHashMap;
    }

    @RequestMapping("del")
    //4.删除某行销售机会信息
    public Map<String,Object> del(Integer scId){
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        boolean flag = saleChanceService.deleteSc(scId);
        objectObjectHashMap.put("msg",flag);
        return objectObjectHashMap;
    }

    @RequestMapping("give")
    //5.更新销售机会信息
    public Map<String,Object> giveSc(SaleChance sc){
        sc.setScStatus(1);
        sc.setScGivetime(new Date());
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        boolean flag = saleChanceService.updateSc(sc);
        objectObjectHashMap.put("msg",flag);
        return objectObjectHashMap;
    }

    //找到分配给当前用户的销售机会
    @RequestMapping("mylist")//http://localhost:8080/sc/mylist
    public HashMap<String,Object> queryMy(Integer page,Integer limit,String uname){
        //1.前端界面需要的数据格式
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        //查询到一页的数据
        IPage<SaleChance> onepage = saleChanceService.getOnePageByGivename(page,limit,uname);
        map.put("count",onepage.getTotal());//表的总行数
        map.put("data",onepage.getRecords());//当前页的数据


        return map;
    }

//    //用于设置机会，开发成功或开发失败
//    public


}

























