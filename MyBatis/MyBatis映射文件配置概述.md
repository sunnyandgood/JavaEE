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

*
   



### 三、insert, update 和 delete

* 
