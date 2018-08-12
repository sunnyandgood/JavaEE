# SpringMVC_rest风格

### 一、rest风格url

* rest风格url 有get(查询),post(新增)，put(修改)，delete(删除)

    >eg:

        /student/1 get --查询id是1的学生

        /student/1 put --修改id是1的学生信息

        /student/1 delete --删除id是1的学生

        /student/ -post --新增学生信息

### 二、我们可以配置一个过滤器HiddenHttpMethodFilter把post请求转换位put请求和delete请求

    <filter>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

* 然后在发post请求的时候多传一个参数 ：_method : put/delete

    * delete请求
        
         >eg：
            
            <form action="${pageContext.request.contextPath}/spring/student/2" method="post">
                <input type="hidden" name="_method" value="delete"/>
                <input type="submit" value="aaa"/>
            </form>


            @RequestMapping(value="/student/{id}",method=RequestMethod.DELETE)
            @ResponseBody    //如果delete或put请求报405需要加此注解
            public String studentDELETE(@PathVariable("id") int id) {
                System.out.println("delete"+id);
                return "success";
            }

    * put请求
    
        >eg:

            <form action="${pageContext.request.contextPath}/spring/student/2" method="post">
                <input type="hidden" name="_method" value="put"/>
                <input type="submit" value="aaa"/>
            </form>

            @RequestMapping(value="/student/{id}",method=RequestMethod.PUT)
            @ResponseBody //如果delete或put请求报405需要加此注解
            public String studentPUT(@PathVariable("id") int id) {
                System.out.println("put"+id);
                return "success";
            }
