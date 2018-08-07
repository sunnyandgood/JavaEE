# MyBatis进阶用法之复杂映射

* resultMap
* 多对一 --查询单对象
* 多对一 --查询List
* 一对多 --查询单对象
* 一对多 --查询List

### 一、resultMap

* 有时候普通的resultType满足不了开发需要，不能个性化的定义javabean和列值间的对应关系，我们需要替换成另一个属性**resultMap**

* resultMap标签中，type和id都为必须属性。type为对应的bean类型，id为唯一标示，供其他select标签引用。id列对应关系要用id标签，其他列都用result标签，可以选择需要对应的特殊列进行对应，其他对应还是会遵循默认对应方式

* 示例：先把之前最普通的查询替换为resultMap,可以发现我们不需要开启配置文件的驼峰式配置即可完成映射

      public interface UserMapper {
          public User selectById(int id);
      }
      

      <resultMap type="User" id="userResultMap">
      </resultMap>
      <select id="selectById" resultMap="userResultMap">
              select * from user where id=#{id}
      </select>

* 单独定义对应关系：

      <resultMap type="User" id="userResultMap">
        <id property="id" column="id"/>
        <result property="userName" column="user_name"/>
      </resultMap>

* 上面的需求不需要resultMap也可以完成，但是接下来的需求普通的resultType将无法完成

### 二、创建classroom表

>classroom.sql

      DROP TABLE IF EXISTS classroom;
      CREATE TABLE classroom (
        id int(11) NOT NULL AUTO_INCREMENT,
        name varchar(255) DEFAULT NULL,
        PRIMARY KEY (id)
      ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

      -- ----------------------------
      -- Records of classroom
      -- ----------------------------
      INSERT INTO `classroom` VALUES ('1', '一班');
      INSERT INTO `classroom` VALUES ('2', '二班');
      INSERT INTO `classroom` VALUES ('3', '三班');
      

> User.java

    private int id;
    private String userName;
    private String gender;
    private String email;
    private ClassRoom classRoom;      

---------------------------多对一----------------------------------------

### 三、多对一之查询单对象（查询一个用户，同时查询用户所在的班级信息）

* UserMapper.java

          //多表:查询某个用户和所在的班级信息: (多对一)
          User selectUserClassRoomById(int id);

* 写法1 (描述单个对象用association)

     * 本身resultMap可以使用默认映射，但是当resultMap中出现association之类的其他特殊标签时，默认映射会失效，所以建议写全属性

               <resultMap id="UserClassRoomResultMap" type="com.edu.bean.User">
                    <id property="id" column="u_id"/>
                    <result property="userName" column="user_name"/>
                    <result property="gender" column="gender"/>
                    <result property="email" column="email"/>
                    <association property="classroom" javaType="com.edu.bean.Classroom">
                        <id property="id" column="c_id"/>
                        <result property="name" column="name"/>
                    </association>
                </resultMap>

                <select id="selectUserClassRoomById" resultMap="UserClassRoomResultMap">
                    select *,user.id u_id from user
                    left join classroom
                    on user.c_id=classroom.id
                    where user.id=#{id}
                </select>

                <!-- select标签写法不变  -->

* 写法2 (级联方式)<不咋用>

       <resultMap type="User" id="userClassRoomResultMap">
            <id property="id" column="id"/>
            <result property="userName" column="user_name"/>
            <result property="gender" column="gender"/>
            <result property="email" column="email"/>
            <result property="classRoom.cId" column="c_id"/>
            <result property="classRoom.name" column="cname"/>
        </resultMap>

        <select id="selectUserClassRoomById" resultMap="userClassRoomResultMap">
                select user.*,classroom.name cname
                from user left join classroom
                on user.c_id=classroom.c_id
                where id=#{id}
        </select>

* 写法3 (Mybatis特有的分步查询+延迟加载)

     * 分步查询会执行2次查询 
     
          >UserMapper.xml

              <resultMap type="User" id="userClassRoomResultMap3">
                   <id property="id" column="id"/>
                  <result property="userName" column="user_name"/>
                  <result property="gender" column="gender"/>
                  <result property="email" column="email"/>
                  <association property="classRoom" column="c_id" select="com.weixin.dao.
                                                 ClassRoomMapper.selectById"></association>
              </resultMap>

               <select id="selectUserClassRoomById" resultMap="userClassRoomResultMap3" >
                      select * from user where id=#{id}
              </select>

          >ClassRoomMapper.xml

              <select id="selectById" resultType="ClassRoom">
                select * from classroom where c_id=#{id}
              </select>

     * 延迟加载：需要在总配置文件中设置：
     
          >mybatis-config.xml

            <settings>
                <!--开启延迟加载-->
                <setting name="lazyLoadingEnabled" value="true"/>
                <!--关闭积极加载-->
                <setting name="aggressiveLazyLoading" value="false"/>
            </settings>           
     
          * 设置延迟加载后，sql会按需查询，sql只执行一次
          
          * 延迟加载可以降低mysql开销，多对一相对开销还小，如果是一对多方式。比如查询所有的教室，对应的查询所有教室的学生，延迟加载就显得更为重要

* 测试

          @Test
          public void testSelectUserClassRoomById(){
              //加载mybatis-config.xml到输入流
              InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
              //通过输入流构建SqlSessionFactory对象
              SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
              //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
              SqlSession sqlSession = sqlSessionFactory.openSession();
              //通过SqlSession对象得到一个mapper的实现对象
              UserMapper mapper = sqlSession.getMapper(UserMapper.class);

              User user = mapper.selectUserClassRoomById(1);
              System.out.println(user);
          }

### 四、多对一之查询List(查询所有的用户，同时查询用户所在的班级信息)

* UserMapper.java

      //多表查询所有的用户和所在的班级名：（多对一）
      List<User> selesctUserClassRoom();

* 写法1 (association)

          <select id="selesctUserClassRoom" resultMap="UserClassRoomResultMap">
              select *,user.id u_id from user
              left join classroom
              on user.c_id=classroom.id
          </select>

* 写法2 (级联方式)

         <select id="selectAllPlus" resultMap="selectAllPlusResultMap">
             select user.*,classroom.name cname from user left join classroom
             on user.c_id=classroom.c_id
        </select>

* 写法3 (分步+延迟)

         <select id="selectAllPlus" resultMap="selectAllPlusResultMap3">
              select * from user
        </select>

     * 关闭延迟加载(虽然没有要求查询班级信息，但是仍然去查了班级，list中有多个用户，每个用户再分别去查自己的班级信息)
     
     * 开启延迟加载(没有用到班级信息，所以只发了一次sql请求)

* 测试

          @Test
          public void testSelesctUserClassRoom(){
              //加载mybatis-config.xml到输入流
              InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
              //通过输入流构建SqlSessionFactory对象
              SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
              //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
              SqlSession sqlSession = sqlSessionFactory.openSession();
              //通过SqlSession对象得到一个mapper的实现对象
              UserMapper mapper = sqlSession.getMapper(UserMapper.class);

              List<User> list = mapper.selesctUserClassRoom();

              System.out.println(list);
          }

--------------------------一对多--------------------------------------

>ClassRoom.java

      private int cId;
      private String name;
      private List<User> userList;


### 五、一对多之查询单对象(查询一个班级，同时查询班级里的用户信息)

* ClassroomMapper.java

      //根据id查询某个班级，同时查出这个班级里所有用户的信息
      Classroom selectClassRoomUserById(int id);

* ClassroomMapper.xml

  <resultMap type="ClassRoom" id="classRoomUserResultMap">
      <id property="cId" column="c_id"/>
      <result property="name" column="name"/>
      <collection property="userList" ofType="User">
          <id property="id" column="id"/>
          <result property="userName" column="user_name"/>
          <result property="gender" column="gender"/>
          <result property="email" column="email"/>
      </collection>
  </resultMap>
  <select id="selectClassRoomUserById" resultMap="ClassroomUserResultMap">
      select *,user.id u_id from classroom
      left join user
      on user.c_id=classroom.id
      where classroom.id=#{id}
  </select>

* 测试

          @Test
          public void testSelectClassRoomUserById(){
              //加载mybatis-config.xml到输入流
              InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
              //通过输入流构建SqlSessionFactory对象
              SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
              //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
              SqlSession sqlSession = sqlSessionFactory.openSession();
              //通过SqlSession对象得到一个mapper的实现对象
              ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

              Classroom classroom = mapper.selectClassRoomUserById(1);
              System.out.println(classroom);
          }

### 六、一对多之查询List

* ClassroomMapper.java

      //查询所有信息，返回一个list
      List<Classroom> selectClassRoomUser();

* ClassroomMapper.xml

      <select id="selectClassRoomUser" resultMap="ClassroomUserResultMap">
           select classroom.*,user.id,user.user_name,user.gender,user.email
             from classroom left join user
             on classroom.id=user.c_id
      </select>

* 测试

          @Test
          public void testSelectClassRoomUser(){
              //加载mybatis-config.xml到输入流
              InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
              //通过输入流构建SqlSessionFactory对象
              SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
              //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
              SqlSession sqlSession = sqlSessionFactory.openSession();
              //通过SqlSession对象得到一个mapper的实现对象
              ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

              List<Classroom> classroomList = mapper.selectClassRoomUser();
              System.out.println(classroomList);
          }

































