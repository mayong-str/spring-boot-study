package com.springboot2.controller;

import com.springboot2.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * author: admin
 * date: 2025/12/1 10:42
 */
@Controller
public class UserController {
    // 模拟数据库存储用户信息
    private static final Map<String, User> userDatabase = new HashMap<>();

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
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
        if (userDatabase.containsKey(user.getUsername())) {
            model.addAttribute("error", "用户名已存在！");
            return "register";
        }

        userDatabase.put(user.getUsername(), user);
        model.addAttribute("success", "注册成功，请登录！");
        return "login";
    }

    // 处理登录请求
    @PostMapping("/login")
    public String loginUser(String username, String password, Model model) {
        User user = userDatabase.get(username);

        if (user == null) {
            model.addAttribute("error", "用户名不存在！");
            return "login";
        }

        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "密码错误！");
            return "login";
        }

        model.addAttribute("username", username);
        return "welcome";
    }

    // 首页/欢迎页面
    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}
