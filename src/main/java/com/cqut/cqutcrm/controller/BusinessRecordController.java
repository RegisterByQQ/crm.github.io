package com.cqut.cqutcrm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqut.cqutcrm.entity.BusinessRecord;
import com.cqut.cqutcrm.entity.Contact;
import com.cqut.cqutcrm.service.BusinessRecordService;
import com.cqut.cqutcrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@RestController
@RequestMapping("record")
@CrossOrigin
public class BusinessRecordController {
    @Autowired
    BusinessRecordService recordService;

    @RequestMapping("list")
    //查询某个客户的相关交往记录
    public Map<String,Object> list(Integer page, Integer limit, Integer cusId){
        Map<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");

        //查询到一页的数据
        IPage<BusinessRecord> onepage = recordService.getOnePage(page, limit,cusId);
        map.put("count", onepage.getTotal());//表的总行数
        map.put("data", onepage.getRecords());//当前页的数据

        return map;
    }

    @Autowired
    UserService userService;
    @RequestMapping("add")
    //添加联系人
    public Map<String,Object> add(BusinessRecord businessRecord,Integer cusId){
        Map<String, Object> map = new HashMap<>();
//        TimeZone time = TimeZone.getTimeZone("Etc/GMT-8");  //转换为中国时区
//
//        TimeZone.setDefault(time);
        businessRecord.setRecordDate(new Date());
        businessRecord.setCusId(cusId);
        boolean flag = recordService.add(businessRecord);
        map.put("msg",flag);

        return map;
    }

    @RequestMapping("update")
    //更新
    public Map<String,Object> update(BusinessRecord businessRecord){
        HashMap<String, Object> map = new HashMap<>();
//        TimeZone time = TimeZone.getTimeZone("Etc/GMT-8");  //转换为中国时区
//
//        TimeZone.setDefault(time);
        //businessRecord.setRecordDate(new Date());
        boolean flag = recordService.update(businessRecord);
        map.put("msg",flag);
        return map;
    }

    @RequestMapping("del")
    //删除
    public Map<String,Object> del(Integer recordId){
        HashMap<String, Object> map = new HashMap<>();
        boolean flag = recordService.del(recordId);
        map.put("msg",flag);
        return map;
    }
}
