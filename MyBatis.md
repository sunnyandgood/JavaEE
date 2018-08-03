# MyBatis

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

     * mybatis-3.4.6.jar
     
     * mysql-connector-java-5.1.14.jar
      
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
                      <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
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
                private int id;
                private String userName;
                private String gender;
                private String email;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public String getEmail() {
                    return email;
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

     * UserMapper.java
     
            package com.edu.mapper;

            import com.edu.bean.User;

            import java.util.List;

            public interface UserMapper {
                List<User> selectAll();
            }

* 5、mybatis新版本支持面向接口编程，程序员不需要再写接口的实现类，转而用配置文件代替：

     * 创建UserMapper的映射文件 UserMapper.xml：(此文件需要和dao文件同名)
           
      <?xml version="1.0" encoding="UTF-8" ?>
      <!DOCTYPE mapper
              PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
              "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
      <mapper namespace="com.edu.mapper.UserMapper">
          <select id="selectAll" resultType="com.edu.bean.User">
               select * from user
          </select>
      </mapper>

* 6、将UserMapper.xml 加载到总配置文件

      <mappers>
           <mapper resource="UserMapper.xml"/>
      </mappers>

* 7、测试

      package com.edu.test;

      import com.edu.bean.User;
      import com.edu.mapper.UserMapper;
      import org.apache.ibatis.io.Resources;
      import org.apache.ibatis.session.SqlSession;
      import org.apache.ibatis.session.SqlSessionFactory;
      import org.apache.ibatis.session.SqlSessionFactoryBuilder;

      import java.io.IOException;
      import java.io.InputStream;
      import java.util.List;

      public class UserTest {
          public void testSelectAll() throws IOException {
              //加载mybatis-config.xml到输入流
              InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
              //通过输入流构建SqlSessionFactory对象
              SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
              //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
              SqlSession sqlSession = sqlSessionFactory.openSession();
              //通过SqlSession对象得到一个mapper的实现对象
              UserMapper mapper = sqlSession.getMapper(UserMapper.class);
              List<User> users = mapper.selectAll();
              System.out.println(users);
          }
      }







