# SpringMVC_RequestMapping注解

### 一、通配符支持

   >eg

    @RequestMapping("/aa/*/cc") --请求地址可以是/aa/xxx/cc
    
    @RequestMapping(value = "/hello/*",method = RequestMethod.GET) --请求地址可以是/hello/XXX
    
    @RequestMapping(value = "/hello/*",method = RequestMethod.GET)//访问路径后跟任何字符
    public String run(){
        System.out.println("hello SpringMVC!");
        return "success";
    }
    //请求方式：http://localhost:8080/springmvc_demo/hello/xxx
    

### 二、@PathVariable

* @RequestMapping和@PathVariable结合用于获取pathinfo

      @RequestMapping("testvariable/{id}")//访问路径后跟数字
      public String testPathVariable(@PathVariable("id")int id) {
          System.out.println(id);
          return "success";
      }
      //请求方式：http://localhost:8080/springmvc_demo/testvariable/9
      
### 三、直接访问：（springmvc.xml）

* 标准工程项目jsp页面都要放入WEB-INF下，是不能直接访问的，我们可以配一个简单的路径来直接访问

      <mvc:view-controller path="/success" view-name="success"/>
      //请求方式：http://localhost:8080/springmvc_demo/success
      <mvc:view-controller path="/student_list" view-name="student_list"/>
      //请求方式：http://localhost:8080/springmvc_demo/student_list

* 但是写上之后，我们发现上面的 @RequestMapping注解全部失效：所以还得再加一个

      <mvc:annotation-driven/>


















