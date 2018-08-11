package com.edu.mapper;

import com.edu.bean.Classroom;
import com.edu.bean.ClassroomExample;
import java.util.List;

public interface ClassroomMapper {
    int countByExample(ClassroomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Classroom record);

    int insertSelective(Classroom record);

    List<Classroom> selectByExample(ClassroomExample example);

    Classroom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classroom record);

    int updateByPrimaryKey(Classroom record);
}