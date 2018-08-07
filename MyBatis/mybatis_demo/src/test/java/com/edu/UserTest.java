package com.edu;

        import com.edu.bean.User;
        import com.edu.mapper.UserMapper;
        import org.apache.ibatis.io.Resources;
        import org.apache.ibatis.session.SqlSession;
        import org.apache.ibatis.session.SqlSessionFactory;
        import org.apache.ibatis.session.SqlSessionFactoryBuilder;
        import org.junit.Test;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class UserTest {

    //加载mybatis-config.xml到输入流
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    //通过输入流构建SqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    public UserTest() throws IOException {
    }

    @Test
    public void testSelectAll() throws IOException {

        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> usersList = mapper.selectAll();
        System.out.println(usersList);
    }

    @Test
    public void testSelectById(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void testInsert(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUserName("小美");
        user.setGender("2");
        user.setEmail("7777@163.com");

        int inser = mapper.inser(user);

        System.out.println(inser);

        //提交事务
        sqlSession.commit();
    }

    @Test
    public void testDeleteById(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        boolean b = mapper.deleteById(5);

        System.out.println(b);

        sqlSession.commit();
    }

    @Test
    public void testUpdate(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //先把数据查出来
        User user = mapper.selectById(1);
        user.setGender("2");

        //修改
        mapper.update(user);
        sqlSession.commit();
    }

    @Test
    public void testUpdateWithMap() {
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //先查
        User user = mapper.selectById(1);

        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", user.getId().toString());
        map.put("name", "小雷");
        map.put("gender", "1");
        map.put("email", user.getEmail());

        //修改
        mapper.updateWithMap(map);
        sqlSession.commit();
    }

    @Test
    public void testSelectByCondition(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<String> list = new ArrayList<>();
        list.add("小雷");
        list.add("1");
        List<User> users = mapper.selectByCondition(list);
        System.out.println(users);
    }

    @Test
    public void testSelectNameGender(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> list = mapper.selectNameGender("小雷","1");
        System.out.println(list);
    }

    @Test
    public void testSelesctUserClassRoom(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> list = mapper.selesctUserClassRoom();

        System.out.println(list);
    }

    @Test
    public void testSelectUserClassRoomById(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.selectUserClassRoomById(1);
        System.out.println(user);
    }


    @Test
    public void testSelectAllByConditionBean(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.selectById(1);
        List<User> users = mapper.selectAllByConditionBean(user);

        System.out.println(users);
    }

    @Test
    public void testselectAllByConditionMap(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.selectById(1);

        Map<String,String> map = new HashMap<String, String>();
        map.put("id",user.getId().toString());
        map.put("userName",user.getUserName());
        map.put("gender",user.getGender());
        map.put("email",user.getEmail());

        List<User> users = mapper.selectAllByConditionMap(map);

        System.out.println(users);
    }



    @Test
    public void testSelectByConditionIf(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String,String> map = new HashMap<>();
        map.put("name","小雷");
        map.put("gender","3");
        List<User> userList = mapper.selectByConditionIf(map);
        System.out.println(userList);
    }

    @Test
    public void testUpdateIf(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = session.getMapper(UserMapper.class);

        User user = new User();
        user.setId(1);
        user.setGender("2");
        boolean b = mapper.updateIf(user);
        session.commit();
        System.out.println(b);
    }

    @Test
    public void testInsertTrim() {
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = session.getMapper(UserMapper.class);

        User user = new User();
        user.setUserName("小华");
        user.setGender("2");
        user.setEmail("122342@163.com");

        boolean b = mapper.insertTrim(user);
        session.commit();
        System.out.println(b);
    }

    @Test
    public void testDeleteForEach(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        UserMapper mapper = session.getMapper(UserMapper.class);

        List<String> list = new ArrayList<>();
        list.add("4");
        list.add("5");
        boolean b = mapper.deleteForEach(list);
        session.commit();
        System.out.println(b);
    }
}
