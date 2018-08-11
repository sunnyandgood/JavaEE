# Spring依赖注入DI

* bean的注入方式主要有以下三种：

    * 属性注入 (默认方式，最为常用)

    * 构造注入

    * 工厂方法注入 (不用)

### 一、属性注入：（javabean中得有无参构造方法）

    <!-- 配置bean -->
    <bean id="student" class="com.edu.bean.Student">
        <property name="name" value="小明"></property>
        <property name="gender" value="男"></property>
    </bean>

### 二、构造注入：（javabean里得有有参构造方法）

    <!-- 配置bean -->
    <bean class="com.edu.bean.Student">
        <constructor-arg name="name" value="小明"></constructor-arg>
        <constructor-arg name="gender" value="男"></constructor-arg>
    </bean>

* 如果javabean中属性是int，容器也会自动转换

      public class Student {
        private Integer id;
        private String name;
        private String gender;
      }    
    
      <!-- 配置bean -->
      <bean id="studentConstructor" class="com.edu.bean.Student">
        <constructor-arg name="id" value="1"/>
        <constructor-arg name="name" value="小明"/>
        <constructor-arg name="gender" value="男"/>
      </bean>

### 三、属性注入之常见方式

   >Classroom类
    
      public class ClassRoom {
          private int cid;
          private String name;
      }

   >Student类
    
      public class Student {
        private Integer id;
        private String name;
        private String gender;
        private Classroom classroom;
      }    

   >测试
   
      @Test
      public void test() {
         ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
         Student student = (Student)applicationContext.getBean("student");
         System.out.println(student);
      }

* 1、学生类中有另一类属性（学生类有个classroom属性）

    * 1>外部连接
    
          <bean id="classroom" class="com.edu.bean.Classroom">
              <property name="cid" value="1"/>
              <property name="name" value="一班"/>
          </bean>
          <bean id="studentClassroom1" class="com.edu.bean.Student">
              <property name="id" value="2"/>
              <property name="name" value="小华"/>
              <property name="gender" value="男"/>
              <property name="classroom" ref="classroom"/>
          </bean>    

    * 2>内部bean
    
          <bean id="studentClassroom2" class="com.edu.bean.Student">
              <property name="id" value="3"/>
              <property name="name" value="小花"/>
              <property name="gender" value="女"/>
              <property name="classroom">
                  <bean class="com.edu.bean.Classroom">
                      <property name="cid" value="3"/>
                      <property name="name" value="三班"/>
                  </bean>
              </property>
          </bean>







