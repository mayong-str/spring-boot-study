package com.springboot2.pojo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * author: admin
 * date: 2025/12/2 10:27
 */
@Data
public class Coach {
    private Long id;
    private String username;
    private String sex;
    private Integer age;
    private String subject;
    private String telephone;
    private LocalDateTime createTime;
}
