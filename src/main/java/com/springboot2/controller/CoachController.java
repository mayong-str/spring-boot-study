package com.springboot2.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.springboot2.pojo.Coach;
import com.springboot2.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * author: admin
 * date: 2025/12/2 10:25
 */
@Controller
@RequestMapping("/coaches")
public class CoachController {

    @Autowired
    CoachService coachService;

    @GetMapping("/coach-management")
    public String showCoach(Model model) {
        List<Coach> coachList = coachService.selectCoachAll();
        model.addAttribute("coachList", coachList);
        return "coach";
    }

    @GetMapping("/toAddCoach")
    public String toAddCoachPage() {
        return "addcoach";
    }

    @PostMapping("/addCoach")
    public String addCoach(Coach coach, Model model) {
        int rows = coachService.addCoach(coach);
        if (rows > 0) {
            List<Coach> coachList = coachService.selectCoachAll();
            model.addAttribute("coachList", coachList);
        } else {
            model.addAttribute("errorMsg", "Failed to add coach information");
        }
        return "coach";
    }

    @PostMapping("/selectCoachById")
    public Coach getCoachById(int id) {
        return coachService.getCoachById(id);
    }

    @PostMapping("/selectCoachAll")
    public String getCoachAll(Model model) {
        List<Coach> coachList = coachService.selectCoachAll();
        model.addAttribute("coachList", coachList);
        return "coach";
    }

    @PostMapping("/updateCoach")
    public Coach updateCoach(Coach coach) {
        return coachService.updateCoach(coach);
    }

    @PostMapping("/deleteCoach")
    public int deleteCoach(int id) {
        return coachService.deleteCoach(id);
    }

    /**
     * 教练列表分页页面
     */
    @GetMapping("/list")
    public String coachList(
            @RequestParam(defaultValue = "1") Integer pageNum,  // 默认第1页
            @RequestParam(defaultValue = "10") Integer pageSize, // 默认10条/页
            Model model) {

        // 1. 调用Service获取分页结果
        PageInfo<Coach> pageInfo = coachService.getCoachPage(pageNum, pageSize);
        // 2. 传递数据到前端（PageInfo包含所有分页信息）
        model.addAttribute("pageInfo", pageInfo);

        return "coach"; // 你的页面路径（和之前保持一致）
    }
}
