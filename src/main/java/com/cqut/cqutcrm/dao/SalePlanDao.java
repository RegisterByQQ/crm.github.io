package com.cqut.cqutcrm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.cqutcrm.entity.SalePlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SalePlanDao extends BaseMapper<SalePlan> {

//    @Select("SELECT dp.dp_id , dp.dp_plandate , dp.dp_plancontent , dp.dp_execase , dp.dp_exedate ,\n" +
//            " dp.dp_exemanager , dp.sc_id from sale_chance  sc , dev_plan dp\n" +
//            "where sc.sc_id = dp.sc_id\n" +
//            "and sc.sc_giveuser=#{giveuser}")
//    List<SalePlan> queryPlan(String giveuser);

    @Select("SELECT dp.dp_id , dp.dp_plandate , dp.dp_plancontent , dp.dp_execase , dp.dp_exedate ,\n" +
            " dp.dp_exemanager , dp.sc_id from sale_chance sc , dev_plan dp\n" +
            "where sc.sc_id = dp.sc_id\n" +
            "and sc.sc_giveuser=#{giveuser} limit #{begin} , #{rows}")
    List<SalePlan> queryPlan
            (@Param("giveuser") String giveuser,
             @Param("begin") Integer begin ,
             @Param("rows") Integer rows);
    @Select("SELECT count(*) from sale_chance sc , dev_plan dp\n" +
            "where sc.sc_id = dp.sc_id\n" +
            "and sc.sc_giveuser=#{giveuser}")
    Integer queryPlanCount(@Param("giveuser") String giveuser);
}