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














