# SpringMVC_获取参数之RequestParam绑定请求参数

### 一、拥有属性：
  
* value
  
* required
  
* defaultValue


### 二、普通方式（value默认值，requared默认为true）

* 参数值可以为空

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
          <form action="${pageContext.request.contextPath}/student/testRequestParam">
              姓名：<input type="text" name="name"/>
              年龄：<input type="text" name="age"/>
              <input type="submit" value="提交" />
          </form>
      </body>
      </html>

* 测试

  * 在/WEB-INF/views下新建success.jsp

  * 然后访问http://localhost:8080/springmvc_demo/requestparam_input.jsp 填值后提交即可跳转到 /WEB-INF/views/success.jsp页面


### 三、value指定，requared默认为true

* 参数值可以为空

* StudentController.java

      @Controller
      @RequestMapping("/student")
      public class StudentController {
          @RequestMapping("/testRequestParam")
          public String testRequestParam(@RequestParam("name") String name1,
                                         @RequestParam("age") Integer age1){
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
          <form action="${pageContext.request.contextPath}/student/testRequestParam">
              姓名：<input type="text" name="name"/>
              年龄：<input type="text" name="age"/>
              <input type="submit" value="提交" />
          </form>
      </body>
      </html>

* 测试

  * 在/WEB-INF/views下新建success.jsp

  * 然后访问http://localhost:8080/springmvc_demo/requestparam_input.jsp 填值后提交即可跳转到 /WEB-INF/views/success.jsp页面


### 四、value指定、requared=false（封装类）

* 参数值可以为空

* StudentController.java

      @Controller
      @RequestMapping("/student")
      public class StudentController {
          @RequestMapping("/testRequestParam")
          public String testRequestParam(@RequestParam(value = "name",required = false) String name1,
                                          @RequestParam(value = "age",required = false) Integer age1){
              System.out.println(name1);
              System.out.println(age1);
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
          <form action="${pageContext.request.contextPath}/student/testRequestParam">
              姓名：<input type="text" name="name"/>
              年龄：<input type="text" name="age"/>
              <input type="submit" value="提交" />
          </form>
      </body>
      </html>

* 测试

  * 在/WEB-INF/views下新建success.jsp

  * 然后访问http://localhost:8080/springmvc_demo/requestparam_input.jsp 填值后提交即可跳转到 /WEB-INF/views/success.jsp页面


### 五、value指定、requared=false（原生数据类型）

* 这样写，参数值必须，不然的话就报错。如果参数值不是必须，则此写法不支持基本变量。

* StudentController.java

      @Controller
      @RequestMapping("/student")
      public class StudentController {
          @RequestMapping("/testRequestParam")
          @RequestMapping("/testRequestParam2")
          public String testRequestParam(@RequestParam(value = "name",required = false) String name1,
                                          @RequestParam(value = "age",required = false) int age1){
                                          //age不能为空，必填
              System.out.println(name1);
              System.out.println(age1);
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
          <form action="${pageContext.request.contextPath}/student/testRequestParam">
              姓名：<input type="text" name="name"/>
              年龄：<input type="text" name="age"/>
              <input type="submit" value="提交" />
          </form>
      </body>
      </html>

* 测试

  * 在/WEB-INF/views下新建success.jsp

  * 然后访问http://localhost:8080/springmvc_demo/requestparam_input.jsp 填值后提交即可跳转到 /WEB-INF/views/success.jsp页面


### 六、value指定、requared=false（原生数据类型）

* 参数给默认值：此时不需要再写required=false

* StudentController.java

      @Controller
      @RequestMapping("/student")
      public class StudentController {
          @RequestMapping("/testRequestParam1")
          public String testRequestParam1(@RequestParam("name") String name1,
                                          @RequestParam(value = "age",required = false,
                                          defaultValue = "20") Integer age1){
              System.out.println(name1);
              System.out.println(age1);
              return "success";
          }
          
          @RequestMapping("/testRequestParam2")
          public String testRequestParam2(@RequestParam("name") String name1,
                                          @RequestParam(value = "age",
                                          defaultValue = "20") Integer age1){
              System.out.println(name1);
              System.out.println(age1);
              return "success";
          }
          
          @RequestMapping("/testRequestParam5")
          public String testRequestParam5(@RequestParam("name") String name1,
                                          @RequestParam(value = "age",
                                          defaultValue = "20") int age1){
              System.out.println(name1);
              System.out.println(age1);
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
          <%--<form action="${pageContext.request.contextPath}/student/testRequestParam1">--%>
          <%--<form action="${pageContext.request.contextPath}/student/testRequestParam2">--%>
          <form action="${pageContext.request.contextPath}/student/testRequestParam3">
              姓名：<input type="text" name="name"/>
              年龄：<input type="text" name="age"/>
              <input type="submit" value="提交" />
          </form>
      </body>
      </html>

* 测试

  * 在/WEB-INF/views下新建success.jsp

  * 然后访问http://localhost:8080/springmvc_demo/requestparam_input.jsp 填值后提交即可跳转到 /WEB-INF/views/success.jsp页面


































