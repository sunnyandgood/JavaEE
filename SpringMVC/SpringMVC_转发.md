# SpringMVC_转发

### 一、转发：reward:

* 之前我们直接return其实就是转发 那么我们如何传参?

* 传属性值

     * 原生api方式
     
     * springmvc推荐方式: Map|Model|ModelMap
     
     * springmvc推荐方式: 1.ModelAndView (模型+视图)
     
* 推荐使用map 或者ModelAndView方式，而且以上方式属性都是放在了request域中     

* webapp/ceshi.jsp

        <%--
          Created by IntelliJ IDEA.
          User: sunny
          Date: 2018/8/13
          Time: 13:00
          To change this template use File | Settings | File Templates.
        --%>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <html>
        <head>
            <title>Title</title>
        </head>
        <body>
            request----- ${requestScope.name}--${requestScope.age}--${requestScope.gender}
            <br>
            session----- ${sessionScope.name}--${sessionScope.age}--${sessionScope.gender}
        </body>
        </html>


### 二、原生api方式

* ValueController.java

      @Controller
      @RequestMapping("/value")
      public class ValueController {
          @RequestMapping("/reward1")
          public String testReward1(HttpServletRequest request){
              request.setAttribute("name","小米");
              return "ceshi";
          }
      }

* 测试：

        http://localhost:8080/spring_mvc_demo2/value/reward1

* 输出：

        request----- 小米---- 
        session----- ----


































    
    
