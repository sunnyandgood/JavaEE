# MyBatis进阶用法之复杂映射

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

* classroom.sql

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

### 三、多对一之查询单对象（查询一个用户，同时查询用户所在的班级信息）

> User.java

    private int id;
    private String userName;
    private String gender;
    private String email;
    private ClassRoom classRoom;

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
