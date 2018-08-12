# SpringMVC_RequestMapping注解之属性

### RequestMapping注解的属性解析

* 拥有属性：（value、method、params、headers）

    * value(默认即为value)//请求路径

    * method//请求方式
    
         * RequestMethod.GET（默认值）
         
         * RequestMethod.POST
         
         * RequestMethod.DELETE
         
         * RequestMethod.HEAD
         
         * RequestMethod.OPTIONS
         
         * RequestMethod.PATCH
         
         * RequestMethod.PUT
         
         * RequestMethod.TRACE

    * params//请求须带的参数
    
      >eg:
      
          params = {"name","age=2"})//请求后跟name和age参数，且age=2

    * headers
