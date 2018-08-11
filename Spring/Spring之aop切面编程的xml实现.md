# Spring之aop切面编程的xml实现

* 1、加入jar包

      jar包同注解的

* 2、详细代码实现

  >业务层接口：
  
      package com.edu.service;

      public interface StudentService {
          Integer add();
          Integer delete(Integer integer);
      }  
  
  >业务层实现：
  
      package com.edu.service.impl;

      import com.edu.service.StudentService;

      public class StudentServiceImpl implements StudentService {
          @Override
          public Integer add() {
              System.out.println("业务层执行add方法");
              if(1 == 2){
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
  
  >切面
  
      package com.edu.aop;

      import org.aspectj.lang.JoinPoint;
      import org.aspectj.lang.ProceedingJoinPoint;

      import java.util.Arrays;

      //@Component
      public class StudentAop2 {
          public void before(JoinPoint joinPoint) {
              System.out.println("学生增加之前操作--方法名:"
                      + joinPoint.getSignature().getName() +
                      "方法参数:"+Arrays.toString(joinPoint.getArgs()));
          }
          public void afterReturning(JoinPoint joinPoint,Object r) {
              System.out.println("正常返回后执行--方法名:" +
                      joinPoint.getSignature().getName()+"方法参数:" +
                      Arrays.toString(joinPoint.getArgs()) +
                      "==返回值"+r);
          }
          public void afterThrowing(JoinPoint joinPoint,Exception e) {
              System.out.println("出现异常时执行--方法名:" +
                      joinPoint.getSignature().getName()+"方法参数:" +
                      Arrays.toString(joinPoint.getArgs()) +
                      "异常"+e);
          }
          public void after(JoinPoint joinPoint) {
              System.out.println("后置执行--方法名:" +
                      joinPoint.getSignature().getName()+"方法参数:" +
                      Arrays.toString(joinPoint.getArgs()));
          }
          public Object around(ProceedingJoinPoint pjp) throws Throwable {
              String methodName = pjp.getSignature().getName();
              System.out.println("这是"+methodName+"方法的前置");
              Object result=null;
              try {
                  result = pjp.proceed();
                  System.out.println("这是"+methodName+"方法的返回通知");
              } catch (Throwable e) {
                  System.out.println("这是"+methodName+"方法的异常通知");
                  throw e;
              }finally {
                  System.out.println("这是"+methodName+"方法的后置通知");
              }
              return result;
          }
      }
  

* 3、配置文件开启包扫描`spring.xml`

      <context:component-scan base-package="com.edu"/>
      
      <bean id="studentService" class="com.edu.service.impl.StudentServiceImpl"/>
      <bean id="studentAop2" class="com.edu.aop.StudentAop2"/>
      <aop:config>
          <aop:pointcut id="pointcut" expression="execution(* com..service.*.*(..))"/>
          <aop:aspect ref="studentAop2">
              <aop:before method="before" pointcut="execution(* com..service.*.*(..))"/>
              <aop:after method="after"  pointcut-ref="pointcut"/>
              <aop:after-returning method="afterReturning" pointcut-ref="pointcut" returning="r"/>
              <aop:after-throwing method="afterThrowing" pointcut-ref="pointcut" throwing="e"/>
              <!--<aop:around method="around" pointcut-ref="pointcut"/>-->
          </aop:aspect>
      </aop:config>

* 4、测试

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





















