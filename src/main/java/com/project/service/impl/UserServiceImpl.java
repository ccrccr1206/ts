package com.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.project.entity.User;
import com.project.entity.Users;
import com.project.mapper.UserMapper;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id, name, password")
                .eq("name", name);

        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Users> query(String empName) {
        return userMapper.query(empName);
    }

    @Override
    public List<Users> querySome(String empName, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return userMapper.querySome(empName);
    }

    @Override
    public void add(Users users) {
        userMapper.add(users);
    }

    @Override
    public void delete(int id) {
        userMapper.deleteById(id);
    }

    @Override
    public void update(Users users) {
         userMapper.update1(users);
    }

    @Override
    public List<Users> queryData(Object o, int i, int i1) {
        return userMapper.queryData();
    }


}
