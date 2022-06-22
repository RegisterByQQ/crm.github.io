package com.cqut.cqutcrm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.cqutcrm.dao.OrdersDao;
import com.cqut.cqutcrm.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersDao ordersDao;

    public IPage<Orders> getOnePage(Integer page, Integer rows){
        IPage<Orders> objectPage = new Page<>(page,rows);//页码，一页的行数
        objectPage = ordersDao.selectPage(objectPage,null);
        return objectPage;
    }

    //删除用户信息
    public boolean del(Integer id){
        int row = ordersDao.deleteById(id);
        return row>0?true:false;
    }

    //根据cusid删除用户
    public void delByCusId(Integer cusId){
        QueryWrapper<Object> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("cus_id",cusId);
        ordersDao.delByCusId(cusId);

    }
}
