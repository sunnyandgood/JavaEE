# Spring之aop切面编程的注解实现

### 一、注解详解

* 前置通知-@Before：该注解标注的方法在业务模块代码执行之前执行，其不能阻止业务模块的执行，除非抛出异常；

* 后置通知-@After：该注解标注的方法在所有的Advice执行完成后执行，无论业务模块是否抛出异常，类似于finally的作用；

* 返回通知-@AfterReturning：该注解标注的方法在业务模块代码成功执行之后执行；

* 异常通知-@AfterThrowing：该注解标注的方法在业务模块抛出指定异常后执行；

* 环绕通知-@Around：该注解功能最为强大，其所标注的方法用于编写包裹业务模块执行的代码，其可以传入一个ProceedingJoinPoint用于调用业务模块的代码，无论是调用前逻辑还是调用后逻辑，都可以在该方法中编写，甚至其可以根据一定的条件而阻断业务模块的调用；

### 二、应用实例

* 1、加入jar包

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
            <version>1.18.2</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>4.3.13.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.1</version>
        </dependency>

* 2、配置文件开启包扫描和aop:`spring.xml`

      <context:component-scan base-package="com.edu"/>
      <aop:aspectj-autoproxy/>

* 3、详细代码实现

  >业务层接口：
  
      package com.edu.service;

      public interface StudentService {
          Integer add();
          Integer delete(Integer integer);
      }
      
  >业务层实现：
  
      package com.edu.service.impl;

      import com.edu.service.StudentService;

      @Service
      public class StudentServiceImpl implements StudentService {
          @Override
          public Integer add() {
              System.out.println("业务层执行add方法");
              if(1 == 1){
                  throw new RuntimeException();
              }
              return 1;
          }

          @Override
          public Integer delete(Integer integer) {
              System.out.println("业务层执行delete方法");
              return 1;
          }
      }
  
  >切面：
  
  ---------前置通知、后置通知、返回通知、异常通知------------------------------------------------

      package com.edu.aop;

      import org.aspectj.lang.JoinPoint;
      import org.aspectj.lang.annotation.*;
      import org.springframework.stereotype.Component;

      import java.util.Arrays;

      @Component
      @Aspect
      public class StudentAop {

          @Before("execution(* com..service.*.*(..))")
          //@Before("execution(int com.edu.service.StudentService.add())")
          public void before(JoinPoint joinPoint){
              System.out.println("===前置通知===" +
                "   方法名：" + joinPoint.getSignature().getName() +
                "   方法参数：" + Arrays.toString(joinPoint.getArgs()));
              //throw new RuntimeException();
          }

          @After("execution(* com..service.*.*(..))")
          public void after(JoinPoint joinPoint){
              System.out.println("===后置通知===" +
                      "  方法名：" + joinPoint.getSignature().getName() +
                      "  方法参数：" + Arrays.toString(joinPoint.getArgs()));
          }

          @AfterReturning(value = "execution(* com.edu.service.*.*(..))",returning = "ret")
          public void afterRetuning(JoinPoint joinPoint,Object ret){
              System.out.println("===方法正常返回后通知===" +
                      "  方法名：" + joinPoint.getSignature().getName() +
                      "  方法参数：" + Arrays.toString(joinPoint.getArgs()) +
                      "  返回值：" + ret);
          }

          @AfterThrowing(value = "execution(* com..service.*.*(..))",throwing = "excep")
          public void afterThrowing(JoinPoint joinPoint,Exception excep){
              System.out.println("===方法出现异常后执行===" +
                      "  方法名：" + joinPoint.getSignature().getName() +
                      "  方法参数：" + Arrays.toString(joinPoint.getArgs()) +
                      "  抛出的异常：" + excep);
          }
      }

  ---------环绕通知------------------------------------------------

      package com.edu.aop;

      import org.aspectj.lang.ProceedingJoinPoint;
      import org.aspectj.lang.annotation.Around;
      import org.springframework.stereotype.Component;

      @Component
      public class StudentAop {
          @Around("execution(* com..service.*.*(..))")
          public Object around(ProceedingJoinPoint point){
              System.out.println("===前置通知===");
              Object object = null;
              try {
                  object = point.proceed();
                  System.out.println("===返回通知===");
              } catch (Throwable throwable) {
                  System.out.println("===异常通知===");
                  //throwable.printStackTrace();
                  return object;
              }finally {
                  System.out.println("===后置通知===");
              }
              return object;
          }
      }

* 测试

      package com.edu;

      import com.edu.service.StudentService;
      import org.junit.Test;
      import org.springframework.context.ApplicationContext;
      import org.springframework.context.support.ClassPathXmlApplicationContext;

      public class StudentTest {
          @Test
          public void testAdd(){
              ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
              StudentService service = applicationContext.getBean(StudentService.class);
              System.out.println(service.add());
          }

          @Test
          public void testDelete(){
              ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
              StudentService service = applicationContext.getBean(StudentService.class);
              System.out.println(service.delete(1));
          }
      }

























