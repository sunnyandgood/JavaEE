package com.edu.service;

import com.edu.bean.User;

import java.util.List;

public interface UserService {
    List<User> selectAll();

    void run();
}
