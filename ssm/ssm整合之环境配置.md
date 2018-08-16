# ssm整合之环境配置

### 一、导入基础jar包

    <!-- springmvc -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>4.3.18.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>4.3.18.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.9.1</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>4.3.13.RELEASE</version>
    </dependency>
    
    
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.4.1</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.3.2</version>
    </dependency>
    
    
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.14</version>
    </dependency>
    <dependency>
      <groupId>com.mchange</groupId>
      <artifactId>c3p0</artifactId>
      <version>0.9.5.2</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>

### 二、`web.xml`(web容器)

    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
                                 http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
             version="3.1">

        <!--spring mvc 容器启动-->
        <servlet>
            <servlet-name>DispatcherServlet</servlet-name>
            <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
            <init-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>classpath:spring-mvc.xml</param-value>
            </init-param>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>DispatcherServlet</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>

        <!-- spring 容器启动-->
        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring.xml</param-value>
        </context-param>
        <listener>
            <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
        </listener>

        <!--字符编码过滤器，放在最前-->
        <filter>
            <filter-name>CharacterEncodingFilter</filter-name>
            <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
            <init-param>
                <param-name>encoding</param-name>
                <param-value>utf-8</param-value>
            </init-param>
        </filter>
        <filter-mapping>
            <filter-name>CharacterEncodingFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>

        <!-- restful 支持 -->
        <filter>
            <filter-name>HiddenHttpMethodFilter</filter-name>
            <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
        </filter>
        <filter-mapping>
            <filter-name>HiddenHttpMethodFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
    </web-app>  

### 三、`spring-mvc.xml`(springmvc容器)

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xmlns:mvc="http://www.springframework.org/schema/mvc"
           xsi:schemaLocation="http://www.springframework.org/schema/beans 
                               http://www.springframework.org/schema/beans/spring-beans.xsd 
                               http://www.springframework.org/schema/context 
                               http://www.springframework.org/schema/context/spring-context.xsd 
                               http://www.springframework.org/schema/mvc 
                               http://www.springframework.org/schema/mvc/spring-mvc.xsd">
        <!-- 扫描控制器，springmvc容器维护-->
        <context:component-scan base-package="com.edu.controller"/>
        <!-- 支持requestmapping注解，jsr303-->
        <mvc:annotation-driven/>
        <!-- 访问静态资源 -->
        <mvc:resources mapping="/resources/**" location="/resources/"/>
        <!-- 视图解析器 -->
        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="prefix" value="/WEB-INF/views/"></property>
            <property name="suffix" value=".jsp"></property>
        </bean>
    </beans>

### 四、`db.properties`

    jdbc.JdbcUrl=jdbc:mysql://localhost:3306/mybatis?characterEncoding=UTF-8
    jdbc.JdbcDriver=com.mysql.jdbc.Driver
    jdbc.user=root
    jdbc.password=root

### 五、`spring.xml`(spring容器)

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
                               http://www.springframework.org/schema/cache 
                               http://www.springframework.org/schema/cache/spring-cache.xsd 
                               http://www.springframework.org/schema/tx 
                               http://www.springframework.org/schema/tx/spring-tx.xsd 
                               http://www.springframework.org/schema/aop 
                               http://www.springframework.org/schema/aop/spring-aop.xsd">

        <!-- spring 容器扫描除 controller的bean进行维护-->
        <context:component-scan base-package="com.edu">
            <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        </context:component-scan>

        <context:property-placeholder location="classpath:db.properties"/>

        <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
            <property name="jdbcUrl" value="${jdbc.JdbcUrl}"></property>
            <property name="driverClass" value="${jdbc.JdbcDriver}"></property>
            <property name="user" value="${jdbc.user}"></property>
            <property name="password" value="${jdbc.password}"></property>
        </bean>

        <!--sqlsessionfactory-->
        <bean class="org.mybatis.spring.SqlSessionFactoryBean">
            <property name="configLocation" value="classpath:mybatis-config.xml"/>
            <property name="dataSource" ref="dataSource"/>
            <property name="typeAliasesPackage" value="com.edu.bean"/>
            <property name="mapperLocations" value="classpath:mapper/*.xml"/>
        </bean>

        <!-- 接口扫描-->
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <property name="basePackage" value="com.edu.mapper"/>
        </bean>

        <!--事务管理器-->
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <property name="dataSource" ref="dataSource"/>
        </bean>
        <!-- 事务属性-->
        <tx:advice id="txAdvice" transaction-manager="transactionManager">
            <tx:attributes>
                <tx:method name="*"/>
            </tx:attributes>
        </tx:advice>
        <!-- 切入-->
        <aop:config>
            <aop:pointcut id="txPoint" expression="execution(* com.edu.service..*.*(..))"/>
            <aop:advisor advice-ref="txAdvice" pointcut-ref="txPoint"/>
        </aop:config>
    </beans>

### 六、`mybatis-config.xml`

    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <settings>
            <setting name="mapUnderscoreToCamelCase" value="true"/>
        </settings>
    </configuration>































