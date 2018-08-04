# MyBatis入门

### 一、简介

* [官方文档](http://www.mybatis.org/mybatis-3/zh/index.html)

### 二、起步

* 1、准备数据库
      
     * 新建数据库 mybatis
      
     * 新建数据表 user
     
              DROP TABLE IF EXISTS `user`;
              CREATE TABLE `user` (
                `id` int(11) NOT NULL AUTO_INCREMENT,
                `user_name` varchar(255) DEFAULT NULL,
                `gender` char(1) DEFAULT NULL,
                `email` varchar(255) DEFAULT NULL,
                PRIMARY KEY (`id`)
              ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

              -- ----------------------------
              -- Records of user
              -- ----------------------------
              INSERT INTO `user` VALUES ('1', '小明', '1', 'xiaoming@163.com');
              
* 2、新建工程并导包（新建一个javase工程）
     
     * 手动（非maven项目）
     
          * mybatis-3.4.6.jar
     
          * mysql-connector-java-5.1.14.jar
          
     * 自动（maven项目）（将以下内容加到pom.xml中）
     
            <dependency>
                  <groupId>junit</groupId>
                  <artifactId>junit</artifactId>
                  <version>4.12</version>
                  <scope>test</scope>
                </dependency>
                <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
                <dependency>
                  <groupId>org.mybatis</groupId>
                  <artifactId>mybatis</artifactId>
                  <version>3.4.1</version>
                </dependency>
                <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
                <dependency>
                  <groupId>mysql</groupId>
                  <artifactId>mysql-connector-java</artifactId>
                  <version>5.1.14</version>
                </dependency>
                <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
                <dependency>
                  <groupId>org.slf4j</groupId>
                  <artifactId>slf4j-log4j12</artifactId>
                  <version>1.7.25</version>
                  <scope>test</scope>
                </dependency>

                <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
              <!-- <dependency>
                  <groupId>org.projectlombok</groupId>
                  <artifactId>lombok</artifactId>
                  <version>1.18.0</version>
                  <scope>provided</scope>
              </dependency> -->     
     
          * 将 Log4j.xml或log4j.properties 置与 classpath 中
          
               * log4j.xml
               
                        <?xml version="1.0" encoding="UTF-8" ?>
                        <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

                        <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

                         <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
                           <param name="Encoding" value="UTF-8" />
                           <layout class="org.apache.log4j.PatternLayout">
                            <param name="ConversionPattern" value="%-5p %d{MM-dd HH:mm:ss,SSS} %m  (%F:%L) \n" />
                           </layout>
                         </appender>

                         <logger name="org.apache.ibatis">
                           <level value="info" />
                         </logger>
                         <root>
                           <level value="debug" />
                           <appender-ref ref="STDOUT" />
                         </root>
                        </log4j:configuration>
               
               * log4j.properties
               
                        log4j.rootLogger=debug,stdout
                        log4j.appender.stdout=org.apache.log4j.ConsoleAppender 
                        log4j.appender.stdout.layout=org.apache.log4j.PatternLayout    
                        log4j.appender.stdout.layout.ConversionPattern="%-5p %m (%c:%L) \n


                        log4j.logger.org.apache.ibatis=INFO

                        #log4j.logger.java.sql=DEBUG
     
* 3、创建Mybatis总配置文件mybatis-config.xml(文件名可变)

      <?xml version="1.0" encoding="UTF-8" ?>
      <!DOCTYPE configuration
              PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
              "http://mybatis.org/dtd/mybatis-3-config.dtd">
      <configuration>
          <!--<typeAliases>
              <package name="com.weixin.vo"/>
          </typeAliases> -->
          <environments default="development">
              <environment id="development">
                  <transactionManager type="JDBC"/>
                  <dataSource type="POOLED">
                      <property name="driver" value="com.mysql.jdbc.Driver"/>
                      <property name="jdbc:mysql://localhost:3306/mybatis??useUnicode=true&amp;characterEncoding=UTF-8"/>
                      <property name="username" value="root"/>
                      <property name="password" value="root"/>
                  </dataSource>
              </environment>
          </environments>
          <!-- <mappers>
              <mapper resource="UserMapper.xml"/>
            </mappers> -->
      </configuration>

* 4、新建User实体类及dao文件

     * User.java(实体类中userName对应数据表中的user_name字段)

            package com.edu.bean;

            public class User {
                private Integer id;
                private String userName;
                private String gender;
                private String email;

                public User() {
                }

                public Integer getId() {
                    return this.id;
                }

                public String getUserName() {
                    return this.userName;
                }

                public String getGender() {
                    return this.gender;
                }

                public String getEmail() {
                    return this.email;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                @Override
                public String toString() {
                    return "User{" +
                            "id=" + id +
                            ", userName='" + userName + '\'' +
                            ", gender='" + gender + '\'' +
                            ", email='" + email + '\'' +
                            '}';
                }
            }

     * UserMapper.java(dao文件)
     
            package com.edu.mapper;

            import com.edu.bean.User;

            import java.util.List;

            public interface UserMapper {
                //查询所有数据
                List<User> selectAll();
                //根据id查询一条语句
                User selectById(int id);
                //增加用户
                int inser(User user);
                //删除用户
                boolean deleteById(int id);
                //修改用户
                boolean update(User user);
            }

* 5、mybatis新版本支持面向接口编程，程序员不需要再写接口的实现类，转而用配置文件代替：

     * 创建UserMapper的映射文件 UserMapper.xml：(此文件需要和dao文件同名)
           
            <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE mapper
                    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
                    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
            <mapper namespace="com.edu.mapper.UserMapper">
                <insert id="inser">
                    insert into user(user_name,gender,email) values (#{userName},#{gender},#{email});
                </insert>
                <update id="update">
                    update user set user_name = #{userName},gender = #{gender},email = #{email} where id = #{id}
                </update>
                <delete id="deleteById">
                    delete from user where id = #{id}
                </delete>
                <select id="selectAll" resultType="com.edu.bean.User">
                     select * from user
                </select>
                <select id="selectById" resultType="com.edu.bean.User">
                    select * from user where id = #{id}
                </select>
            </mapper>

* 6、将UserMapper.xml 加载到总配置文件mybatis-config.xml中

      <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
      </settings>
      
      <environments>
            ........
      </environments>
      
      <mappers>
           <mapper resource="UserMapper.xml"/>
      </mappers>



* 7、测试

      package com.edu;

      import com.edu.bean.User;
      import com.edu.mapper.UserMapper;
      import org.apache.ibatis.io.Resources;
      import org.apache.ibatis.session.SqlSession;
      import org.apache.ibatis.session.SqlSessionFactory;
      import org.apache.ibatis.session.SqlSessionFactoryBuilder;
      import org.junit.Test;

      import java.io.IOException;
      import java.io.InputStream;
      import java.util.List;

      public class UserTest {

          //加载mybatis-config.xml到输入流
          InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
          //通过输入流构建SqlSessionFactory对象
          SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

          public UserTest() throws IOException {
          }

          @Test
          public void testSelectAll() throws IOException {

              //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
              SqlSession sqlSession = sqlSessionFactory.openSession();
              //通过SqlSession对象得到一个mapper的实现对象
              UserMapper mapper = sqlSession.getMapper(UserMapper.class);
              List<User> usersList = mapper.selectAll();
              System.out.println(usersList);
          }

          @Test
          public void testSelectById(){
              //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
              SqlSession sqlSession = sqlSessionFactory.openSession();
              //通过SqlSession对象得到一个mapper的实现对象
              UserMapper mapper = sqlSession.getMapper(UserMapper.class);

              User user = mapper.selectById(1);
              System.out.println(user);
          }

          @Test
          public void testInsert(){
              //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
              SqlSession sqlSession = sqlSessionFactory.openSession();
              //通过SqlSession对象得到一个mapper的实现对象
              UserMapper mapper = sqlSession.getMapper(UserMapper.class);

              User user = new User();
              user.setUserName("小美");
              user.setGender("2");
              user.setEmail("7777@163.com");

              int inser = mapper.inser(user);

              System.out.println(inser);

              //提交事务
              sqlSession.commit();
          }

          @Test
          public void testDeleteById(){
              //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
              SqlSession sqlSession = sqlSessionFactory.openSession();
              //通过SqlSession对象得到一个mapper的实现对象
              UserMapper mapper = sqlSession.getMapper(UserMapper.class);

              boolean b = mapper.deleteById(5);

              System.out.println(b);

              sqlSession.commit();
          }

          @Test
          public void testUpdate(){
              //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
              SqlSession sqlSession = sqlSessionFactory.openSession();
              //通过SqlSession对象得到一个mapper的实现对象
              UserMapper mapper = sqlSession.getMapper(UserMapper.class);

              //先把数据查出来
              User user = mapper.selectById(1);
              user.setGender("2");

              //修改
              mapper.update(user);
              sqlSession.commit();
          }
      }
