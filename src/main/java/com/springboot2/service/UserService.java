package com.springboot2.service;

import com.springboot2.dao.UserMapper;
import com.springboot2.pojo.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * author: admin
 * date: 2025/12/1 14:30
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录验证
     * @param username 用户名
     * @param password 密码
     * @param role 用户角色
     * @return true：登录成功；false：登录失败
     */
    public boolean login(String username, String password, Integer role) {
        User user = userMapper.selectByUsername(username, role);
        if (user == null) {
            return false; // 用户不存在
        }
        // 验证密码
        return BCrypt.checkpw(password, user.getPassword());
    }


    /**
     * 注册用户
     * @param user 注册用户信息
     * @return true：注册成功；false：用户名已存在
     */
    @Transactional // 事务管理（确保插入操作原子性）
    public boolean register(User user) {
        // 1. 检查用户名是否已存在
        User existingUser = userMapper.selectByUsername(user.getUsername(), user.getRole());
        if (existingUser != null) {
            return false; // 用户名已存在
        }

        // 2. 密码加密（重要！禁止明文存储，这里用 BCrypt 示例）
        String encryptedPwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(encryptedPwd);

        // 3. 设置创建时间
        user.setCreateTime(LocalDateTime.now());

        // 4. 插入数据库
        int rows = userMapper.insertUser(user);
        return rows > 0; // 插入成功返回 true
    }
}
