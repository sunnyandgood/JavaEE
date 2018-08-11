package com.edu;

import com.edu.bean.User;
import com.edu.mapper.UserMapper;
import com.edu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:spring.xml"})
public class TestUser {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUser(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        SqlSessionTemplate sqlSessionTemplate = applicationContext.getBean(SqlSessionTemplate.class);
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        List<User> userList = mapper.selectByExample(null);
        System.out.println(userList);
    }

    @Test
    public void testServiceImpl(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        List<User> list = userService.selectAll();
        System.out.println(list);
    }

    @Test
    public void testjunit4(){
        List<User> userList = userMapper.selectByExample(null);
        System.out.println(userList);
    }

    @Test
    public void testAop(){
        userService.run();
    }

}