# MyBatis映射文件配置概述


### 一、Mapper XML 文件

* MyBatis 的真正强大在于它的映射语句，也是它的魔力所在。相当于之前的dao层实现类位置

* 基本结构：

    * namespace为命名空间，一般和接口名同名，不能使用别名， 按规范书写给人的感觉将非常舒适：namespace写类名，下面的id写方法名
    
          <?xml version="1.0" encoding="UTF-8" ?>
          <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
          <mapper namespace="com.weixin.dao.UserMapper">
            <select id="selectAll" resultType="user">
              select * from user
            </select>
          </mapper>
          
* SQL映射文件还包括如下标签：

      insert – 映射插入语句
      update – 映射更新语句
      delete – 映射删除语句
      select – 映射查询语句
      resultMap – 是最复杂也是最强大的元素，用来描述如何从数据库结果集中来加载对象
      sql – 可被其他语句引用的可重用语句块
      cache – 给定命名空间的缓存配置
      cache-ref – 其他命名空间缓存配置的引用



### 二、select

* 查询语句是 MyBatis 中最常用的元素之一，光能把数据存到数据库中价值并不大，如果还能重新取出来才有用，多数应用也都是查询比修改要频繁。对每个插入、更新或删除操作，通常对应多个查询操作。这是 MyBatis 的基本原则之一，也是将焦点和努力放到查询和结果映射的原因。简单查询的 select 元素是非常简单的。

   >比如：
   
      <select id="selectPerson" parameterType="int" resultType="hashmap">
        SELECT * FROM PERSON WHERE ID = #{id}
      </select>   
      
   * 这个语句被称作 selectPerson，接受一个 int（或 Integer）类型的参数，并返回一个 HashMap 类型的对象，其中的键是列名，值便是结果行中的对应值。

   * 注意参数符号：

            #{id}

    * 这就告诉 MyBatis 创建一个预处理语句参数，通过 JDBC，这样的一个参数在 SQL 中会由一个“?”来标识，并被传递到一个新的预处理语句中，就像这样：

            // Similar JDBC code, NOT MyBatis…
            String selectPerson = "SELECT * FROM PERSON WHERE ID=?";
            PreparedStatement ps = conn.prepareStatement(selectPerson);
            ps.setInt(1,id);

   * 当然，这需要很多单独的 JDBC 的代码来提取结果并将它们映射到对象实例中，这就是 MyBatis 节省你时间的地方。我们需要深入了解参数和结果映射。
   
* select 元素有很多属性允许你配置，来决定每条语句的作用细节。

      <select
        id="selectPerson"
        parameterType="int"
        parameterMap="deprecated"
        resultType="hashmap"
        resultMap="personResultMap"
        flushCache="false"
        useCache="true"
        timeout="10000"
        fetchSize="256"
        statementType="PREPARED"
        resultSetType="FORWARD_ONLY">   
        
     * Select Attributes
   
         <table>
         <tr>
            <td>属性</td>
            <td>描述</td>
         </tr>
         <tr>
            <td>id</td>
            <td>在命名空间中唯一的标识符，可以被用来引用这条语句。</td>
         </tr>
         <tr>
            <td>parameterType</td>
            <td>将会传入这条语句的参数类的完全限定名或别名。这个属性是可选的，因为 MyBatis 可以通过 TypeHandler 推断出具体传入语句的参数，默认值为 unset。</td>
         </tr>
         <tr>
            <td>parameterMap</td>
            <td>这是引用外部 parameterMap 的已经被废弃的方法。使用内联参数映射和 parameterType 属性。 </td>
         </tr>
         <tr>
            <td>resultType</td>
            <td>从这条语句中返回的期望类型的类的完全限定名或别名。注意如果是集合情形，那应该是集合可以包含的类型，而不能是集合本身。使用 resultType 或 resultMap，但不能同时使用。</td>
         </tr>
         <tr>
            <td>resultMap</td>
            <td>外部 resultMap 的命名引用。结果集的映射是 MyBatis 最强大的特性，对其有一个很好的理解的话，许多复杂映射的情形都能迎刃而解。使用 resultMap 或 resultType，但不能同时使用。</td>
         </tr>
         <tr>
            <td>flushCache</td>
            <td>将其设置为 true，任何时候只要语句被调用，都会导致本地缓存和二级缓存都会被清空，默认值：false。</td>
         </tr>
         <tr>
            <td>useCache</td>
            <td>将其设置为 true，将会导致本条语句的结果被二级缓存，默认值：对 select 元素为 true。</td>
         </tr>
         <tr>
            <td>timeout</td>
            <td>这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。默认值为 unset（依赖驱动）。</td>
         </tr>
         <tr>
            <td>fetchSize</td>
            <td>这是尝试影响驱动程序每次批量返回的结果行数和这个设置值相等。默认值为 unset（依赖驱动）。</td>
         </tr>
         <tr>
            <td>statementType</td>
            <td>STATEMENT，PREPARED 或 CALLABLE 的一个。这会让 MyBatis 分别使用 Statement，PreparedStatement 或 CallableStatement，默认值：PREPARED。</td>
         </tr>
         <tr>
            <td>resultSetType</td>
            <td>FORWARD_ONLY，SCROLL_SENSITIVE 或 SCROLL_INSENSITIVE 中的一个，默认值为 unset （依赖驱动）。</td>
         </tr>
         <tr>
            <td>databaseId</td>
            <td>如果配置了 databaseIdProvider，MyBatis 会加载所有的不带 databaseId 或匹配当前 databaseId 的语句；如果带或者不带的语句都有，则不带的会被忽略。</td>
         </tr>
         <tr>
            <td>resultOrdered</td>
            <td>这个设置仅针对嵌套结果 select 语句适用：如果为 true，就是假设包含了嵌套结果集或是分组了，这样的话当返回一个主结果行的时候，就不会发生有对前面结果集的引用的情况。这就使得在获取嵌套的结果集的时候不至于导致内存不够用。默认值：false。</td>
         </tr>
         <tr>
            <td>resultSets</td>
            <td>  这个设置仅对多结果集的情况适用，它将列出语句执行后返回的结果集并每个结果集给一个名称，名称是逗号分隔的。</td>
         </tr>
      </table>
   



### 三、insert, update 和 delete

* 
