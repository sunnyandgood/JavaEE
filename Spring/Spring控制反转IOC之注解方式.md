# Spring控制反转IOC之注解方式

### 一、Spring能够从classpath中自动扫描到bean

* 注解包括：

    * @Component :除了下面的都拿它标识（组件）

    * @Respository ：持久层

    * @Service :业务层

    * @Controller :控制层

* 对于扫描到的bean，默认id就是类名首字母小写，当然我们也可以通过value属性指定bean
    
    >eg
    
      @Component//默认id是studentDao
      StudentDao
      
      @Component(value = "stu")//指定id为stu
      StudentDao

* 当在类上使用了特定注解后，还需要在配置文件中添加声明：

   >eg

      <!-- base-package表示需要扫描的包及子包 -->
      <context:component-scan base-package="com.edu.bean"></context:component-scan>

   * 子节点： 可以有多个(此节点有一个属性type: type的取值annotation(注解) 和assignable(具体接口或类))
   
      * `<context:include-filter>` 包含特例
      
      * `<context:exclude-filter>` 不包含特例
      
      >eg:annotation
         
        ----------- exclude-filter-------------- 
         
         <context:component-scan base-package="com.edu.bean">
          <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Component"/>
         </context:component-scan>
       
        ----------- include-filter-------------- 
       
         <context:component-scan base-package="com.edu.bean" use-default-filters="false">
          <context:include-filter type="annotation" expression="org.springframework.stereotype.Component"/>
         </context:component-scan>
       
      >eg:assignable
      
        ----------- exclude-filter-------------- 
         
          <context:component-scan base-package="com.edu.bean">
               <context:exclude-filter type="assignable" expression="com.edu.bean.Classroom"/>
          </context:component-scan>
       
        ----------- include-filter-------------- 
       
          <context:component-scan base-package="com.edu.bean" use-default-filters="false">
              <context:include-filter type="assignable" expression="com.edu.bean.Student"/>
          </context:component-scan>       

### 二、环境配置

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>4.3.14.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>4.3.14.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.3.14.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>4.3.14.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.18</version>
            <scope>provided</scope>
        </dependency>


### 三、应用

* 1、@Component（组件）的使用

   * 1>javabean（Student类）
   
         package com.edu.bean;

         import lombok.Data;
         import org.springframework.stereotype.Component;

         @Data
         @Component//默认id是student
         public class Student {
             private Integer id;
             private String name;
             private String gender;
         }

   * 2>xml配置
   
         <context:component-scan base-package="com.edu.bean"/>
   
   * 3>测试
   
          @Test
          public void test1(){
              //1.创建ioc容器对象：
              ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
              
              //通过id获得Student实例；com.edu.bean.Student的默认id是student
              Student student = (Student) applicationContext.getBean("student");
              
              //通过类名获得Student实例
              Student student2 = applicationContext.getBean(Student.class);
              
              System.out.println(student);
          }   
   
   * 4>拓展
   
      * 指定id
      
            @Component(value = "stu")
            或
            @Component("stu")
   
      * @Component、@Respository、@Service、@Controller可以混用
      
            //@Component//默认id是student
            //@Component(value = "stu")//指定id为stu
            //@Component("stu")//指定id为stu
            @Service//默认id是student
            //@Service(value = "aa")//指定id为aa
            //@Service("aa")//指定id为aa
            public class Student {
                private Integer id;
                private String name;
                private String gender;
            }

 * 2、`<context:include-filter> 包含特例`与`<context:exclude-filter> 不包含特例`的使用
  
   * javabean
   
      >Student类
      
         package com.edu.bean;

         import lombok.Data;
         import org.springframework.stereotype.Component;

         @Data
         @Component
         public class Student {
             private Integer id;
             private String name;
             private String gender;
         }
         
      >Classroom类

         package com.edu.bean;

         import lombok.Data;
         import org.springframework.stereotype.Component;

         @Data
         @Component
         public class Classroom {
             private Integer id;
             private String name;
         }

* 2、xml配置

   * 1>xml配置之`<context:include-filter>`
   
          <context:component-scan base-package="com.edu.bean">
              <context:exclude-filter type="assignable" expression="com.edu.bean.Classroom"/>
          </context:component-scan>   
    
   * 2>xml配置之`<context:exclude-filter>`
   
          <context:component-scan base-package="com.edu.bean" use-default-filters="false">
              <context:include-filter type="assignable" expression="com.edu.bean.Student"/>
          </context:component-scan>   

* 3、测试

       @Test
       public void testStudent(){
           ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
           Student student = (Student)applicationContext.getBean("student");
           System.out.println(student);
       }

       @Test
       public void testStudent(){
           ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
           Student student = (Student)applicationContext.getBean("student");
           System.out.println(student);
       }












































