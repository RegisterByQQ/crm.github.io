package com.cqut.cqutcrm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.cqutcrm.entity.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ContactDao extends BaseMapper<Contact> {
    @Select("delete from contact where contact.cus_id = #{cus_id}")
    Integer delByCusId(@Param("cus_id") Integer id);
}
