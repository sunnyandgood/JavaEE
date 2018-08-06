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
