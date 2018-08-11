# Spring控制反转IOC之注解方式

### 一、Spring能够从classpath中自动扫描到bean

* 注解包括：

    * @Component :除了下面的都拿它标识（组件）

    * @Respository ：持久层

    * @Service :业务层

    * @Controller :控制层

* 对于扫描到的bean，默认id就是类名首字母小写，当然我们也可以通过value属性指定bean

    * @Repository:默认id是类名首字母小写 eg：StudentDao的默认id是studentDao

* 当在类上使用了特定注解后，还需要在配置文件中添加声明：

      <!-- base-package表示需要扫描的包及子包 -->
      <context:component-scan base-package="com.edu.bean"></context:component-scan>


### 二、环境配置

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
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
            <version>1.16.18</version>
            <scope>provided</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.mchange/c3p0 -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.40</version>
        </dependency>

### 三、应用

* 1、入门

















































