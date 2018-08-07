# MyBatis进阶用法之动态SQL

* 动态 SQL 元素和使用 JSTL 或其他类似基于 XML 的文本处理器相似

* 主要标签：
    
    * if
    
    * trim (where, set)
    
    * foreach

### 一、if标签

* 使用场景1：查询用户，搜索条件可以是user_name,gender,email中的任意多个，使用if标签可以方便快速的解决，省去繁琐的sql拼接代码

* 使用场景2：类似的在修改时，因为我们不知道具体修改哪几列，我们需要先查询出数据的全部数据，再把要修改的数据做替换。而利用If标签可以只对要修改的列进行修改，不需要先查询数据

* UserMapper.java

        //if使用之搜索
        List<User> selectByConditionIf(Map<String,String> map);

* UserMapper.xml

       <select id="selectByConditionIf" resultType="com.edu.bean.User">
            select * from user where 1=1
            <if test="name!=null">
              and user_name = #{name}
            </if>
            <if test="gender!=null">
                and gender = #{gender}
            </if>
            <if test="email!=null">
                and email = #{email}
            </if>
        </select>

* 测试

        @Test
        public void testSelectByConditionIf(){
            //加载mybatis-config.xml到输入流
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过输入流构建SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
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

### 二、Where标签

* UserMapper.java

        //if使用之搜索
        List<User> selectByConditionIf(Map<String,String> map);

* UserMapper.xml

        <select id="selectByConditionIf" resultType="com.edu.bean.User">
            select * from user
            <where>
                <if test="name!=null">
                    and user_name = #{name}
                </if>
                <if test="gender!=null">
                    and gender = #{gender}
                </if>
                <if test="email!=null">
                    and email = #{email}
                </if>
            </where>
        </select>

* 测试

        @Test
        public void testSelectByConditionIf(){
            //加载mybatis-config.xml到输入流
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过输入流构建SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
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

































