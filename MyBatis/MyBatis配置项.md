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

   <table>
      <tr width="400px">
         <td>设置参数</td>
         <td>描述</td>
         <td>有效值</td>
         <td>默认值</td>
      </tr>
      <tr width="400px">
         <td>cacheEnabled</td>
         <td>全局地开启或关闭配置文件中的所有映射器已经配置的任何缓存。</td>
         <td>true | false</td>
         <td>TRUE</td>
      </tr>
      <tr width="400px">
         <td>lazyLoadingEnabled</td>
         <td>延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置fetchType属性来覆盖该项的开关状态。</td>
         <td>true | false</td>
         <td>FALSE</td>
      </tr>
      <tr width="400px">
         <td>aggressiveLazyLoading</td>
         <td>当开启时，任何方法的调用都会加载该对象的所有属性。否则，每个属性会按需加载（参考lazyLoadTriggerMethods).</td>
         <td>true | false</td>
         <td>false (true in ≤3.4.1)</td>
      </tr>
      <tr width="400px">
         <td>multipleResultSetsEnabled</td>
         <td>是否允许单一语句返回多结果集（需要兼容驱动）。</td>
         <td>true | false</td>
         <td>TRUE</td>
      </tr>
      <tr width="400px">
         <td>useColumnLabel</td>
         <td>使用列标签代替列名。不同的驱动在这方面会有不同的表现， 具体可参考相关驱动文档或通过测试这两种不同的模式来观察所用驱动的结果。</td>
         <td>true | false</td>
         <td>TRUE</td>
      </tr>
      <tr width="400px">
         <td>useGeneratedKeys</td>
         <td>允许 JDBC 支持自动生成主键，需要驱动兼容。 如果设置为 true 则这个设置强制使用自动生成主键，尽管一些驱动不能兼容但仍可正常工作（比如 Derby）。</td>
         <td>true | false</td>
         <td>FALSE</td>
      </tr>
      <tr width="400px">
         <td>autoMappingBehavior</td>
         <td>指定 MyBatis 应如何自动映射列到字段或属性。 NONE 表示取消自动映射；PARTIAL 只会自动映射没有定义嵌套结果集映射的结果集。 FULL 会自动映射任意复杂的结果集（无论是否嵌套）。</td>
         <td>NONE, PARTIAL, FULL</td>
         <td>PARTIAL</td>
      </tr>
      <tr width="400px">
         <td>autoMappingUnknownColumnBehavior</td>
         <td>指定发现自动映射目标未知列（或者未知属性类型）的行为。NONE: 不做任何反应、WARNING: 输出提醒日志 ('org.apache.ibatis.session.AutoMappingUnknownColumnBehavior' 的日志等级必须设置为 WARN)、FAILING: 映射失败 (抛出 SqlSessionException)</td>
         <td>NONE, WARNING, FAILING</td>
         <td>NONE</td>
      </tr>
      <tr width="400px">
         <td>defaultExecutorType</td>
         <td>配置默认的执行器。SIMPLE 就是普通的执行器；REUSE 执行器会重用预处理语句（prepared statements）； BATCH 执行器将重用语句并执行批量更新。</td>
         <td>SIMPLE REUSE BATCH</td>
         <td>SIMPLE</td>
      </tr>
      <tr width="400px">
         <td>defaultStatementTimeout</td>
         <td>设置超时时间，它决定驱动等待数据库响应的秒数。</td>
         <td>任意正整数</td>
         <td>Not Set (null)</td>
      </tr>
      <tr width="400px">
         <td>defaultFetchSize</td>
         <td>为驱动的结果集获取数量（fetchSize）设置一个提示值。此参数只可以在查询设置中被覆盖。</td>
         <td>任意正整数</td>
         <td>Not Set (null)</td>
      </tr>
      <tr width="400px">
         <td>safeRowBoundsEnabled</td>
         <td>允许在嵌套语句中使用分页（RowBounds）。如果允许使用则设置为false。</td>
         <td>true | false</td>
         <td>FALSE</td>
      </tr>
      <tr width="400px">
         <td>safeResultHandlerEnabled</td>
         <td>允许在嵌套语句中使用分页（ResultHandler）。如果允许使用则设置为false。</td>
         <td>true | false</td>
         <td>TRUE</td>
      </tr>
      <tr width="400px">
         <td>mapUnderscoreToCamelCase</td>
         <td>是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射。</td>
         <td>true | false</td>
         <td>FALSE</td>
      </tr>
      <tr width="400px">
         <td>localCacheScope</td>
         <td>MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。 默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。 若设置值为 STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 的不同调用将不会共享数据。</td>
         <td>SESSION | STATEMENT</td>
         <td>SESSION</td>
      </tr>
      <tr width="400px">
         <td>jdbcTypeForNull</td>
         <td>当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。 某些驱动需要指定列的 JDBC 类型，多数情况直接用一般类型即可，比如 NULL、VARCHAR 或 OTHER。</td>
         <td>JdbcType 常量. 大多都为: NULL, VARCHAR and OTHER</td>
         <td>OTHER</td>
      </tr>
      <tr width="400px">
         <td>lazyLoadTriggerMethods</td>
         <td>指定哪个对象的方法触发一次延迟加载。</td>
         <td>用逗号分隔的方法列表。</td>
         <td>equals,clone,hashCode,toString</td>
      </tr>
      <tr width="400px">
         <td>defaultScriptingLanguage</td>
         <td>指定动态 SQL 生成的默认语言。</td>
         <td>一个类型别名或完全限定类名。</td>
         <td>org.apache.ibatis.scripting.xmltags.XMLLanguageDriver</td>
      </tr>
      <tr width="400px">
         <td>defaultEnumTypeHandler</td>
         <td>指定 Enum 使用的默认 TypeHandler 。 (从3.4.5开始)</td>
         <td>一个类型别名或完全限定类名。</td>
         <td>org.apache.ibatis.type.EnumTypeHandler</td>
      </tr>
      <tr width="400px">
         <td>callSettersOnNulls</td>
         <td>指定当结果集中值为 null 的时候是否调用映射对象的 setter（map 对象时为 put）方法，这对于有 Map.keySet() 依赖或 null 值初始化的时候是有用的。注意基本类型（int、boolean等）是不能设置成 null 的。</td>
         <td>true | false</td>
         <td>FALSE</td>
      </tr>
      <tr width="400px">
         <td>returnInstanceForEmptyRow</td>
         <td>当返回行的所有列都是空时，MyBatis默认返回null。 当开启这个设置时，MyBatis会返回一个空实例。 请注意，它也适用于嵌套的结果集 (i.e. collectioin and association)。（从3.4.2开始）</td>
         <td>true | false</td>
         <td>FALSE</td>
      </tr>
      <tr width="400px">
         <td>logPrefix</td>
         <td>指定 MyBatis 增加到日志名称的前缀。</td>
         <td>任何字符串</td>
         <td>Not set</td>
      </tr>
      <tr width="400px">
         <td>logImpl</td>
         <td>指定 MyBatis 所用日志的具体实现，未指定时将自动查找。</td>
         <td>SLF4J | LOG4J | LOG4J2 | JDK_LOGGING | COMMONS_LOGGING | STDOUT_LOGGING | NO_LOGGING</td>
         <td>Not set</td>
      </tr>
      <tr width="400px">
         <td>proxyFactory</td>
         <td>指定 Mybatis 创建具有延迟加载能力的对象所用到的代理工具。</td>
         <td>CGLIB | JAVASSIST</td>
         <td>JAVASSIST (MyBatis 3.3 or above)</td>
      </tr>
      <tr width="400px">
         <td>vfsImpl</td>
         <td>指定VFS的实现</td>
         <td>自定义VFS的实现的类全限定名，以逗号分隔。</td>
         <td>Not set</td>
      </tr>
      <tr width="400px">
         <td>useActualParamName</td>
         <td>允许使用方法签名中的名称作为语句参数名称。 为了使用该特性，你的工程必须采用Java 8编译，并且加上-parameters选项。（从3.4.1开始）</td>
         <td>true | false</td>
         <td>TRUE</td>
      </tr>
      <tr width="400px">
         <td>configurationFactory</td>
         <td>指定一个提供Configuration实例的类。 这个被返回的Configuration实例用来加载被反序列化对象的懒加载属性值。 这个类必须包含一个签名方法static Configuration getConfiguration(). (从 3.2.3 版本开始)</td>
         <td>类型别名或者全类名.</td>
         <td>Not set</td>
      </tr>
   </table>

   >eg
   
      <settings>
        <setting name="cacheEnabled" value="true"/>
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="multipleResultSetsEnabled" value="true"/>
        <setting name="useColumnLabel" value="true"/>
        <setting name="useGeneratedKeys" value="false"/>
        <setting name="autoMappingBehavior" value="PARTIAL"/>
        <setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>
        <setting name="defaultExecutorType" value="SIMPLE"/>
        <setting name="defaultStatementTimeout" value="25"/>
        <setting name="defaultFetchSize" value="100"/>
        <setting name="safeRowBoundsEnabled" value="false"/>
        
        <setting name="mapUnderscoreToCamelCase" value="false"/>
              <!--此配置默认是false，此配置可以把数据库字段中的下划线替换成java实体类中的驼峰式变量。-->
              <!--例如： 数据库中 user_name 可以自动映射为java实体类中的 userName-->
              
        <setting name="localCacheScope" value="SESSION"/>
        <setting name="jdbcTypeForNull" value="OTHER"/>
        <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
      </settings>   

### 四、typeAliases 类型别名

* 映射文件中,默认只能写全类名： 配置此项后，可以给全类名起别名，在映射文件中方便书写：

      <typeAliases>
        <typeAlias alias="User" type="com.weixin.User"/>
      </typeAliases>

* 配置会有默认别名 user ,别名是不区分大小写的，我们在映射文件中还是建议使用User

      <typeAliases>
        <typeAlias type="com.weixin.User"/>
      </typeAliases>

* 最优方案👉 一个一个写别名又很麻烦，所以直接指定实体类所在的包，包下所有实体类都会设置别名

      <typeAliases>
          <package name="com.weixin.bean"/>
      </typeAliases>



