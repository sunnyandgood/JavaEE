# SpringMVC_重定向

* webapp/index.jsp

      <html>
      <body>
      <h2>Hello World!</h2>
      </body>
      </html>

### 一、redirect（页面url显示为跳转目的页面的地址）

* ValueController.java

      @Controller
      @RequestMapping("/value")
      public class ValueController {
            @RequestMapping("/redirect")
            public String testRedirect(){
                return "redirect:/index.jsp";//页面url显示为index.jsp的地址
            }
            
            @RequestMapping("/modelredirect")
            public ModelAndView testModelAndViewDispatcher(ModelAndView modelAndView){
                modelAndView.setViewName("redirect:/index.jsp");
                return modelAndView;
            }
      }

* 测试：

        http://localhost:8080/spring_mvc_demo2/value/redirect
        
        http://localhost:8080/spring_mvc_demo2/value/forward
    


### 二、forward（页面url不跳转）


* ValueController.java

      @Controller
      @RequestMapping("/value")
      public class ValueController {
            @RequestMapping("/forward")
            public String testForward(){
                return "forward:/index.jsp";//页面url不跳转
            }
            
            @RequestMapping("/modelforward")
            public ModelAndView testModelAndViewDispatcher2(ModelAndView modelAndView){
                modelAndView.setViewName("forward:/index.jsp");
                return modelAndView;
            }
      }

* 测试：

      http://localhost:8080/spring_mvc_demo2/value/modelredirect
      
      http://localhost:8080/spring_mvc_demo2/value/modelforward











