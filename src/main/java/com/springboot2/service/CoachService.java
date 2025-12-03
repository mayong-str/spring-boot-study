package com.springboot2.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /**
     * 分页查询教练列表
     * @param pageNum  当前页码（从1开始）
     * @param pageSize 每页条数
     * @return 分页结果（包含数据+分页信息）
     */
    public PageInfo<Coach> getCoachPage(Integer pageNum, Integer pageSize) {
        // 1. 开启分页：PageHelper.startPage(页码, 每页条数)
        // 注意：这行代码必须在查询SQL执行之前调用
        PageHelper.startPage(pageNum, pageSize);

        // 2. 执行查询（PageHelper会自动拦截，添加分页SQL）
        List<Coach> coachList = coachMapper.selectCoachAll();

        // 3. 包装分页结果：PageInfo包含总记录数、总页数等信息
        return new PageInfo<>(coachList);
    }
}
