# MyBatis映射文件配置之CRUD

### 一、查找单个对象

* 如果就一个普通参数，上面的id可以换成其他任意字符，多个参数的情况见后

      public interface UserMapper {
          public User selectById(int id);
      }

      <mapper namespace="com.weixin.dao.UserMapper">
        <select id="selectById" resultType="User">
                select * from user where id=#{id}
        </select>   
      </mapper>
      
### 二、查找多个对象放到List中

* 在select标签中，id,resultType为必须属性 当要返回List<User>时，resultType的值还应该是User，还有一个重要属性为resultMap，见后

      public interface UserMapper {
          public List<User> selectAll();
      }

      <mapper namespace="com.weixin.dao.UserMapper">
         <select id="selectAll" resultType="User">
             select * from user
        </select>  
      </mapper>

### 三、增加，删除，修改，(写法基本一致)

* 增加删除修改标签中，只有id属性是必须的，如果java接口上类型是boolean，那么结果就是true或者false，如果接口上写int，返回结果就是操作行数

      public boolean insert(User user);
      public boolean deleteById(int id);
      public boolean update(User user);

      或

      public int insert(User user);
      public int deleteById(int id);
      public int update(User user);

* 例子

      <insert id="insert"  useGeneratedKeys="true" keyProperty="id">
          insert into user(user_name,gender,email) values(#{userName},#{gender},#{email})
      </insert>
      
      <delete id="deleteById">
        delete from user where id=#{_id}
      </delete>
      
      <update id="update">
        update user set user_name=#{userName},gender=#{gender},email=#{email} 
          where id=#{id}
      </update>

     -------------使用时---------------------

      //mybatis默认开启事务，执行完毕执行需要提交事务
      session.commit();


















