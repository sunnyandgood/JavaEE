# SpringMVC_RequestMapping注解

### 一、通配符支持

   >eg

    @RequestMapping("/aa/*/cc") --请求地址可以是/aa/xxx/cc
    
    @RequestMapping(value = "/hello/*",method = RequestMethod.GET) --请求地址可以是/hello/XXX

### 二、@PathVariable

* @RequestMapping和@PathVariable结合用于获取pathinfo

      @RequestMapping("testvariable/{id}")
      public String testPathVariable(@PathVariable("id")int id) {
          System.out.println(id);
          return "success";
      }

### 三、直接访问：

* 标准工程项目jsp页面都要放入WEB-INF下，是不能直接访问的，我们可以配一个简单的路径来直接访问

      <mvc:view-controller path="/aaa" view-name="success"/>

* 但是写上之后，我们发现上面的 @RequestMapping注解全部失效：所以还得再加一个

      <mvc:annotation-driven/>


















