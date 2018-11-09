# hibernate_HQL

* 1、HQL是`Hibernate Query Language`即Hibernate查询语言

* 2、HQL语法举例：

    * 1>`from Dept`

    * 2>`from Dept where deptName = 'SALES'`

    * 3>`from Dept dept where dept.location is not null`

    * 4>`from Emp order by hireDate，salary desc`

* 3、执行HQL语句的步骤：
   ```java
   //加载总配置文件
   Configuration configure = new Configuration().configure();
   //获得SessionFactory对象
   SessionFactory sessionFactory = configure.buildSessionFactory();
   //获取Session对象
   Session session = sessionFactory.openSession();
   String hql = "from Emp";
   Query query = session.createQuery(hql);
   List<Emp> empList = query.list();
   ```
* 4、实例
   ```java
   package com.edu.test;

   import java.util.List;

   import org.hibernate.Query;
   import org.hibernate.SessionFactory;
   import org.hibernate.Transaction;
   import org.hibernate.cfg.Configuration;
   import org.hibernate.classic.Session;
   import org.junit.Test;

   import com.edu.bean.User;

   public class UserTest {
     //加载总配置文件
     Configuration configure = new Configuration().configure();
     //获得SessionFactory对象
     SessionFactory sessionFactory = configure.buildSessionFactory();
     //获取Session对象
     Session session = sessionFactory.openSession();

     @Test
     public void testHQLSelectAll() {
       Query query = session.createQuery("from User");
       List<?> list = query.list();
       System.out.println(list);
     }

     @Test
     public void testHQLSelectSome() {
       Query query = session.createQuery("from User where id > 50");
       List<?> list = query.list();
       System.out.println(list);
     }

     @Test
     public void testHQLSelectById() {
       Query query = session.createQuery("from User where id = 1");
       User user = (User)query.uniqueResult();
       System.out.println(user);
     }
   }
   ```










