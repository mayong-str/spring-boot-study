package com.springboot2.service;

import com.springboot2.dao.CoachMapper;
import com.springboot2.pojo.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * author: admin
 * date: 2025/12/2 13:26
 */
@Service
public class CoachService {

    @Autowired
    CoachMapper coachMapper;

    public int addCoach(Coach coach) {
        coach.setCreateTime(LocalDateTime.now());
        return coachMapper.insertCoach(coach);
    }

    public Coach getCoachById(int id) {
        return coachMapper.selectCoachById(id);
    }

    public List<Coach> selectCoachAll(){
        return coachMapper.selectCoachAll();
    }

    public Coach updateCoach(Coach coach) {
        coach.setCreateTime(LocalDateTime.now());
        return coachMapper.updateCoach(coach);
    }

    public  int deleteCoach(int id) {
        return coachMapper.deleteCoachById(id);
    }
}
