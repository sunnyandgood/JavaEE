# MyBatis进阶用法之复杂映射

### 一、resultMap

* 有时候普通的resultType满足不了开发需要，不能个性化的定义javabean和列值间的对应关系，我们需要替换成另一个属性resultMap

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










































