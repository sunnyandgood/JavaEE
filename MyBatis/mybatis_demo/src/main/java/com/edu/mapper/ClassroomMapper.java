package com.edu.mapper;

import com.edu.bean.Classroom;

import java.util.List;

public interface ClassroomMapper {
    //根据id查询某个班级，同时查出这个班级里所有用户的信息
    Classroom selectClassRoomUserById(int id);
    //查询所有信息，返回一个list
    List<Classroom> selectClassRoomUser();
}
