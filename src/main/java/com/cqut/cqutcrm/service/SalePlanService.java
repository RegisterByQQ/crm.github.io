package com.cqut.cqutcrm.service;


import com.cqut.cqutcrm.dao.SalePlanDao;
import com.cqut.cqutcrm.entity.SalePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalePlanService {

    @Autowired//自动装配
    SalePlanDao salePlanDao;

//    //1查询所有
//    public List<SalePlan> queryPlan(String uname){
//        return salePlanDao.queryPlan(uname);
//    }


// 3. 查询出当前登录用户的销售计划（select）
    public List<SalePlan> queryPlan(String uname , Integer begin , Integer rows) {
        return salePlanDao.queryPlan(uname ,begin , rows);
    }

    // 4. 查询出总行数
    public Integer queryPlanCount(String uname ) {
        return salePlanDao.queryPlanCount(uname);
    }


    //3制定计划
    public boolean addSp(SalePlan sp){
        int row = salePlanDao.insert(sp);
        return row>0?true:false;
    }

    //4更新
    public boolean updateSp(SalePlan sp){
        int row = salePlanDao.updateById(sp);
        return row>0?true:false;
    }



}
