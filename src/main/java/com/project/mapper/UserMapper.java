package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.entity.User;
import com.project.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


    List<Users> query(String empName);

    List<Users> querySome(@Param("empName")String empName);

    void add(Users users);

    void update1(Users users);

    List<Users> queryData();
}
