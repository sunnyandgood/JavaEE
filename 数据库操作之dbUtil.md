# 数据库操作之dbUtil

* 一、DbUtil类
  ```java
  package com.edu.dbUtil;

  import java.lang.reflect.Field;
  import java.sql.ResultSet;
  import java.util.ArrayList;
  import java.util.List;

  public class DbUtil {

    public static Object resultToList(ResultSet resultSet,Class<?> classs){
      //用来封装实体类对象
      List<Object> list=new ArrayList<Object>();
      try {
        while(resultSet.next()){
          //是用哪个反射机制创建实体类对象
          Object entity=classs.newInstance();
          for(int i=1;i<=resultSet.getMetaData().getColumnCount();i++){
            //根据索引获取当前字段名称
            String columnName=resultSet.getMetaData().getColumnName(i);
            //获取当前索引的值
            Object value=resultSet.getObject(i);
            //根据字段名称获得当前类中的单一属性
            Field field=classs.getDeclaredField(columnName);
            //将当前属性设置为可赋值状态
            field.setAccessible(true);
            //为指定对象的舒心进行赋值
            field.set(entity, value);
          }
          list.add(entity);
        }
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
      return list;
    }
  }
  ```
* 二、Dao类
  ```java
  package com.edu.dao;

  import java.sql.Connection;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.sql.Statement;
  import java.util.List;

  import javax.sql.DataSource;

  import com.mchange.v2.c3p0.ComboPooledDataSource;
  import com.edu.dbUtil.DbUtil;

  public class Dao {

    private Connection connection=null;
    private Statement statement=null;
    private ResultSet resultSet=null;
    private DataSource data=new ComboPooledDataSource();


    public void addObj(String sql) {
      // TODO Auto-generated method stub
      try {
        connection=data.getConnection();
        //创建执行对象
        statement=connection.createStatement();
        statement.executeUpdate(sql);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }finally {
        try {
  //				connection.commit();
          connection.close();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }

    public List<?> query(String sql,Class<?> class1) {
      // TODO Auto-generated method stub
      List<?> list=null;
      try {
        connection=data.getConnection();
        //创建执行对象
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        list=(List<?>) DbUtil.resultToList(resultSet, class1);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }finally {
        try {
  //				connection.commit();
          connection.close();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      return list;
    }
  }
  ```
