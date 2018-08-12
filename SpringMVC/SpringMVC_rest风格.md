# SpringMVC_rest风格

rest风格url

rest风格url 有get(查询),post(新增)，put(修改)，delete(删除)

/student/1 get --查询id是1的学生

/student/1 put --修改id是1的学生信息

/student/1 delete --删除id是1的学生

/student/ -post --新增学生信息

我们可以配置一个过滤器HiddenHttpMethodFilter把post请求转换位put请求和delete请求
<filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>xml

然后在发post请求的时候多传一个参数 ：_method : put/delete

如： 

delete请求
<form action="${pageContext.request.contextPath}/spring/student/2" method="post">
    <input type="hidden" name="_method" value="delete"/>
    <input type="submit" value="aaa"/>
</form>jsp
@RequestMapping(value="/student/{id}",method=RequestMethod.DELETE)
    @ResponseBody    //如果delete或put请求报405需要加此注解
    public String studentDELETE(@PathVariable("id") int id) {
        System.out.println("delete"+id);
        return "success";
    }java

put请求
<form action="${pageContext.request.contextPath}/spring/student/2" method="post">
    <input type="hidden" name="_method" value="put"/>
    <input type="submit" value="aaa"/>
</form>jsp
@RequestMapping(value="/student/{id}",method=RequestMethod.PUT)
    @ResponseBody //如果delete或put请求报405需要加此注解
    public String studentPUT(@PathVariable("id") int id) {
        System.out.println("put"+id);
        return "success";
    }
