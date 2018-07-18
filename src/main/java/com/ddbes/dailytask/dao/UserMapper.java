package com.ddbes.dailytask.dao;

import com.ddbes.dailytask.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT id,username FROM `user` WHERE login_name = #{loginName} AND password=#{password}")
    Map<String,Object> login(@Param("loginName") String loginName,@Param("password") String password);
}
