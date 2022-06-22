package com.cqut.cqutcrm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.cqutcrm.dao.BusinessRecordDao;
import com.cqut.cqutcrm.dao.ContactDao;
import com.cqut.cqutcrm.dao.CustomerDao;
import com.cqut.cqutcrm.dao.OrdersDao;
import com.cqut.cqutcrm.entity.Customer;
import com.cqut.cqutcrm.entity.ReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    //查询指定页的数据
    public IPage<Customer> getOnePage(Integer page, Integer rows,Integer uId){
        IPage<Customer> objectPage = new Page<>(page,rows);//页码，一页的行数

        QueryWrapper<Customer> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("user_id",uId);
        objectPage = customerDao.selectPage(objectPage,objectQueryWrapper);
        return objectPage;
    }
    public List<Customer> queryAll(){
        List<Customer> list = customerDao.selectList(null);
        return list;
    }


    //添加客户
    public boolean add(Customer customer){
        int row = customerDao.insert(customer);
        return row>0?true:false;
    }

    //根据客户的名字查询客户
    public Customer findByCusName(String cusName) {
        QueryWrapper<Customer> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.like("cus_name", cusName);
        List<Customer> customers = customerDao.selectList(objectQueryWrapper);
        if (customers != null&&customers.size()>0) {
            return customers.get(0);
        } else {
            return null;
        }
    }

    //更新用户信息
    public boolean updateCus(Customer customer){
        int row = customerDao.updateById(customer);
        return row>0?true:false;
    }

    @Autowired
    ContactDao contactDao;
    @Autowired
    BusinessRecordDao recordDao;
    @Autowired
    OrdersDao ordersDao;
    //删除用户信息
    public boolean del(List ids){
        for (int i = 0; i < ids.size(); i++) {
            contactDao.delByCusId(Integer.parseInt(String.valueOf(ids.get(i))));
            recordDao.delByCusId(Integer.parseInt(String.valueOf(ids.get(i))));
            contactDao.delByCusId(Integer.parseInt(String.valueOf(ids.get(i))));
        }
        customerDao.offL();
        int row = customerDao.deleteBatchIds(ids);
        customerDao.onL();
        return row>0?true:false;
    }


    //客户报表部分

    //统计客户等级情况
    public List<ReportEntity> getCustomerCountByLevel(){
        return customerDao.selectCustomerCountByLevel();
    }

    //统计客户地区情况
    public List<ReportEntity> getCustomerCountByRegion(){
        return customerDao.selectCustomerCountByRegion();
    }
}
