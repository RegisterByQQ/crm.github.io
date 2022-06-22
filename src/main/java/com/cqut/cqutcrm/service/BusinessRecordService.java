package com.cqut.cqutcrm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.cqutcrm.dao.BusinessRecordDao;
import com.cqut.cqutcrm.entity.BusinessRecord;
import com.cqut.cqutcrm.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessRecordService {
    @Autowired
    BusinessRecordDao recordDao;

    //查询，根据客户id查询
    public IPage<BusinessRecord> getOnePage(Integer page, Integer rows, Integer cusId){
        IPage<BusinessRecord> objectPage = new Page<>(page,rows);//页码，一页的行数

        QueryWrapper<BusinessRecord> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("cus_id",cusId);
        objectPage = recordDao.selectPage(objectPage,objectQueryWrapper);
        return objectPage;
    }

    //新增交易记录
    public boolean add(BusinessRecord businessRecord){
        int row = recordDao.insert(businessRecord);
        return row>0?true:false;
    }

    //更新
    public boolean update(BusinessRecord businessRecord){
        int row = recordDao.updateById(businessRecord);
        return row>0?true:false;

    }

    //删除
    public boolean del(Integer recordId){
        int row = recordDao.deleteById(recordId);
        return row>0?true:false;
    }


    //根据cusid删除用户
    public void delByCusId(Integer cusId){
        QueryWrapper<Object> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("cus_id",cusId);
        recordDao.delByCusId(cusId);
    }
}
