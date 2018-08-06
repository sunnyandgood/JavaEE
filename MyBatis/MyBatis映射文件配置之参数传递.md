# MyBatis映射文件配置之参数传递

### 一、单个普通参数

* 直接使用`#{参数名}`进行取值，mybatis没做特殊处理，参数名可以随便写。

### 二、单个参数-参数为map，和bean

* UserMapper.java

      //传入一个javabean参数返回list
      List<User>selectAllByConditionBean(User user);
      //传入一个map参数返回list
      List<User>selectAllByConditionMap(Map<String,String> map);

* UserMapper.xml

      <select id="selectAllByConditionBean" resultType="com.edu.bean.User">
          select * from user where id=#{id} and user_name=#{userName}
      </select>
      <select id="selectAllByConditionMap" resultType="com.edu.bean.User">
          select * from user  where id=#{id} and user_name=#{userName}
      </select>

* TestUserMapper.java

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

### 三、单个参数-参数为list，数组的形式

* 传入list,默认的键是 list；传入数组,默认的键是array，建议使用 @Param()指定建名

* UserMapper.java

      //查询、参数是list
      List<User> selectByCondition(@Param("list") List<String> list);//默认是list，可通过@Param修改映射
  
* UserMapper.xml

      <select id="selectByCondition" resultType="com.edu.bean.User">
          select * from user where user_name=#{list[0]} and gender=#{list[1]}
      </select>

* TestUserMapper.java

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
      
 ###  四、多个参数
  
 *  UserMapper.java
 
        //多参数
        List<User> selectNameGender(@Param("name") String n,@Param("gender") String g);

* UserMapper.xml

      <select id="selectNameGender" resultType="com.edu.bean.User">
          select * from user where user_name=#{name} and gender=#{gender}
      </select>

* TestUserMapper.java

      @Test
      public void testSelectNameGender(){
          //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
          SqlSession sqlSession = sqlSessionFactory.openSession();
          //通过SqlSession对象得到一个mapper的实现对象
          UserMapper mapper = sqlSession.getMapper(UserMapper.class);

          List<User> list = mapper.selectNameGender("小雷","1");
          System.out.println(list);
      }










