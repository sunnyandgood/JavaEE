# SpringDI之使用外部配置数据源(数据库连接)

### 一、原始方法

    @Test
    public void testC3p0() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mybatis");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");
        comboPooledDataSource.getConnection();
    }
    
### 二、使用spring

* 1、xml配置

      <bean id="dataBase1" class="com.mchange.v2.c3p0.ComboPooledDataSource">
          <property name="driverClass" value="com.mysql.jdbc.Driver"/>
          <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/mybatis"/>
          <property name="user" value="root"/>
          <property name="password" value="root"/>
      </bean>

* 2、测试

      @Test
      public void testC3p01() throws SQLException {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
          DataSource dataSource = (DataSource) applicationContext.getBean("dataBase1");
          //DataSource dataSource = applicationContext.getBean(DataSource.class);
          dataSource.getConnection();
      }

### 三、使用spring之db.properties

* 1、在resource资源文件夹下新建db.properties

      jdbc.JdbcUrl=jdbc:mysql://localhost:3306/mybatis
      jdbc.driverClass=com.mysql.jdbc.Driver
      jdbc.user=root
      jdbc.password=root

* 2、xml配置

      <context:property-placeholder location="classpath:db.properties"/>
      <bean id="dataBase2" class="com.mchange.v2.c3p0.ComboPooledDataSource">
          <property name="driverClass" value="${jdbc.driverClass}"/>
          <property name="jdbcUrl" value="${jdbc.JdbcUrl}"/>
          <property name="user" value="${jdbc.user}"/>
          <property name="password" value="${jdbc.password}"/>
      </bean>

* 3、测试

      @Test
      public void testC3p01() throws SQLException {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
          DataSource dataSource = (DataSource) applicationContext.getBean("dataBase2");
          //DataSource dataSource = applicationContext.getBean(DataSource.class);
          dataSource.getConnection();
      }
















