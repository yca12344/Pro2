package com.example.springdb.service;

import com.example.springdb.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    int save(User user);

    Integer delete(int id);

    User get(int id);

    int update(User user);

    User selectUserById(int id);

    boolean login(User user);

    boolean register(User user);
}
