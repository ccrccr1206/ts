package com.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class Users {
    private Integer id;

    private String empName;

    private String sex;

    private String age;

    private String deptName;

    private String empDegreeName;
}
