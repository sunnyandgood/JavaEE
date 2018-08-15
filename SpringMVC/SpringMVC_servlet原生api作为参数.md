# SpringMVC_servlet原生api作为参数

### 一、MVC的handler方法可以接受ServletAPI类型作为参数

* ServletAPI

    * HttpServletRequest

    * HttpServletRespons

    * HttpSession

    * InputStream

    * OutputStream

    * Reader

    * Outer

     **参数上直接写，就可以直接使用**

* 实例

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
                request----- ${requestScope.name}
                <br>
                session----- ${sessionScope.name}
            </body>
            </html>

    * ValueController.java

          @Controller
          @RequestMapping("/value")
          public class ValueController {
                @RequestMapping("/session")
                public String testHttpSession(String name, Map map, HttpSession session){
                    map.put("name",name);
                    session.setAttribute("name",name);
                    return "ceshi";
                }
          }

    * 测试：

            http://localhost:8080/spring_mvc_demo2/value/session?name=小红

    * 输出：

            request----- 小红
            session----- 小红

### 二、@SessionAttribute

* @SessionAttribute使用简介

    * @SessionAttribute只能放在类上

    * 它有两个属性 value type

        * @SessionAttributes(value= {"gender"},types= {String.class})

* 实例

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


    * ValueController.java

          @SessionAttributes
          //@SessionAttributes(types = {String.class})
          //@SessionAttributes(value= {"gender","name","age"},types= {String.class,String.class,Integer.class})
          @RequestMapping("/value")
          @Controller
          public class ValueController {
              @RequestMapping("/sessionAttribute")
              public String testSessionAttribute(String name,Map map){
                  map.put("name",name);
                  map.put("gender","男");
                  map.put("age",20);
                  return "ceshi";
              }
          }

    * 测试：

            http://localhost:8080/spring_mvc_demo2/value/sessionAttribute?name=小培

    * 输出：
    
      * ` @SessionAttributes`
      
            request----- 小培--20--男 
            session----- ----      
      
      * `@SessionAttributes(types = {String.class})`
      
            request----- 小培--20--男 
            session----- 小培----男
      
      
      * `@SessionAttributes(value= {"gender","name","age"},types= {String.class,String.class,Integer.class})`
      
            request----- 小培--20--男 
            session----- 小培--20--男
      
      




