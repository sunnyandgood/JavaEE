# spring+mybatis整合之声明式事务配置

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
        <!--<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">-->
            <!--<constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"/>-->
        <!--</bean>-->

         <!--维护所有Mapper-->
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <property name="basePackage" value="com.edu.mapper"></property>
        </bean>


        <!-- 事务管理器 -->
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <property name="dataSource" ref="dataSource"></property>
        </bean>
        <!-- 事务属性 -->
        <tx:advice id="txAdvice" transaction-manager="transactionManager">
            <tx:attributes>
                <tx:method name="list*" read-only="true"/>
                <tx:method name="select*" read-only="true"/>
                <tx:method name="get*" read-only="true"/>
                <tx:method name="*"/>
            </tx:attributes>
        </tx:advice>
        <!--  切面  -->
        <aop:config>
            <aop:pointcut expression="execution(* com..service..*.*(..))" id="txPointcut"/>
            <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/>
        </aop:config>
    </beans>

### 三、详细代码实现

* 业务层接口：

      package com.edu.service;

      public interface UserService {
      
          void run();
      }



* 业务层实现：

      package com.edu.service.impl;

      import com.edu.bean.User;
      import com.edu.mapper.UserMapper;
      import com.edu.service.UserService;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.stereotype.Service;

      @Service
      public class UserServiceImpl implements UserService {

          @Autowired
          private UserMapper userMapper;

          @Override
          public void run() {
              User user = new User();
              user.setUserName("小明100");
              user.setEmail("sdfsf@163.com");
              userMapper.insertSelective(user);

              User user2 = new User();
              user2.setId(56);//已存在的id
              user2.setUserName("小红300");
              userMapper.insertSelective(user2);
          }
      }

### 四、测试

    package com.edu;

    import com.edu.service.UserService;
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.test.context.ContextConfiguration;
    import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

    @RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
    @ContextConfiguration(locations={"classpath:spring.xml"})
    public class TestUser {

        @Autowired
        private UserService userService;
        @Test
        public void testAop(){
            userService.run();

        }
    }
