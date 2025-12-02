package com.springboot2.controller;

import com.springboot2.pojo.User;
import com.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * author: admin
 * date: 2025/12/1 10:42
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginPage(){
        return "index";
    }

    // 显示注册页面
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // 处理注册请求
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        // 调用 Service 注册
        boolean registerSuccess = userService.register(user);

        if (!registerSuccess) {
            model.addAttribute("error", "用户名已存在！");
            return "register"; // 注册失败，返回注册页
        }

        model.addAttribute("success", "注册成功，请登录！");
        return "index"; // 注册成功，跳转到登录页（或首页）
    }

    // 处理登录请求
    @PostMapping("/login")
    public String loginUser(String username, String password, Integer role, Model model) {
        boolean result = userService.login(username, password, role);

        if (result) {
            model.addAttribute("username", username);
            return "home";
        }
        else {
            model.addAttribute("error", "用户名或密码错误！");
            return "index";
        }
    }

    // 首页/欢迎页面
    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}
