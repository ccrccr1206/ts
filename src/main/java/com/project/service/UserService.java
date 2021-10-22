package com.project.service;

import com.project.entity.User;
import com.project.entity.Users;

import java.util.List;

public interface UserService {

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    User getUser(String name);


    List<Users> query(String empName);

    List<Users> querySome(String empName, int pageNumber, int pageSize);


    void add(Users users);

    void delete(int id);

    void update(Users users);

    List<Users> queryData(Object o, int i, int i1);
}
