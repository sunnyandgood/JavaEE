# SpringIOC注解方式之自动装配

### 一、自动装配

* 使用注解使ioc容器维护bean，但是bean之间的关联关系我们可以使用 `@Autowired` , 此注解可以写在**属性上，构造方法上，setter方法上** 

* spring容器会首先根据`变量名 = bean的id名`(此处id必须是手动指定的，默认的不行)去匹配，如果匹配不上再根据类型查找容器中相应的bean进行关联

  >eg:
  
      @Autowired
      private StudentDao studentDao;
      //首先比对id名和变量名是否相同，比对不上就选类名去填补。  

* 默认容器中必须有匹配bean，如果允许可以没有此类型的bean，那么可以@Autowired(required=false)

* 如果容器中按类型匹配有多个匹配类型，我们一般可以使用`@Qualifier`,后边必须指出所需id。

  >eg:


      @Autowired
      @Qualifier(value = "studentDaoImpl2")
      private StudentDao studentDao;
      //@Qualifier：指定所需的id
      
      //或：
      @Autowired
      @Qualifier("studentDAOImpl")
      private StudentDAO studentDAO;
      
      //或：
      @Autowired
      @Qualifier("studentDAOImpl")
      public void setStudentDAO(StudentDAO studentDAO) {
          this.studentDAO = studentDAO;
      }
      
      //或：
      @Autowired
      public void setStudentDAO(@Qualifier("studentDAOImpl")StudentDAO studentDAO) {
          this.studentDAO = studentDAO;
      }

### 二、应用

* dao层

  >`StudentDao`接口
  
      package com.edu.dao;

      public interface StudentDao {
          int insert();
      }
  
  >`StudentDaoImpl`类
  
      package com.edu.dao.impl;

      import com.edu.dao.StudentDao;
      import org.springframework.stereotype.Repository;

      //@Repository
      //@Repository(value = "studentDao")
      //@Repository("studentDao")
      @Repository //持久层
      public class StudentDaoImpl implements StudentDao {

          @Override
          public int insert() {
              System.out.println("增加一个学生");
              return 1;
          }
      }
  
  >`StudentDaoImpl2`类
  
      package com.edu.dao.impl;

      import com.edu.dao.StudentDao;
      import org.springframework.stereotype.Repository;

      @Repository
      public class StudentDaoImpl2 implements StudentDao {
          @Override
          public int insert() {
              System.out.println("又一个实现类：增加一个学生");
              return 1;
          }
      }
  

* service层

  >`StudentService`接口
  
      package com.edu.service;

      public interface StudentService {
          int insert();
      }
  
  >`StudentServiceImpl`类
  
      package com.edu.service.impl;

      import com.edu.dao.StudentDao;
      import com.edu.service.StudentService;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.beans.factory.annotation.Qualifier;
      import org.springframework.stereotype.Service;

      @Service//业务层
      public class StudentServiceImpl implements StudentService {

          @Autowired
          @Qualifier(value = "studentDaoImpl2")
          private StudentDao studentDao;

      //    @Autowired
      //    private StudentDao studentDaoImp;

      //    @Autowired
      //    private StudentDao studentDaoImpl2;

          @Override
          public int insert() {
              return studentDao.insert();
          }
      }
      
* 配置xml

      <context:component-scan base-package="com.edu"/>

* 测试

      package com.edu;

      import com.edu.service.StudentService;
      import com.edu.service.impl.StudentServiceImpl;
      import org.junit.Test;
      import org.springframework.context.ApplicationContext;
      import org.springframework.context.support.ClassPathXmlApplicationContext;

      public class AutoTest {
          @Test
          public void testAuto(){
              ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
              StudentService service = applicationContext.getBean(StudentServiceImpl.class);
              service.insert();
          }
      }

  
  
  
  
  
  
  












