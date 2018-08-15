# SpringMVC_jsr303

### 一、问题来源

* 表单前端验证我们有 jquery-validate,但是一旦用户禁用js后，前端表单验证将失效

* 我们需要后端验证，java有一个规范叫Bean validation(jar303),官方参考实现是Hibernate Validator,此实现和Hibernate ORM没有任何关系

### 二、解决方案

* [官方参考文档](https://www.ibm.com/developerworks/cn/java/j-lo-jsr303/index.html)

* Bean Validation 中的 constraint

    * 表 1: Bean Validation 中内置的 constraint
    
        <table>
           <tr>
              <td>Constraint</td>
              <td>详细信息</td>
           </tr>
           <tr>
              <td>@Null</td>
              <td>被注释的元素必须为 null</td>
           </tr>
           <tr>
              <td>@NotNull</td>
              <td>被注释的元素必须不为 null</td>
           </tr>
           <tr>
              <td>@AssertTrue</td>
              <td>被注释的元素必须为 true</td>
           </tr>
           <tr>
              <td>@AssertFalse</td>
              <td>被注释的元素必须为 false</td>
           </tr>
           <tr>
              <td>@Min(value)</td>
              <td>被注释的元素必须是一个数字，其值必须大于等于指定的最小值</td>
           </tr>
           <tr>
              <td>@Max(value)</td>
              <td>被注释的元素必须是一个数字，其值必须小于等于指定的最大值</td>
           </tr>
           <tr>
              <td>@DecimalMin(value)</td>
              <td>被注释的元素必须是一个数字，其值必须大于等于指定的最小值</td>
           </tr>
           <tr>
              <td>@DecimalMax(value)</td>
              <td>被注释的元素必须是一个数字，其值必须小于等于指定的最大值</td>
           </tr>
           <tr>
              <td>@Size(max, min)</td>
              <td>被注释的元素的大小必须在指定的范围内</td>
           </tr>
           <tr>
              <td>@Digits (integer, fraction)</td>
              <td>被注释的元素必须是一个数字，其值必须在可接受的范围内</td>
           </tr>
           <tr>
              <td>@Past</td>
              <td>被注释的元素必须是一个过去的日期</td>
           </tr>
           <tr>
              <td>@Future</td>
              <td>被注释的元素必须是一个将来的日期</td>
           </tr>
           <tr>
              <td>@Pattern(value)</td>
              <td>被注释的元素必须符合指定的正则表达式</td>
           </tr>
        </table>    

    * 表 2: Hibernate Validator 附加的 constraint

        <table>
           <tr>
              <td>Constraint</td>
              <td>详细信息</td>
           </tr>
           <tr>
              <td>@Email</td>
              <td>被注释的元素必须是电子邮箱地址</td>
           </tr>
           <tr>
              <td>@Length</td>
              <td>被注释的字符串的大小必须在指定的范围内</td>
           </tr>
           <tr>
              <td>@NotEmpty</td>
              <td>被注释的字符串的必须非空</td>
           </tr>
           <tr>
              <td>@Range</td>
              <td>被注释的元素必须在合适的范围内</td>
           </tr>
        </table>

### 三、使用方法：

* 1、导入jar：

      <dependency>
          <groupId>org.hibernate</groupId>
          <artifactId>hibernate-validator</artifactId>
          <version>5.4.1.Final</version>
      </dependency>xml

* 2、在要验证的bean上加相应的注解：

      public class User {
          private Integer id;
          
          @NotEmpty(message = "姓名不能为空")
          private String name;
          
          @Pattern(regexp = "男|女", message = "必须是男或者女")
          private String gender;
          
          @Valid
          private ClassRoom classRoom;

          @DateTimeFormat(pattern = "yyyy-MM-dd")
          @NotNull(message = "生日不能为空")
          @Past(message = "必须是过去的时间")
          private Date birthday;
      }

* 3、然后在在需要验证的参数前加 @Valid

      public String save(@Valid User user,BindingResult result) {
      
      }

* 4、验证的错误信息将放到result中,下面输出错误信息
    
      if(result.getErrorCount()>0) {
          for (FieldError fieldError  : result.getFieldErrors()) {
              System.out.println(fieldError.getField()+"===="+fieldError.getDefaultMessage());
          }
      }

  * 需要注意的是，因为参数可以验证多个，每个参数的错误会放在各自的result，所以它们应该紧挨成对出现。


### 四、实例

* Student.java

package com.edu.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class Student {
    private Integer id;

    @NotEmpty(message = "姓名不能为空")
    private String name;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "生日不能为空")
    @Past(message = "必须是过去时间")
    private Date birthday;

    @Pattern(regexp = "男|女",message = "必须是男或者女")
    private String gander;

    @Valid
    private Classroom classroom;
}



* Classroomjava

      package com.edu.bean;

      import lombok.Data;
      import org.hibernate.validator.constraints.NotEmpty;

      @Data
      public class Classroom {
          private Integer id;
          private String name;
      }

* webapp/input.jsp

      <%--
        Created by IntelliJ IDEA.
        User: sunny
        Date: 2018/8/13
        Time: 10:17
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <html>
      <head>
          <title>Title</title>
      </head>
      <body>
          <form action="${pageContext.request.contextPath}/student/add">
              姓名：<input type="text" name="name"/>
              生日：<input type="text" name="birthday"/>输入格式：yyyy-MM-dd
              性别：<input type="text" name="gander"/>
              班级：<input type="text" name="classroom.name"/>
              <input type="submit" value="提交" />
          </form>
      </body>
      </html>


* StudentController.java

      package com.edu.controller;

      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.RequestMapping;

      @Controller
      @RequestMapping("/student")
      public class StudentController {

          /**
           * 1. 使用@RequestMapping注解映射请求的url
           * 2. 返回值会通过视图解析器解析为实际的物理视图
           * prefix+ return值 + suffix 得到实际的物理视图，然后转发
           * @return
           */
            @RequestMapping("/add")
            public String add(Student student){
                System.out.println(student);
                return "success";
            }
      }

* 在/WEB-INF/views下新建success.jsp

   * 然后访问http://localhost:8080/springmvc_demo/input.jsp 填值后提交即可跳转到 `/WEB-INF/views/success.jsp`页面



































