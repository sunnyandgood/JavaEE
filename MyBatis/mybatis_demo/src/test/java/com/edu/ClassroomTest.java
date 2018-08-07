package com.edu;

import com.edu.bean.Classroom;
import com.edu.mapper.ClassroomMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ClassroomTest {
    //加载mybatis-config.xml到输入流
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    //通过输入流构建SqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


    public ClassroomTest() throws IOException {
    }

    @Test
    public void testSelectClassRoomUserById(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

        Classroom classroom = mapper.selectClassRoomUserById(1);
        System.out.println(classroom);
    }

    @Test
    public void testSelectClassRoomUser(){
        //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession对象得到一个mapper的实现对象
        ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

        List<Classroom> classroomList = mapper.selectClassRoomUser();
        System.out.println(classroomList);
    }
}
