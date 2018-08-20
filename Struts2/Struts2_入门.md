# Struts2_入门

### 一、Struts2简介

* 1、Struts2 是一个MVC框架，以WebWork框架的设计思想为核心，吸收了Struts1 的部分优点。

* 2、Struts2 拥有更加广阔的前景，自身功能强大，还对其他框架下开发的程序提供很好的兼容性。

    <div align="center"><img src="./img/Struts2.png"/></div>

* 3、[Struts官方地址](http://struts.apache.org)

* 4、Struts2 `Struts 2.3.15.1`目录结构


    <div align="center"><img src="./img/目录结构.png"/></div>

     * apps目录：Struts2示例应用程序
     * docs目录：Struts2指南、向导、API文档
     * lib目录：Struts 2的发行包及其依赖包
     * src目录：Struts 2项目源代码

### 二、Struts2使用说明

* 使用Struts 2 开发程序的基本步骤

    * 1、[加载Struts2 类库](./struts2-lib-xml)

    * 2、配置web.xml文件

    * 3、开发视图层页面

    * 4、开发控制层Action（创建一个`xxxAction`继承自`com.opensymphony.xwork2.Action`并实现`execute()`方法）

    * 5、配置struts.xml文件

    * 6、部署、运行项目

* 1、Struts2 类库介绍

    <table>
       <tr>
          <td>文件名</td>
          <td>说   明</td>
       </tr>
       <tr>
          <td>struts2-core-xxx.jar</td>
          <td>Struts 2框架的核心类库</td>
       </tr>
       <tr>
          <td>xwork-core-xxx.jar</td>
          <td>XWork类库，Struts 2的构建基础</td>
       </tr>
       <tr>
          <td>ognl-xxx.jar</td>
          <td>Struts 2使用的一种表达式语言类库</td>
       </tr>
       <tr>
          <td>freemarker-xxx.jar</td>
          <td>Struts 2的标签模板使用类库</td>
       </tr>
       <tr>
          <td>javassist-xxx.GA.jar</td>
          <td>对字节码进行处理</td>
       </tr>
       <tr>
          <td>commons-fileupload-xxx.jar</td>
          <td>文件上传时需要使用</td>
       </tr>
       <tr>
          <td>commons-io-xxx.jar</td>
          <td>Java IO扩展</td>
       </tr>
       <tr>
          <td>commons-lang-xxx.jar</td>
          <td>包含了一些数据类型的工具类</td>
       </tr>
    </table>

* 2、配置 `web.xml`

        <filter>
            <filter-name>struts2</filter-name>
            <filter-class>
                org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter
            </filter-class>
        </filter>
        <filter-mapping>
            <filter-name>struts2</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>

* 3、配置struts.xml文件

    >eg:

        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE struts PUBLIC
            "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
            "http://struts.apache.org/dtds/struts-2.0.dtd">
        <struts>
            <!-- 创建一个default包，继承自Struts2的struts-default包 -->
            <package name="default" namespace="/" extends="struts-default">
                <!-- 接收处理用户的/hello.action请求 ， 并根据返回结果，完成跳转 -->
                <action name="hello" class="com.edu.acction.HelloAction">
                    <!-- 结果为"sucess"时，跳转至hello.jsp页面 -->
                    <result name="success">hello.jsp</result>
                </action>
            </package>
        </struts>

### 三、初体验

* 1、[加载Struts2 类库](./struts2-lib-xml)

* 2、配置web.xml文件

* 3、开发视图层页面

* 4、开发控制层Action

    * 创建一个`xxxAction`继承自`com.opensymphony.xwork2.Action`并实现`execute()`方法

            package com.edu.acction;

            import com.opensymphony.xwork2.Action;

            public class HelloAction implements Action{
                @Override
                public String execute() throws Exception {
                    System.out.println("Hello world!");
                    return "success";
                }
            }


* 5、配置struts.xml文件

        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE struts PUBLIC
            "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
            "http://struts.apache.org/dtds/struts-2.0.dtd">
        <struts>
            <!-- 创建一个default包，继承自Struts2的struts-default包 -->
            <package name="default" namespace="/" extends="struts-default">
                <!-- 接收处理用户的/hello.action请求 ， 并根据返回结果，完成跳转 -->
                <action name="hello" class="com.edu.acction.HelloAction">
                    <!-- 结果为"sucess"时，跳转至hello.jsp页面 -->
                    <result name="success">hello.jsp</result>
                </action>
            </package>
        </struts>

### 四、登录demo

* 1、[加载Struts2 类库](./struts2-lib-xml)

* 2、配置web.xml文件

* 3、开发视图层页面

* 4、开发控制层Action

* 5、配置struts.xml文件






