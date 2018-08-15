# SpringMVC_请求资源

### 问题分析

* springmvc默认只能访问jsp，其他任何资源css,js,html都不能直接访问

* 我们需要在springmvc.xml中加配置 

      <mvc:default-servlet-handler/>

  * 意思是如果springmvc中没有映射的路径。会交给tomcat默认处理

  * 但是加了此配置后，发现@RequestParameter注解失效 需要加配置 
  
        <mvc:annotation-driven/>
  
* 或者在springmvc.xml中加配置 

      <mvc:resources mapping="/resources/**" location="/resources/"/>

      <mvc:resources mapping="/a/**" location="/resources/"/>

      <mvc:resources mapping="/aa/**" location="/WEB-INF/resources/"/>
