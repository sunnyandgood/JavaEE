# hibernate_增删改查

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
      public void testSelect() {
        //User user = (User) session.load(User.class, 1);
        User user = (User)session.get(User.class, 1);
        System.out.println(user);
      }

      @Test
      public void testInsert() {
        User user = new User();
        user.setUserName("小晓");
        user.setEmail("hddf@163.com");
        Transaction transaction = session.beginTransaction();
        try {
          session.save(user);
          transaction.commit();
        } catch (Exception e) {
          transaction.rollback();
          e.printStackTrace();
        }
      }

      @Test
      public void testDelete() {
        User user = new User();
        user.setId(112);
        Transaction transaction = session.beginTransaction();
        try {
          session.delete(user);
          transaction.commit();
        } catch (Exception e) {
          transaction.rollback();
          e.printStackTrace();
        }
      }

      @Test
      public void testUpdate() {
        User user = (User) session.get(User.class, 113);
        user.setUserName("小夏");
        Transaction transaction = session.beginTransaction();
        try {
          session.update(user);
          transaction.commit();
        } catch (Exception e) {
          transaction.rollback();
          e.printStackTrace();
        }
      }
    }
