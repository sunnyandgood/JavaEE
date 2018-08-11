# spring+mybatis整合之Mybatis准备

### 一、用mybatis-generator(代码生成工具)生成mybatis部分

* `generatorConfig.xml`

      <?xml version="1.0" encoding="UTF-8"?>
      <!DOCTYPE generatorConfiguration
              PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
              "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
      <generatorConfiguration>
          <context id="test" targetRuntime="MyBatis3">
              <!--生成equals方法-->
              <!--<plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin"></plugin>-->
              <!--实现序列化接口-->
              <plugin type="org.mybatis.generator.plugins.SerializablePlugin"></plugin>
              <!--生成toString方法-->
              <!--<plugin type="org.mybatis.generator.plugins.ToStringPlugin"></plugin>-->
              <commentGenerator>
                  <!-- 这个元素用来去除指定生成的注释中是否包含生成的日期 false:表示保护 -->
                  <!--如果生成日期，会造成即使修改一个字段，整个实体类所有属性都会发生变化，不利于版本控制，所以设置为true-->
                  <property name="suppressDate" value="true" />
                  <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
                  <property name="suppressAllComments" value="true" />
              </commentGenerator>
              <!--数据库链接URL，用户名、密码 -->
              <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                              connectionURL="jdbc:mysql://localhost:3306/mybatis?
                              useUnicode=true&amp;characterEncoding=UTF-8" userId="root" password="root">
              </jdbcConnection>
              <javaTypeResolver>
                  <!-- 默认false,把jdbc Decimal和Numberic解析为Integer,为true时解析为BigDecimal -->
                  <property name="forceBigDecimals" value="false" />
              </javaTypeResolver>
              <!-- 生成模型的包名和位置 -->
              <javaModelGenerator targetPackage="com.edu.bean"
                                  targetProject="src">
                  <property name="enableSubPackages" value="true" />
                  <property name="trimStrings" value="true" />
              </javaModelGenerator>
              <!-- 生成映射文件的包名和位置 -->
              <sqlMapGenerator targetPackage="com.edu.xml"
                               targetProject="src">
                  <property name="enableSubPackages" value="true" />
              </sqlMapGenerator>
              <!-- 生成DAO的包名和位置 -->
              <javaClientGenerator type="XMLMAPPER"
                                   targetPackage="com.edu.mapper" 
                                   implementationPackage="com.test.dao.impl"  targetProject="src">
                  <property name="enableSubPackages" value="true" />
              </javaClientGenerator>

              <!-- 要生成哪些表 -->
              <table tableName="user" domainObjectName="User"
                     enableCountByExample="true" enableUpdateByExample="false"
                     enableDeleteByExample="false" enableSelectByExample="true"
                     selectByExampleQueryId="false">
              </table>
              <table tableName="classroom" domainObjectName="Classroom"
                     enableCountByExample="true" enableUpdateByExample="false"
                     enableDeleteByExample="false" enableSelectByExample="true"
                     selectByExampleQueryId="false">
              </table>
          </context>
      </generatorConfiguration>


### 二、详细代码实现

* javabean

   * [User类](./spring-mybatis/spring_mybatis_demo/src/main/java/com/edu/bean/Classroom.java)

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   





















