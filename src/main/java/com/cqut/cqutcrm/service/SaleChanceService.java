package com.cqut.cqutcrm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqut.cqutcrm.dao.SaleChanceDao;
import com.cqut.cqutcrm.entity.SaleChance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service//注解功能：spring框架创建对象
public class SaleChanceService {

    @Autowired//注解功能：spring容器中找到SaleChanceDao的实现类对象，赋值给变量saleChanceDao
    SaleChanceDao saleChanceDao;

    //1.定义查询所有的方法
    public List<SaleChance> queryAll(){
        List<SaleChance> list = saleChanceDao.selectList(null);
        return list;
    }

    //查询指定页的数据
    public IPage<SaleChance> getOnePage(Integer page,Integer rows){
        IPage<SaleChance> objectPage = new Page<>(page,rows);//页码，一页的行数
        objectPage = saleChanceDao.selectPage(objectPage,null);
        return objectPage;
    }


    //2.定义一个添加销售机会的方法
    public boolean addSc(SaleChance sc){
        //初次添加销售机会时候需要设置，销售机会状态为0，添加的人，添加的时间
        sc.setScStatus(0);
        sc.setScCreatetime(new Date());
        int row = saleChanceDao.insert(sc);
        return row>0?true:false;
    }

    //3.提供一个根据主键更新的方法
    public boolean updateSc(SaleChance sc){
        int row = saleChanceDao.updateById(sc);
        return row>0?true:false;
    }

    //4.提供一个根据主键删除的方法
    public boolean deleteSc(Integer scId){
        int row = saleChanceDao.deleteById(scId);
        return row>0?true:false;
    }

    //5.提供一个根据分配的客户经理的名字进行查询的方法
    public IPage<SaleChance> getOnePageByGivename(Integer page,Integer row,String uname){
        IPage<SaleChance> objectIPage = new Page<>(page, row);
        QueryWrapper<SaleChance> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.like("sc_giveuser",uname);
        objectIPage = saleChanceDao.selectPage(objectIPage,objectQueryWrapper);

        return objectIPage;
    }

    //根据销售机会scid，查询销售机会
    public SaleChance queryByScId(Integer scId){
        return saleChanceDao.selectById(scId);
    }


}

































