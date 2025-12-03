package com.springboot2.dao;

import com.springboot2.pojo.Coach;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
*
*author: admin
*date: 2025/12/2 13:16
*/
@Mapper
public interface CoachMapper{
    int insertCoach(Coach coach);
    int deleteCoachById(Integer id);
    Coach selectCoachById(Integer id);
    List<Coach> selectCoachAll();
    Coach updateCoach(Coach coach);

}
