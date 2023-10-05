package com.xxxx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
