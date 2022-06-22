package com.cqut.cqutcrm.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.cqutcrm.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//注解功能：让spring的框架创建这个对象
//BaseMapper封装了基本的crud,如果有关联查询，需要另外写方法
public interface UserDao extends BaseMapper<User> {

}
