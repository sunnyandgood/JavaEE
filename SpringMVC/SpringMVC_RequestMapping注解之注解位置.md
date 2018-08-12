# SpringMVC_RequestMapping注解之注解位置

### 一、注解位置解析

* 1、类上

* 2、方法上

* 请求的地址将会是 `/项目名/类上注解/方法上注解`

   >eg:
   
      http://localhost:8080/springmvc_demo/student/list


### 二、项目实战

* 需要jar包：

    * core、beans、context、expression、aop、web、web-mvc

    * commons-logging 


* 首先要在web.xml编写`DispatcherServlet`

      <?xml version="1.0" encoding="UTF-8"?>
      <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
               http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
               version="3.1">
          <!-- 配置DispatcherServlet -->
          <!--The front controller of this Spring Web application, -->
                         <!--responsible for handling all application requests-->
          <servlet>
              <servlet-name>springDispatcherServlet</servlet-name>
              <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
              <init-param>
                  <param-name>contextConfigLocation</param-name>
                  <param-value>classpath:springmvc.xml</param-value>
              </init-param>
              <load-on-startup>1</load-on-startup>
          </servlet>

          <!-- Map all requests to the DispatcherServlet for handling -->
          <servlet-mapping>
              <servlet-name>springDispatcherServlet</servlet-name>
              <url-pattern>/</url-pattern>
          </servlet-mapping>

      </web-app>

* 随后在resource目录下创建springmvc.xml 在xml配置包扫描和视图解析器

      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:context="http://www.springframework.org/schema/context"
             xmlns:mvc="http://www.springframework.org/schema/mvc"
             xsi:schemaLocation="http://www.springframework.org/schema/mvc 
               http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd
               http://www.springframework.org/schema/beans 
               http://www.springframework.org/schema/beans/spring-beans.xsd
               http://www.springframework.org/schema/context 
               http://www.springframework.org/schema/context/spring-context-4.3.xsd">

          <!-- 配置扫描包 -->
          <context:component-scan base-package="com.edu.controller"></context:component-scan>
          <!-- 配置视图解析器 -->
          <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
              <property name="prefix" value="/WEB-INF/views/"></property>
              <property name="suffix" value=".jsp"></property>
          </bean>
      </beans>
      
* 新建java文件，弄成控制层：(**WEB-INF是安全目录，不能直接访问，用转发的方式进去**。)

      package com.edu.controller;

      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.RequestMapping;

      @Controller//控制层
      @RequestMapping("/student")//默认是value属性
      public class StudentController {
          /**
           * 1. 使用@RequestMapping注解映射请求的url
           * 2. 返回值会通过视图解析器解析为实际的物理视图
           * prefix+ return值 + suffix 得到实际的物理视图，然后转发
           * @return
           */
          @RequestMapping("/list")//默认是value属性
          public String list(){
              return "student_list";
          }
      }
      

* 在/WEB-INF/views下新建student_list.jsp

   * 然后访问http://localhost:8080/springmvc_demo/student/list 即可跳转到 `/WEB-INF/views/student_list.jsp`页面
