# SpringMVC_RequestMapping注解之属性

### 一、RequestMapping注解的属性解析

* 1、拥有属性：（value、method、params、headers）

    * 1>value(默认即为value)//请求路径
    
      >eg:
      
          @RequestMapping("/hello")
          @RequestMapping(value = "/hello")
          
          //请求方式：http://localhost:8080/springmvc_demo/hello

    * 2>method//请求方式
    
         * RequestMethod.GET
         
         * RequestMethod.POST
         
         * RequestMethod.DELETE
         
         * RequestMethod.HEAD
         
         * RequestMethod.OPTIONS
         
         * RequestMethod.PATCH
         
         * RequestMethod.PUT
         
         * RequestMethod.TRACE
      
      >eg:
      
          @RequestMapping(value = "/hello")
          @RequestMapping(value = "/hello",method = RequestMethod.GET)
          @RequestMapping(value = "/hello",method = RequestMethod.POST)
          
          //请求方式：http://localhost:8080/springmvc_demo/hello

    * 3>params//请求须带的参数
    
      >eg:
      
          params = {"name","age=2"})//请求后跟name和age参数，且age=2(name的值随意，age必须为2)
          @RequestMapping(value = "/hello",method = RequestMethod.GET,params = {"name","age=2"})
          
          //请求方式：http://localhost:8080/springmvc_demo/hello?name=asd&age=2

    * 4>headers
    
      >eg:
      
            //可以访问，因为浏览器会自己传Connection=keep-alive请求头
            @RequestMapping(value = "/hello",method = RequestMethod.GET,
                 params = {"name","age=2"},headers = "Connection=keep-alive")
            
            //不可以访问，因为浏览器没有Connection=keep-alive2请求头，需要直接封装请求头
            @RequestMapping(value = "/hello",method = RequestMethod.GET,
                 params = {"name","age=2"},headers = "Connection=keep-alive2")
            
            
            //请求方式：http://localhost:8080/springmvc_demo/hello?name=asd&age=2

* 2、方法限制(method)

  >eg：

      @RequestMapping(value="/hello",method=RequestMethod.GET) --请求地址是/hello，而且必须是get请求

      @RequestMapping(value="/hello",method=RequestMethod.POST) --请求地址是/hello，而且必须是post请求

* 3、参数限制|请求头限制(了解)：

  >eg:
  
      @RequestMapping(value = "/hello",params = {"name=1","age"},headers ="Connection=keep-alive" )

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
      import org.springframework.web.bind.annotation.RequestMethod;

      @Controller//控制层
      public class HelloController {

          /**
           * 1. 使用@RequestMapping注解映射请求的url
           * 2. 返回值会通过视图解析器解析为实际的物理视图
           * prefix+ return值 + suffix 得到实际的物理视图，然后转发
           * @return
           */
          //请求后跟name和age参数，且age=2
          @RequestMapping(value = "/hello",method = RequestMethod.GET,params = {"name","age=2"},
                  headers = "Connection=keep-alive")
          public String run(){
              System.out.println("hello SpringMVC!");
              return "success";
          }
      }

* 在/WEB-INF/views下新建success.jsp

   * 然后访问http://localhost:8080/springmvc_demo/hello?name=asd&age=2 即可跳转到 `/WEB-INF/views/success.jsp`页面




































