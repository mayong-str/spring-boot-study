package com.springboot2.controller;

import com.springboot2.pojo.Coach;
import com.springboot2.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * author: admin
 * date: 2025/12/2 10:25
 */
@Controller
@RequestMapping("/coachs")
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
    public Coach getCoachById(int id, Model model) {
        return coachService.getCoachById(id);
    }

    @PostMapping("/selectCoachAll")
    public String getCoachAll(Model model) {
        List<Coach> coachList = coachService.selectCoachAll();
        model.addAttribute("coachList", coachList);
        return "coach";
    }

    @PostMapping("/updateCoach")
    public Coach updateCoach(Coach coach, Model model) {
        return coachService.updateCoach(coach);
    }

    @PostMapping("/deleteCoach")
    public int deleteCoach(int id, Model model) {
        return coachService.deleteCoach(id);
    }
}
