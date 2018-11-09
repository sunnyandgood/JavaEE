# 数据库操作之jdbcTemplate

* 一、数据库建表
```sql
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for student
-- ----------------------------
CREATE TABLE `student` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `birthday` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
```
* 二、实体类(StudentEntity类)
```java
package com.edu.bean;

import java.util.Date;

public class StudentEntity {
  private int id;
  private String name;
  private Date birthday;

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  @Override
  public String toString() {
    return "StudentEntity [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
  }
}
```
* 三、数据库连接（BaseDao类）
```java
package com.edu.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class BaseDao {
  public static DataSource dataSource = new ComboPooledDataSource();

  public JdbcTemplate geTemplate() {
    return new JdbcTemplate(dataSource);
  }
}
```
* 四、dao包(StudentDao接口)
```java
package com.edu.dao;

import java.util.List;

import com.edu.bean.StudentEntity;

public interface StudentDao {
  /**
   * 增加一条数据
   * @param studentEntity
   * @return
   */
  boolean insert(StudentEntity studentEntity);
  /**
   * 根据id删除一条数据
   * @param studentEntity
   * @return
   */
  boolean deleteById(int id);
  /**
   * 根据id更新一条数据
   * @param id
   * @return
   */
  boolean update(StudentEntity studentEntity);
  /**
   * 根据id查询一条数据
   * @param studentEntity
   * @return
   */
  StudentEntity selectById(int id);
  /**
   * 查询所有数据
   * @return
   */
  List<StudentEntity> selectAll();

  /**
   * 查询数据条数
   * @return
   */
  int selectCount();
}
```
* 五、StudentDao接口的实现类(StudentDaoImpl类)
```java
package com.edu.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.edu.bean.StudentEntity;
import com.edu.dao.BaseDao;
import com.edu.dao.StudentDao;
import com.edu.util.C3p0Util;

public class StudentDaoImpl extends BaseDao implements StudentDao{

  @Override
  public boolean insert(StudentEntity studentEntity) {
    // TODO Auto-generated method stub
    String sql = "insert into student (name,birthday) values(?,?)";
    return geTemplate().update(sql,studentEntity.getName(),studentEntity.getBirthday()) > 0;
  }

  @Override
  public boolean deleteById(int id) {
    // TODO Auto-generated method stub
    String sql = "delete from student where id=?";
    return geTemplate().update(sql,id) > 0;
  }

  @Override
  public boolean update(StudentEntity studentEntity) {
    // TODO Auto-generated method stub
    String sql = "update student set name=?,birthday=? where id=?";
    return C3p0Util.jdbcTemplate.update(sql,studentEntity.getName(),
              studentEntity.getBirthday(),studentEntity.getId()) > 0;
  }

  @Override
  public StudentEntity selectById(int id) {
    // TODO Auto-generated method stub
    String sql = "select * from student where id=?";
    return geTemplate().queryForObject(sql, new BeanPropertyRowMapper<>(StudentEntity.class),id);

  }

  @Override
  public List<StudentEntity> selectAll() {
    // TODO Auto-generated method stub
    String sql = "select * from student";
    return geTemplate().query(sql, new BeanPropertyRowMapper<>(StudentEntity.class));
  }

  @Override
  public int selectCount() {
    // TODO Auto-generated method stub
    String sql = "select count(*) from student";
    return geTemplate().queryForObject(sql, Integer.class);
  }

}
```
* 六、测试类
```java
package com.edu;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.edu.bean.StudentEntity;
import com.edu.dao.StudentDao;
import com.edu.dao.impl.StudentDaoImpl;

public class StudentTest {
  private StudentDao studentDao = new StudentDaoImpl();

  @Test
  public void testinsert() {
    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setName("小明");
    studentEntity.setBirthday(new Date());
    boolean insert = studentDao.insert(studentEntity);
    System.out.println(insert);
  }

  @Test
  public void testDelete() {
    boolean deleteById = studentDao.deleteById(2);
    System.out.println(deleteById);
  }

  @Test
  public void testUpdate() {
    StudentEntity studentEntity  = studentDao.selectById(3);
    studentEntity.setName("小花");
    boolean update = studentDao.update(studentEntity);
    System.out.println(update);
  }

  @Test
  public void testSelectById() {
    StudentEntity studentEntity = studentDao.selectById(1);
    System.out.println(studentEntity);
  }

  @Test
  public void testSelectAll() {
    List<StudentEntity> list = studentDao.selectAll();
    System.out.println(list);
  }

  @Test
  public void testSelectCount() {
    int selectCount = studentDao.selectCount();
    System.out.println(selectCount);
  }
}
```
