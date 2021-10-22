package com.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("login")
public class User {

    private Integer id;

    private String name;

    private String password;



}
