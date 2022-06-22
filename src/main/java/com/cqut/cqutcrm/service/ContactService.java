package com.cqut.cqutcrm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.cqutcrm.dao.ContactDao;
import com.cqut.cqutcrm.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactDao contactDao;

    //查询，根据客户id查询
    public IPage<Contact> getOnePage(Integer page, Integer rows, Integer cusId){
        IPage<Contact> objectPage = new Page<>(page,rows);//页码，一页的行数

        QueryWrapper<Contact> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("cus_id",cusId);
        objectPage = contactDao.selectPage(objectPage,objectQueryWrapper);
        return objectPage;
    }

    //添加
    public boolean add(Contact contact){
        int row = contactDao.insert(contact);
        return row>0?true:false;
    }

    //更新
    public boolean update(Contact contact){
        int row = contactDao.updateById(contact);
        return row>0?true:false;

    }

    //删除
    public boolean del(Integer conid){
        int row = contactDao.deleteById(conid);
        return row>0?true:false;
    }

    //根据cusid删除用户
    public void delByCusId(Integer cusId){
        QueryWrapper<Object> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("cus_id",cusId);
        contactDao.delByCusId(cusId);
    }



}
