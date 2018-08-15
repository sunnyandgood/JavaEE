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
            request----- ${requestScope.name}
            <br>
            session----- ${sessionScope.name}
        </body>
        </html>


### 二、原生api方式

* 1、不传参

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

            request----- 小米
            session----- 

* 2、传参

    * ValueController.java

          @Controller
          @RequestMapping("/value")
          public class ValueController {
              @RequestMapping("/reward2")
              public String testReward2(String name, HttpServletRequest request){
                  request.setAttribute("name", name);
                  return "ceshi";
              }
          }

    * 测试：

            http://localhost:8080/spring_mvc_demo2/value/reward2?name=小雅

    * 输出：

            request----- 小雅
            session----- 

### 三、springmvc推荐方式之Map

* ValueController.java

      @Controller
      @RequestMapping("/value")
      public class ValueController {
            @RequestMapping("/map")
            public String testMap(String name, Map<String,Object> map){
                map.put("name","小红");
                return "ceshi";
            } 
      }

* 测试：

        http://localhost:8080/spring_mvc_demo2/value/map
    
* 输出：

        request----- 小红
        session----- 小红
      
### 四、springmvc推荐方式之Model

* ValueController.java

      @Controller
      @RequestMapping("/value")
      public class ValueController {
            @RequestMapping("/model")
            public String testModel(String name, Model model){
                model.addAttribute("name",name);
                return "ceshi";
            }
      }

* 测试：

        http://localhost:8080/spring_mvc_demo2/value/model?name=小雷
    
* 输出：

        request----- 小雷
        session----- 小雷

### 五、springmvc推荐方式之ModelMap

* ValueController.java

      @Controller
      @RequestMapping("/value")
      public class ValueController {
            @RequestMapping("/modelMap")
            public String testModelMap(String name, ModelMap modelMap){
                modelMap.addAttribute("name",name);
                return "ceshi";
            }
      }

* 测试：

        http://localhost:8080/spring_mvc_demo2/value/modelMap?name=小花
    
* 输出：

        request----- 小花 
        session----- 小花

### 六、springmvc推荐方式之ModelAndView

* ValueController.java

      @Controller
      @RequestMapping("/value")
      public class ValueController {
            @RequestMapping("/modelAndView1")
            public ModelAndView testModelAndView1(String name,ModelAndView modelAndView){
                modelAndView.addObject("name",name);
                modelAndView.setViewName("ceshi");
                return modelAndView;
            }

            @RequestMapping("/modelAndView2")
            public ModelAndView testModelAndView2(String name){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("name",name);
                modelAndView.setViewName("ceshi");
                return modelAndView;
            }

            @RequestMapping("/modelAndView3")
            public ModelAndView testModelAndView3(String name){
                ModelAndView modelAndView = new ModelAndView("ceshi");
                modelAndView.addObject("name",name);
                return modelAndView;
            }
      }

* 测试：

        http://localhost:8080/spring_mvc_demo1/value/modelAndView1?name=小明
        
        http://localhost:8080/spring_mvc_demo2/value/modelAndView1?name=小风
        
        http://localhost:8080/spring_mvc_demo3/value/modelAndView1?name=小王
    
* 输出：

        request----- 小明
        session----- 小明
        
        request----- 小风
        session----- 小风
        
        request----- 小王
        session----- 小王

































    
    
