# Mybatis_Spring_SpringMVC的启动方式

### 一、Mybatis启动方式（读取配置文件）

    //加载mybatis-config.xml到输入流
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    //通过输入流构建SqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    //通过SqlSessionFactory对象得到一个SqlSession对象，每个数据库操作都依靠SqlSession
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //通过SqlSession对象得到一个mapper的实现对象
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    
    public UserTest() throws IOException {
    }


### 二、Spring启动方式（读取配置文件）

    //1.创建ioc容器对象：
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		//2.从容器中获得维护的Student实例
		
		//通过id
		Student student1 = (Student)applicationContext.getBean("student");
    //通过类名
    Student student2 = applicationContext.getBean(Student.class);

### 三、SpringMVC启动方式
