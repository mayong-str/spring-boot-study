package com.springboot2.dao;

import com.springboot2.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * author: admin
 * date: 2025/12/1 14:25
 */
@Mapper
public interface UserMapper {
    User selectByUsername(@Param("username") String username, @Param("role") Integer role );
    int insertUser(User user);
}
