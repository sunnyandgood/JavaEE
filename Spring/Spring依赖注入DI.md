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

* 1、学生类中有另一类属性（学生类有个classroom属性）
    
    * javabean
    
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

    * xml配置
    
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

   * 测试
   
         @Test
         public void testStudentClassroom() {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
            Student student = (Student)applicationContext.getBean("studentClassroom1");//studentClassroom2
            System.out.println(student);
         }

* 2、Classrom类中有list

    * javabean
    
       >Classroom类

            public class ClassRoom {
                private int cid;
                private String name;
                private List<Student> studentList;
            }

      >Student类
    
            public class Student {
              private Integer id;
              private String name;
              private String gender;
            }    

    * xml配置
    
          <bean id="classroomList" class="com.edu.bean.Classroom">
              <property name="cid" value="2"/>
              <property name="name" value="二班"/>
              <property name="studentList">
                  <list>
                      <ref bean="studentClassroom1"/>
                      <bean class="com.edu.bean.Student">
                          <property name="id" value="2"/>
                          <property name="name" value="小雷"/>
                          <property name="gender" value="男"/>
                          <property name="classroom">
                              <ref bean="classroom"/>
                          </property>
                      </bean>
                  </list>
              </property>
           </bean>
           
         * 此案例list中一个classroom来自外部，一个是内部bean 配置数组也是用list标签，而set集合使用set标签，基本使用一样。

    * 测试
   
            @Test
            public void testClassroomList() {
               ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
               Student student = (Student)applicationContext.getBean("classroomList");
               System.out.println(student);
            }

* 3、Classrom类中有array

    * javabean
    
       >Classroom类

            public class ClassRoom {
                private int cid;
                private String name;
                private Student studentArray[];
            }

      >Student类
    
            public class Student {
              private Integer id;
              private String name;
              private String gender;
            }    

    * xml配置
    
          <bean id="classroomArray" class="com.edu.bean.Classroom">
              <property name="cid" value="6"/>
              <property name="name" value="六班"/>
              <property name="studentArray">
                  <list>
                      <bean class="com.edu.bean.Student">
                          <property name="id" value="8"/>
                          <property name="name" value="花花"/>
                      </bean>
                      <bean class="com.edu.bean.Student">
                          <property name="id" value="9"/>
                          <property name="name" value="蕾蕾"/>
                      </bean>
                  </list>
              </property>
          </bean>

    * 测试
   
            @Test
            public void testClassroomArray() {
               ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
               Student student = (Student)applicationContext.getBean("classroomArray");
               System.out.println(student);
            }

* 4、Classrom类中有set

    * javabean
    
       >Classroom类

            public class ClassRoom {
                private int cid;
                private String name;
                private Set<Student> studentSet;
            }

      >Student类
    
            public class Student {
              private Integer id;
              private String name;
              private String gender;
            }    

    * xml配置
    
      * 1>set标签
      
            <bean id="classroomSet1" class="com.edu.bean.Classroom">
                 <property name="cid" value="9"/>
                 <property name="name" value="九班"/>
                 <property name="studentSet">
                     <set>
                         <bean class="com.edu.bean.Student">
                             <property name="id" value="8"/>
                             <property name="name" value="花花"/>
                         </bean>
                         <bean class="com.edu.bean.Student">
                             <property name="id" value="9"/>
                             <property name="name" value="蕾蕾"/>
                         </bean>
                     </set>
                 </property>
             </bean>
      
      * 2>lait标签
      
             <bean id="classroomSet2" class="com.edu.bean.Classroom">
                 <property name="cid" value="10"/>
                 <property name="name" value="十班"/>
                 <property name="studentSet">
                     <list>
                         <bean class="com.edu.bean.Student">
                             <property name="id" value="8"/>
                             <property name="name" value="花花"/>
                         </bean>
                         <bean class="com.edu.bean.Student">
                             <property name="id" value="9"/>
                             <property name="name" value="蕾蕾"/>
                         </bean>
                     </list>
                 </property>
             </bean>
        
    * 测试
   
            @Test
            public void testClassroomSet() {
               ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
               Student student = (Student)applicationContext.getBean("classroomSet1");//classroomSet2
               System.out.println(student);
            }
            

* 5、Classrom类中有map

    * javabean
    
       >Classroom类

            public class ClassRoom {
                private int cid;
                private String name;
                private Map<String,Student> studentMap;
            }

      >Student类
    
            public class Student {
              private Integer id;
              private String name;
              private String gender;
            }    

    * xml配置
    
          <bean id="classroomMap" class="com.edu.bean.Classroom">
              <property name="cid" value="90"/>
              <property name="name" value="九十班"/>
              <property name="studentMap">
                  <map>
                      <entry key="aa" value-ref="student"/>
                      <entry key="bb">
                          <bean class="com.edu.bean.Student">
                              <property name="id" value="100"/>
                              <property name="name" value="大炮"/>
                          </bean>
                      </entry>
                  </map>
              </property>
          </bean>

    * 测试
   
            @Test
            public void testClassroomMap() {
               ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
               Student student = (Student)applicationContext.getBean("classroomMap");
               System.out.println(student);
            }

### 四、使用p命名空间为属性赋值:

* javabean

     >Student类
    
            public class Student {
              private Integer id;
              private String name;
              private String gender;
            }    



* xml配置

      <bean id="studentP" class="com.edu.bean.Student" p:id="99" p:name="狗蛋" p:gender="男"></bean>

* 测试

      @Test
      public void testClassroomMap() {
         ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
         Student student = (Student)applicationContext.getBean("studentP");
         System.out.println(student);
      }
























