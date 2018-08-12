# SpringMVC_RequestMapping注解之属性

### RequestMapping注解的属性解析

* 拥有属性：（value、method、params、headers）

    * value(默认即为value)//请求路径
    
      >eg:
      
          @RequestMapping("/hello")
          @RequestMapping(value = "/hello")
          
          //请求方式：http://localhost:8080/springmvc_demo/hello

    * method//请求方式
    
         * RequestMethod.GET（默认值）
         
         * RequestMethod.POST
         
         * RequestMethod.DELETE
         
         * RequestMethod.HEAD
         
         * RequestMethod.OPTIONS
         
         * RequestMethod.PATCH
         
         * RequestMethod.PUT
         
         * RequestMethod.TRACE
      
      >eg:
      
          @RequestMapping(value = "/hello")
          @RequestMapping(value = "/hello",method = RequestMethod.GET)
          @RequestMapping(value = "/hello",method = RequestMethod.POST)
          
          //请求方式：http://localhost:8080/springmvc_demo/hello

    * params//请求须带的参数
    
      >eg:
      
          params = {"name","age=2"})//请求后跟name和age参数，且age=2(name的值随意，age必须为2)
          @RequestMapping(value = "/hello",method = RequestMethod.GET,params = {"name","age=2"})
          
          //请求方式：http://localhost:8080/springmvc_demo/hello?name=asd&age=2

    * headers
    
      >eg:
      
            //可以访问，因为浏览器会自己传Connection=keep-alive请求头
            @RequestMapping(value = "/hello",method = RequestMethod.GET,
                 params = {"name","age=2"},headers = "Connection=keep-alive")
            
            //不可以访问，因为浏览器没有Connection=keep-alive2请求头，需要直接封装请求头
            @RequestMapping(value = "/hello",method = RequestMethod.GET,
                 params = {"name","age=2"},headers = "Connection=keep-alive2")
            
            
            //请求方式：http://localhost:8080/springmvc_demo/hello?name=asd&age=2

* 方法限制(method)

  >eg：

      @RequestMapping(value="/hello",method=RequestMethod.GET) --请求地址是/hello，而且必须是get请求

      @RequestMapping(value="/hello",method=RequestMethod.POST) --请求地址是/hello，而且必须是post请求

* 参数限制|请求头限制(了解)：

  >eg:
  
      @RequestMapping(value = "/hello",params = {"name=1","age"},headers ="Connection=keep-alive" )




















