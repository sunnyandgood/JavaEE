# spring+mybatis整合之环境搭建

* Spring是一个大容器，我们可以把mybatis交给spring去维护管理

* 需要导入的包：

    * spring包

    * mybatis包

    * mybatis-spring整合包

    * spring-jdbc

    * mysql-connect

    * 连接池包

    <div align="center"><img src="../img/Spring-Mybatis.png"/></div>

* pom.xml

      <dependencies>
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
              <version>4.3.13.RELEASE</version>
          </dependency>
          <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-jdbc</artifactId>
              <version>4.3.13.RELEASE</version>
          </dependency>
          <dependency>
              <groupId>commons-logging</groupId>
              <artifactId>commons-logging</artifactId>
              <version>1.2</version>
          </dependency>
          <dependency>
              <groupId>log4j</groupId>
              <artifactId>log4j</artifactId>
              <version>1.2.17</version>
          </dependency>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.12</version>
              <scope>test</scope>
          </dependency>
          <dependency>
              <groupId>org.mybatis</groupId>
              <artifactId>mybatis</artifactId>
              <version>3.4.1</version>
          </dependency>
          <dependency>
              <groupId>org.mybatis</groupId>
              <artifactId>mybatis-spring</artifactId>
              <version>1.3.2</version>
          </dependency>
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.14</version>
          </dependency>
          <dependency>
              <groupId>org.slf4j</groupId>
              <artifactId>slf4j-log4j12</artifactId>
              <version>1.7.25</version>
              <scope>test</scope>
          </dependency>
          <dependency>
              <groupId>com.mchange</groupId>
              <artifactId>c3p0</artifactId>
              <version>0.9.5.2</version>
          </dependency>
          <dependency>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
              <version>1.18.0</version>
              <scope>provided</scope>
          </dependency>
      </dependencies>

