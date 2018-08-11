# spring+mybatis整合SqlSessionFactory及SqlSession的托管

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

        <!--sqlsesson连接,此类没有 -->
        <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
            <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"/>
        </bean>
    </beans>

### 三、测试

    package com.edu;

    import com.edu.bean.User;
    import com.edu.mapper.UserMapper;
    import org.junit.Test;
    import org.mybatis.spring.SqlSessionTemplate;
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.support.ClassPathXmlApplicationContext;

    import java.util.List;

    public class TestUser {


        @Test
        public void testUser(){
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
            SqlSessionTemplate sqlSessionTemplate = applicationContext.getBean(SqlSessionTemplate.class);
            UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
            List<User> userList = mapper.selectByExample(null);
            System.out.println(userList);
        }
    }











