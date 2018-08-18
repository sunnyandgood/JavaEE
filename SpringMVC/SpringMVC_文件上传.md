# SpringMVC_文件上传

* 导入jar包

      <!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
      <dependency>
          <groupId>commons-fileupload</groupId>
          <artifactId>commons-fileupload</artifactId>
          <version>1.3.3</version>
      </dependency>

* springmvc.xml中配置

      <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
          <property name="defaultEncoding" value="utf-8"/>
          <property name="maxUploadSize" value="5000000"/>  <!--单位字节-->
      </bean>

### 一、小体验

* 表单

      <%--
        Created by IntelliJ IDEA.
        User: sunny
        Date: 2018/8/17
        Time: 20:23
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <html>
      <head>
          <title>文件上传</title>
      </head>
      <body>
          <form action="${ctx}/upload" method="post" enctype="multipart/form-data">
              <input type="file" name="file"/>
              <input type="submit" value="提交">
          </form>
      </html>

* UpLoadController.java

      package com.edu.controller;

      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.RequestMapping;
      import org.springframework.web.multipart.MultipartFile;


      @Controller
      public class UpLoadController {

          @RequestMapping("/upload")
          public String upload(MultipartFile file){
              System.out.println(file.getOriginalFilename());
              return "redirect:/hello.jsp";
          }
      }

### 二、文件上传

* 表单

      <%--
        Created by IntelliJ IDEA.
        User: sunny
        Date: 2018/8/17
        Time: 20:23
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <html>
      <head>
          <title>文件上传</title>
      </head>
      <body>
          <form action="${ctx}/upload" method="post" enctype="multipart/form-data">
              <input type="file" name="file"/>
              <input type="submit" value="提交">
          </form>
      </html>

* UpLoadController.java

      package com.edu.controller;

      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.RequestMapping;
      import org.springframework.web.multipart.MultipartFile;

      import javax.servlet.http.HttpServletRequest;
      import java.io.File;
      import java.io.IOException;


      @Controller
      public class UpLoadController {

          @RequestMapping("/upload")
          public String upload(MultipartFile file, HttpServletRequest request){
              //System.out.println(request.getServletContext().getRealPath("/"));
              String realPath = request.getServletContext().getRealPath("/resources/upload");
              File file1 = new File(realPath,file.getOriginalFilename());
              try {
                  file.transferTo(file1);
              } catch (IOException e) {
                  e.printStackTrace();
              }
              return "redirect:/hello.jsp";
          }
      }

### 三、改变文件名预防同名覆盖

* 表单

      <%--
        Created by IntelliJ IDEA.
        User: sunny
        Date: 2018/8/17
        Time: 20:23
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <html>
      <head>
          <title>文件上传</title>
      </head>
      <body>
          <form action="${ctx}/upload" method="post" enctype="multipart/form-data">
              <input type="file" name="file"/>
              <input type="submit" value="提交">
          </form>
      </html>

* UpLoadController.java

      package com.edu.controller;

      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.RequestMapping;
      import org.springframework.web.multipart.MultipartFile;

      import javax.servlet.http.HttpServletRequest;
      import java.io.File;
      import java.io.IOException;
      import java.util.UUID;

      @Controller
      public class UpLoadController {
          @RequestMapping("/upload")
          public String upload(MultipartFile file, HttpServletRequest request){
              //得到上传路径的硬盘路径
              String realPath = request.getServletContext().getRealPath("/resources/upload");

              //得到文件扩展名
              String oldFilename = file.getOriginalFilename();
              String ext = oldFilename.substring(oldFilename.indexOf("."));

              //新文件名
              String newFileName = UUID.randomUUID() + ext;

              System.out.println(newFileName);
              File file1 = new File(realPath,newFileName);
              try {
                  //存文件
                  file.transferTo(file1);
              } catch (IOException e) {
                  e.printStackTrace();
              }
              return "redirect:/hello.jsp";
          }
      }

### 四、ajax异步上传之单图显示：

* 使用插件：hfileuploadify(fileupload山寨版): [文件下载](https://github.com/ComputerEreop/Huploadify)










