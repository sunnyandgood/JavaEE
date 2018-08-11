# spring+mybatis整合MapperScannerConfigurer(批量配置,final)

### 一、`db.properties`

    jdbc.JdbcUrl=jdbc:mysql://localhost:3306/mybatis?useUnicode=true&amp;characterEncoding=UTF-8
    jdbc.JdbcDriver=com.mysql.jdbc.Driver
    jdbc.user=root
    jdbc.password=root

### 二、在resource资源文件夹下新建XML Configuration File->Spring Config、取名`spring.xml`

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context" 
           xmlns:tx="http://www.springframework.org/schema/tx"
           xmlns:aop="http://www.springframework.org/schema/aop"
           xsi:schemaLocation="http://www.springframework.org/schema/beans 
               http://www.springframework.org/schema/beans/spring-beans.xsd 
               http://www.springframework.org/schema/context 
               http://www.springframework.org/schema/context/spring-context.xsd 
               http://www.springframework.org/schema/tx 
               http://www.springframework.org/schema/tx/spring-tx.xsd 
               http://www.springframework.org/schema/aop 
               http://www.springframework.org/schema/aop/spring-aop.xsd">

        <context:component-scan base-package="com.edu"/>
        
        <context:property-placeholder location="db.properties"/>
        <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
            <property name="jdbcUrl" value="${jdbc.JdbcUrl}"/>
            <property name="driverClass" value="${jdbc.JdbcDriver}"/>
            <property name="user" value="${jdbc.user}"/>
            <property name="password" value="${jdbc.password}"/>
        </bean>

        <!-- sqlsession工厂 -->
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <property name="dataSource" ref="dataSource"/>
            <property name="typeAliasesPackage" value="com.edu.bean"/>
            <property name="mapperLocations" value="classpath:mapper/*Mapper.xml"/>
            <property name="configLocation" value="classpath:mybatis-config.xml"/>
        </bean>

        <!--维护所有Mapper-->
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <property name="basePackage" value="com.edu.mapper"></property>
        </bean>
    </beans> 

### 三、详细代码实现

* 业务层接口：

      package com.edu.service;

      import com.edu.bean.User;

      import java.util.List;

      public interface UserService {
          List<User> selectAll();
      }


* 业务层实现：

      package com.edu.service.impl;

      import com.edu.bean.User;
      import com.edu.mapper.UserMapper;
      import com.edu.service.UserService;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.stereotype.Service;

      import java.util.List;

      @Service//业务层
      public class UserServiceImpl implements UserService {

          @Autowired
          private UserMapper userMapper;

          @Override
          public List<User> selectAll() {
              return userMapper.selectByExample(null);
          }
      }

### 四、测试

      package com.edu;

      import com.edu.bean.User;
      import com.edu.service.UserService;
      import org.junit.Test;
      import org.springframework.context.ApplicationContext;
      import org.springframework.context.support.ClassPathXmlApplicationContext;

      import java.util.List;

      public class TestUser {
          @Test
          public void testServiceImpl(){
              ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
              UserService userService = applicationContext.getBean(UserService.class);
              List<User> list = userService.selectAll();
              System.out.println(list);
          }
      }
