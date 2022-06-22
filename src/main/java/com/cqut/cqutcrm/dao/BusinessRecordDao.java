package com.cqut.cqutcrm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.cqutcrm.entity.BusinessRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BusinessRecordDao extends BaseMapper<BusinessRecord> {
    @Select("delete from business_record where business_record.cus_id = #{cus_id}")
    Integer delByCusId(@Param("cus_id") Integer id);
}
