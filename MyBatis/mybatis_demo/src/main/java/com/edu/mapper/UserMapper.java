package com.edu.mapper;

import com.edu.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询所有数据
    List<User> selectAll();
    //根据id查询一条语句
    User selectById(int id);
    //增加用户
    int inser(User user);
    //删除用户
    boolean deleteById(int id);
    //修改用户
    boolean update(User user);

    //修改用户、参数是map
    boolean updateWithMap(Map<String,String> map);

    /**
     * @param list
     * @return
     */
    //查询、参数是list
    List<User> selectByCondition(@Param("list") List<String> list);//默认是list，可通过@Param修改映射

    //多参数
    List<User> selectNameGender(@Param("name") String n,@Param("gender") String g);


    //多表:查询某个用户和所在的班级信息: (多对一)
    User selectUserClassRoomById(int id);

    //多表查询所有的用户和所在的班级名：（多对一）
    List<User> selesctUserClassRoom();



    //传入一个javabean参数返回list
    List<User>selectAllByConditionBean(User user);
    //传入一个map参数返回list
    List<User>selectAllByConditionMap(Map<String,String> map);



    //if使用之搜索（where）
    List<User> selectByConditionIf(Map<String,String> map);

    //set、if使用之update
    boolean updateIf(User user);

    //trim使用之插入
    boolean insertTrim(User user);

    //删除所有id对应的数据
    boolean deleteForEach(@Param("ids") List<String> ids);
}

