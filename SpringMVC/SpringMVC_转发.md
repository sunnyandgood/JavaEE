# SpringMVC_转发

### 一、转发：reward:

* 之前我们直接return其实就是转发 那么我们如何传参?

* 传属性值

     * 原生api方式
     
     * springmvc推荐方式: Map|Model|ModelMap
     
     * springmvc推荐方式: 1.ModelAndView (模型+视图)
     
* 推荐使用map 或者ModelAndView方式，而且以上方式属性都是放在了request域中     

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




































    
    
