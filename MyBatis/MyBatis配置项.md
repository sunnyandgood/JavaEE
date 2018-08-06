# MyBatis配置项

### 一、XML 映射配置文件

* MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置（settings）和属性（properties）信息。文档的顶层结构如下：

    * configuration 配置
        
        * properties 属性
        
        * settings 设置
        
        * typeAliases 类型别名
        
        * typeHandlers 类型处理器
        
        * objectFactory 对象工厂
        
        * plugins 插件
        
        * environments 环境
            
            * environment 环境变量
                
                * transactionManager 事务管理器
                * dataSource 数据源
        
        * databaseIdProvider 数据库厂商标识
        
        * mappers 映射器

### 二、properties属性

* 此配置可以把数据库连接信息配置到另外一个配置文件中，实际工作中数据库连接一般交给Spring管理，此配置一般不用。

   >eg:

         <transactionManager type="JDBC"/>
         <dataSource type="POOLED">
             <property name="driver" value="com.mysql.jdbc.Driver"/>
             <property name="url" value="jdbc:mysql://localhost:3306/mybatis??useUnicode=true&amp;
                                                                        characterEncoding=UTF-8"/>
             <property name="username" value="root"/>
             <property name="password" value="root"/>
         </dataSource>

### 三、setting设置

* 这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。下表描述了设置中各项的意图、默认值等。

