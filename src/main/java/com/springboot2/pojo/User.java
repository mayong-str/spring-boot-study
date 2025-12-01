package com.springboot2.pojo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * author: admin
 * date: 2025/12/1 10:28
 */
@Data
public class User {
    private Long id; // 对应数据库 id（自增主键）
    private String username; // 用户名（唯一）
    private String password; // 密码
    private String nickname; // 昵称
    private Integer role; // 角色（0：学员，1：教练，2：管理员）
    private String email; // 邮件
    private LocalDateTime createTime; // 创建时间
}