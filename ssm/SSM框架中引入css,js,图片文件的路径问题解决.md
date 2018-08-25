# SSM框架中引入css,js,图片文件的路径问题解决

### 一、（方式一）在`web.xml`文件中添加以下代码：

     <!-- 静态资源文件的引入 -->
     <servlet-mapping>
      <servlet-name>default</servlet-name>
      <url-pattern>*.js</url-pattern>
     </servlet-mapping>
     <servlet-mapping>
      <servlet-name>default</servlet-name>
      <url-pattern>*.css</url-pattern>
     </servlet-mapping>
     <servlet-mapping>
      <servlet-name>default</servlet-name>
      <url-pattern>*.png</url-pattern>
     </servlet-mapping>


* 然后在jsp文件头部添加如下代码：

      <%
       String path = request.getContextPath();
       String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
              + request.getServerPort()+ path + "/";
      %>

* 需要引入相应的样式资源文件时：使用`<link href="<%=basePath%>/assets/css/bootstrap.min.css" rel="stylesheet">`，这种形式即可解决路径问题。


### 二、（方式二）在`SpringMVC.xml`中添加如下代码

    <!-- 静态资源映射 location是本地静态资源路径 mapping是映射的url地址，访问时就使用该地址 -->
    <mvc:default-servlet-handler />
    <mvc:resources location="/WEB-INF/static/" mapping="/**" /> 

* 在jsp页面引入c标签

      <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
      <%
      String path = request.getContextPath();
      String basePath = request.getScheme()+"://"+request.getServerName()+":"
          +request.getServerPort()+path+"/";
      %>

* 在引入js、css时使用c标签

      <link rel="stylesheet" href="${ctx}/style.css" type="text/css"></link>








