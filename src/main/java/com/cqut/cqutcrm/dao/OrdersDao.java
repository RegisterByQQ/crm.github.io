package com.cqut.cqutcrm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.cqutcrm.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrdersDao extends BaseMapper<Orders> {
    @Select("delete from orders where orders.cus_id = #{cus_id}")
    Integer delByCusId(@Param("cus_id") Integer id);
}
