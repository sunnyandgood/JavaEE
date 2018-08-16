# SpringMVC_返回json

### 一、环境准备

* 导入jar

      <dependency>
          <groupId>com.fasterxml.jackson.core</groupId>
          <artifactId>jackson-databind</artifactId>
          <version>2.9.4</version>
      </dependency>

* 在需要返回json的controller方法上直接加注解@ResponseBody

  >eg:
  
      @ResponseBody
      @RequestMapping("/testJson")
      public List toJson() {
          Map<String,Object> map = new HashMap<String, Object>();
          List list = new ArrayList<>();
          map.put("hello", "123");
          list.add(map);    
          return list;
      }  

### 二、实例

* 1、直接返回

    * StudentController.java

          @Controller
          @RequestMapping("/student")
          public class StudentController {
              @RequestMapping("/Json1")
              @ResponseBody
              public String testJson1(){
                  return "hello";
              }
          }

    * 测试

      >url

          http://localhost:8080/spring_mvc_demo2/student/Json1

      >输出

          hello

* 2、返回自定义类型

    * StudentController.java

          @Controller
          @RequestMapping("/student")
          public class StudentController {
              @RequestMapping("/Json2")
              @ResponseBody
              public Student testJson2(){
                  Student student = new Student();
                  student.setName("小明");
                  student.setGander("男");
                  student.setId(2);
                  return student;
              }
          }

    * 测试

      >url

          http://localhost:8080/spring_mvc_demo2/student/Json2

      >输出

          {"id":2,"name":"小明","birthday":null,"gander":"男","classroom":null}


* 3、返回list

    * StudentController.java

          @Controller
          @RequestMapping("/student")
          public class StudentController {
              @ResponseBody
              @RequestMapping("/testJson")
              public List testJson() {
                  Map<String,Object> map = new HashMap<String, Object>();
                  List list = new ArrayList<>();
                  map.put("hello", "123");
                  list.add(map);
                  return list;
              }
          }

    * 测试

      >url

          http://localhost:8080/spring_mvc_demo2/student/testJson

      >输出

          [{"hello":"123"}]




















