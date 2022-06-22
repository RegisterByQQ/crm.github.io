package com.cqut.cqutcrm.controller;

import com.cqut.cqutcrm.entity.Customer;
import com.cqut.cqutcrm.entity.SaleChance;
import com.cqut.cqutcrm.entity.SalePlan;
import com.cqut.cqutcrm.entity.User;
import com.cqut.cqutcrm.service.CustomerService;
import com.cqut.cqutcrm.service.SaleChanceService;
import com.cqut.cqutcrm.service.SalePlanService;
import com.cqut.cqutcrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sp")
@CrossOrigin
public class SalPlanController {

    @Autowired
    SalePlanService salePlanService;
    @Autowired
    SaleChanceService saleChanceService;
    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;

//    //查询计划
//    @RequestMapping("list")
//    public HashMap<String, Object> query(String uname) {
//        //1.前端界面需要的数据格式
//        List<SalePlan> data = salePlanService.queryPlan(uname);
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", 0);
//        map.put("msg", "");
//        map.put("data",data);
//        map.put("count",20);
//        //查询到一页的数据
//        return (HashMap<String, Object>) map;
//    }

    // 2. 查询计划
    //  参数uname : 表示当前登录的用户名， 也就是销售机会中被分配人的名字
    @RequestMapping("list")
    public Map<String , Object> query(String uname , Integer page , Integer limit){
        int begin = (page-1)* limit;
        List<SalePlan> data = salePlanService.queryPlan(uname,begin ,limit);
        Map<String , Object> map = new HashMap<>();
        map.put("msg","");
        map.put("code" ,0);
        map.put("data" , data);
        map.put("count" , salePlanService.queryPlanCount(uname));
        return map;
    }

    //制定计划
    @RequestMapping("add")
    public Map<String,Object> add(SalePlan plan){
        boolean flag = salePlanService.addSp(plan);
        Map<String,Object> map = new HashMap<>();
        map.put("msg",flag);
        return map;
    }

    //执行计划
    @RequestMapping("exeplan")
    public Map<String,Object>  exeplan(SalePlan plan, SaleChance saleChance){
        //更新销售计划
        boolean flag = salePlanService.updateSp(plan);

        //判断机会状态为2,开发成功
        //机会状态为3，开发失败
        if(saleChance.getScStatus()==2){
            saleChanceService.updateSc(saleChance);
            //创建客户
            //查询出customer,如果查询不到，则创建新客户，插入数据，如果已存在客户，则不插入。
            Customer customer = new Customer();
            SaleChance saleChance1 = saleChanceService.queryByScId(saleChance.getScId());
            if(saleChance1!=null){
                Customer cusName = customerService.findByCusName(saleChance1.getScCusname());
                if(cusName==null){
                    User user = userService.findByUname(saleChance.getScGiveuser());
                    if(user!=null){
                        customer.setUserId(user.getuId());
                    }
                    customer.setCusName(saleChance1.getScCusname());
                    customerService.add(customer);
                }
            }
        }
        else if(saleChance.getScStatus()==3){
            saleChanceService.updateSc(saleChance);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("msg",flag);
        return map;
    }
}
