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
    
      <bean id="studentConstructor" class="com.edu.bean.Student">
        <constructor-arg name="id" value="1"/>
        <constructor-arg name="name" value="小明"/>
        <constructor-arg name="gender" value="男"/>
      </bean>

### 三、属性注入之常见方式

* 1、Classroom类与Student类：

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

* 2、学生类中有另一类属性（学生类有个classroom属性）

    * 1>









