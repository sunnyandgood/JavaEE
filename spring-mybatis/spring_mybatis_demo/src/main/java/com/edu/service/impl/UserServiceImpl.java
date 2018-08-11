package com.edu.service.impl;

import com.edu.bean.User;
import com.edu.mapper.UserMapper;
import com.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectByExample(null);
    }

    @Override
    public void run() {
        User user = new User();
        user.setUserName("小明100");
        user.setEmail("sdfsf@163.com");
        userMapper.insertSelective(user);

        User user2 = new User();
        user2.setId(59);
        user2.setUserName("小红300");
        userMapper.insertSelective(user2);
    }
}
