# SpringMVC_获取参数的一般方式

* 方法上的形参名前端与参数变量名一致会自动赋值：

* 获取pojo参数:形参直接写类名就可以,表单直接写类的各属性，支持级联

### 一、简单测试(无表单)

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
      public class HelloController {

          /**
           * 1. 使用@RequestMapping注解映射请求的url
           * 2. 返回值会通过视图解析器解析为实际的物理视图
           * prefix+ return值 + suffix 得到实际的物理视图，然后转发
           * @return
           */
          @RequestMapping("/form")
          public String testForm(String name,int age){//支持类型自动转换
              System.out.println(name);
              System.out.println(age);
              return "success";
          }
      }



* 在/WEB-INF/views下新建success.jsp

   * 然后访问http://localhost:8080/springmvc_demo/form?name=asd&age=9 即可跳转到 `/WEB-INF/views/success.jsp`页面


### 二、表单获值（普通类型）

* webapp/form.jsp

      <%--
        Created by IntelliJ IDEA.
        User: sunny
        Date: 2018/8/12
        Time: 17:04
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <html>
      <head>
          <title>Title</title>
      </head>
      <body>
          <form action="${pageContext.request.contextPath}/form" method="get">
              <input type="text" name="name"/>
              <input type="age" name="age"/>
              <input type="submit" value="提交">
          </form>
      </body>
      </html>

* HelloController.java（方法上的形参名与前端参数变量名一致会自动赋值：）

      package com.edu.controller;

      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.RequestMapping;

      @Controller//控制层
      public class HelloController {

          /**
           * 1. 使用@RequestMapping注解映射请求的url
           * 2. 返回值会通过视图解析器解析为实际的物理视图
           * prefix+ return值 + suffix 得到实际的物理视图，然后转发
           * @return
           */
          @RequestMapping("/form")
          public String testForm(String name,int age){//支持类型自动转换
              System.out.println(name);
              System.out.println(age);
              return "success";
          }
      }

* 在/WEB-INF/views下新建success.jsp

   * 然后访问http://localhost:8080/springmvc_demo/form.jsp 填值后提交即可跳转到 `/WEB-INF/views/success.jsp`页面


* 在web.xml中配置乱码过滤器

       <filter>
           <filter-name>HiddenHttpMethodFilter</filter-name>
           <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
       </filter>
       <filter-mapping>
           <filter-name>HiddenHttpMethodFilter</filter-name>
           <url-pattern>/*</url-pattern>
       </filter-mapping>

### 三、表单获值（自定义类型）

* User.java

      package com.edu.bean;

      public class User {
          private Integer age;
          private String name;

          public Integer getAge() {
              return age;
          }

          public void setAge(Integer age) {
              this.age = age;
          }

          public String getName() {
              return name;
          }

          public void setName(String name) {
              this.name = name;
          }

          @Override
          public String toString() {
              return "User{" +
                      "age=" + age +
                      ", name='" + name + '\'' +
                      '}';
          }
      }


* HelloController.java（方法上的形参名与前端参数变量名一致会自动赋值：）

      package com.edu.controller;

      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.RequestMapping;

      @Controller//控制层
      public class HelloController {

          /**
           * 1. 使用@RequestMapping注解映射请求的url
           * 2. 返回值会通过视图解析器解析为实际的物理视图
           * prefix+ return值 + suffix 得到实际的物理视图，然后转发
           * @return
           */
            @RequestMapping("/formDIY")//method没规定时，任何形式均能接收
            public String testFormDIY(User user){
            System.out.println(user);
            return "success";
            }
      }

* 在/WEB-INF/views下新建success.jsp

   * 然后访问http://localhost:8080/springmvc_demo/formDIY?name=asd&age=88 填值后提交即可跳转到 `/WEB-INF/views/success.jsp`页面
















