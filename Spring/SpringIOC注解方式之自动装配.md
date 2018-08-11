# SpringIOC注解方式之自动装配

### 一、自动装配

* 使用注解使ioc容器维护bean，但是bean之间的关联关系我们可以使用 `@Autowired` , 此注解可以写在**属性上，构造方法上，setter方法上** 

* spring容器会首先根据`变量名 = bean的id名`(此处id必须是手动指定的，默认的不行)去匹配，如果匹配不上再根据类型查找容器中相应的bean进行关联

  >eg:
  
      @Autowired
      private StudentDao studentDao;
      //首先比对id名和变量名是否相同，比对不上就选类名去填补。  

* 默认容器中必须有匹配bean，如果允许可以没有此类型的bean，那么可以@Autowired(required=false)

* 如果容器中按类型匹配有多个匹配类型，我们一般可以使用`@Qualifier`,后边必须指出所需id。

  >eg:


      @Autowired
      @Qualifier(value = "studentDaoImpl2")
      private StudentDao studentDao;
      //@Qualifier：指定所需的id
      
      //或：
      @Autowired
      @Qualifier("studentDAOImpl")
      private StudentDAO studentDAO;
      
      //或：
      @Autowired
      @Qualifier("studentDAOImpl")
      public void setStudentDAO(StudentDAO studentDAO) {
          this.studentDAO = studentDAO;
      }
      
      //或：
      @Autowired
      public void setStudentDAO(@Qualifier("studentDAOImpl")StudentDAO studentDAO) {
          this.studentDAO = studentDAO;
      }

### 二、应用














