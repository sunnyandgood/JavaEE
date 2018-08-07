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

* 上面示例中，where后面跟了1=1,mybatis觉得这种处理方式不雅，加入where标签。可以智能识别 `and` 和`or`,把多出来的and或者or去掉

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

### 三、Set标签

* UserMapper.java

        //set、if使用之update
        boolean updateIf(User user);
* UserMapper.xml

       <update id="updateIf">
            update user
            <set>
                <if test="userName!=null">
                    user_name = #{userName},
                </if>
                <if test="gender!=null">
                    gender = #{gender},
                </if>
                <if test="email!=null">
                    email = #{email},
                </if>
            </set>
            where id = #{id}
        </update>

* 测试

        @Test
        public void testUpdateIf(){
            //加载mybatis-config.xml到输入流
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过输入流构建SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
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

### 四、Trim标签

* trim 可在之前之后都加元素，可代替where或者set(但是不建议)

    * trim替代where
    
            <select id="selectByConditionIf" resultType="com.edu.bean.User">
                select * from user
                <trim prefix="where" prefixOverrides="and">
                    <if test="name!=null">
                        and user_name = #{name}
                    </if>
                    <if test="gender!=null">
                        and gender = #{gender}
                    </if>
                    <if test="email!=null">
                        and email = #{email}
                    </if>
                </trim>
            </select>
    
    * trim替代set

            <update id="updateIf">
                  <trim prefix="set" suffixOverrides=",">
                      <if test="userName!=null">
                           user_name=#{userName},
                      </if>     
                      <if test="gender!=null">
                           gender=#{gender},
                      </if> 
                      <if test="email!=null">
                           email =#{email},
                      </if>     
                  </trim>
                  where id=#{id}
            </update>

* UserMapper.java

        //trim使用之插入
        boolean insertTrim(User user);

* UserMapper.xml

        <insert id="insertTrim">
                insert into user
                <trim prefix="(" suffixOverrides="," suffix=")">
                    <if test="userName!=null">
                        user_name,
                    </if>
                    <if test="gender!=null">
                        gender,
                    </if>
                    <if test="email!=null">
                        email,
                    </if>
                </trim>
                values
                <trim prefix="(" suffix=")" suffixOverrides=",">
                    <if test="userName!=null">
                        #{userName},
                    </if>
                    <if test="gender!=null">
                        #{gender},
                    </if>
                    <if test="email!=null">
                        #{email},
                    </if>
                </trim>
        </insert>

* 测试

        @Test
        public void testInsertTrim() {
            //加载mybatis-config.xml到输入流
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过输入流构建SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
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

### 五、Foreach标签

* 动态 SQL 的另外一个常用的必要操作是需要对一个集合进行遍历，通常是在构建 IN 条件语句的时候。比如：

* UserMapper.java

        //删除所有id对应的数据
        boolean deleteForEach(@Param("ids") List<String> ids);

* UserMapper.xml

        <delete id="deleteForEach">
            delete from user
            where id in
            <foreach collection="ids" open="(" close=")" separator="," item="id">
                #{id}
            </foreach>
        </delete>

* 测试

        @Test
        public void testDeleteForEach(){
            //加载mybatis-config.xml到输入流
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过输入流构建SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);    
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












