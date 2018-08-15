# SpringMVC_转发

### 一、转发：reward:

* 之前我们直接return其实就是转发 那么我们如何传参?

* 传属性值

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




































    
    
