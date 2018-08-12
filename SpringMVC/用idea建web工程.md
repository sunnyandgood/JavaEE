# 用idea建web工程

### 一、建工程

* File->New Project->Maven->Create from archetype->maven-archetype-webapp

    <div align="center"><img src="./img/newProject1.png"/></div>

* Next->填写内容

    <div align="center"><img src="./img/newProject2.png"/></div>

* Next

    <div align="center"><img src="./img/newProject3.png"/></div>

* Next

    <div align="center"><img src="./img/newProject4.png"/></div>

* Finish

    <div align="center"><img src="./img/newProject5.png"/></div>

* 选择打开方式->删除build标签中的内容

    <div align="center"><img src="./img/删除build标签中的内容.png"/></div>

* 在main下新建java和resources文件夹、在src下新建test文件夹、再在test下建java文件夹

    <div align="center"><img src="./img/在main下新建java和resources文件夹_在src下新建test文件夹_再在test下建java文件夹.png"/></div>

* 在main下建java文件夹
    
    <div align="center"><img src="./img/java.png"/></div>

* 在main下建resources文件夹

    <div align="center"><img src="./img/resources.png"/></div>
    
*  在src下新建test文件夹、再在test下建java文件夹

    <div align="center"><img src="./img/test_java.png"/></div>

* web_app

    <div align="center"><img src="./img/web_app.png"/></div>
    
### 二、配置tomcat    
    
* Run/Debug Configurations

     <div align="center"><img src="./img/run.png"/></div>
    
* 点击Templates
    
     <div align="center"><img src="./img/tomcat1.png"/></div>
    
* 选择模板
    
     <div align="center"><img src="./img/tomcat2.png"/></div>
    
* 设置Deployment
     
     <div align="center"><img src="./img/tomcat3.png"/></div>
    
* 选择web样式
     
     <div align="center"><img src="./img/tomcat4.png"/></div>
    
* 修改查看路径

     <div align="center"><img src="./img/tomcat5.png"/></div>
    
* 添加tomcat模板到项目
    
     <div align="center"><img src="./img/tomcat6.png"/></div>
    
* 修改web版本
    
     <div align="center"><img src="./img/tomcat7.png"/></div>
    
* 将web版本改为3.1
     
     <div align="center"><img src="./img/tomcat8.png"/></div>
    
* 在浏览器输入http://localhost:8080/web_app/
     
     <div align="center"><img src="./img/tomcat9.png"/></div>    
    
    
### 三、普通项目

* 导入servletjar包

        <dependency>
          <groupId>javax.servlet</groupId>
          <artifactId>javax.servlet-api</artifactId>
          <version>3.1.0</version>
          <scope>provided</scope>
        </dependency>

* HelloServlet


        package com.edu;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

        @WebServlet("/Hello")
        public class HelloServlet extends HttpServlet {
            protected void doPost(HttpServletRequest request,
                                  HttpServletResponse response)
                    throws ServletException, IOException {
                System.out.println("================");
            }

            protected void doGet(HttpServletRequest request,
                                 HttpServletResponse response)
                    throws ServletException, IOException {
                System.out.println("================");
            }
        }

    
* 在浏览器输入http://localhost:8080/springmvc_demo/Hello    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
