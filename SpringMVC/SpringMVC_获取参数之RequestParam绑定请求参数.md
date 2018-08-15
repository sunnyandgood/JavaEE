# SpringMVC_获取参数之RequestParam绑定请求参数

### 一、拥有属性：
  
* value
  
* required
  
* defaultValue


### 二、普通方式

* StudentController.java

      @Controller
      @RequestMapping("/student")
      public class StudentController {
          @RequestMapping("/testRequestParam")
          public String testRequestParam(String name,Integer age){
              System.out.println(name);
              System.out.println(age);
              return "success";
          }
      }    

* webapp/requestparam_input.jsp

      <%--
        Created by IntelliJ IDEA.
        User: sunny
        Date: 2018/8/13
        Time: 10:31
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <html>
      <head>
          <title>Title</title>
      </head>
      <body>
          <%--<form action="${pageContext.request.contextPath}/student/testRequestParam">--%>
          <form action="${pageContext.request.contextPath}/student/testRequestParam2">
              姓名：<input type="text" name="name"/>
              年龄：<input type="text" name="age"/>
              <input type="submit" value="提交" />
          </form>
      </body>
      </html>

* 测试

  * 在/WEB-INF/views下新建success.jsp

  * 然后访问http://localhost:8080/springmvc_demo/requestparam_input.jsp 填值后提交即可跳转到 /WEB-INF/views/success.jsp页面





























