# SpringMVC_文件上传

* 导入jar包

      <!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
      <dependency>
          <groupId>commons-fileupload</groupId>
          <artifactId>commons-fileupload</artifactId>
          <version>1.3.3</version>
      </dependency>

* springmvc.xml中配置

      <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
          <property name="defaultEncoding" value="utf-8"/>
          <property name="maxUploadSize" value="5000000"/>  <!--单位字节-->
      </bean>

### 一、小体验

* 表单

      <%--
        Created by IntelliJ IDEA.
        User: sunny
        Date: 2018/8/17
        Time: 20:23
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <html>
      <head>
          <title>文件上传</title>
      </head>
      <body>
          <form action="${ctx}/upload" method="post" enctype="multipart/form-data">
              <input type="file" name="file"/>
              <input type="submit" value="提交">
          </form>
      </html>

* UpLoadController.java

      package com.edu.controller;

      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.RequestMapping;
      import org.springframework.web.multipart.MultipartFile;


      @Controller
      public class UpLoadController {

          @RequestMapping("/upload")
          public String upload(MultipartFile file){
              System.out.println(file.getOriginalFilename());
              return "redirect:/hello.jsp";
          }
      }

### 二、文件上传

* 表单

      <%--
        Created by IntelliJ IDEA.
        User: sunny
        Date: 2018/8/17
        Time: 20:23
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <html>
      <head>
          <title>文件上传</title>
      </head>
      <body>
          <form action="${ctx}/upload" method="post" enctype="multipart/form-data">
              <input type="file" name="file"/>
              <input type="submit" value="提交">
          </form>
      </html>

* UpLoadController.java

      package com.edu.controller;

      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.RequestMapping;
      import org.springframework.web.multipart.MultipartFile;

      import javax.servlet.http.HttpServletRequest;
      import java.io.File;
      import java.io.IOException;


      @Controller
      public class UpLoadController {

          @RequestMapping("/upload")
          public String upload(MultipartFile file, HttpServletRequest request){
              //System.out.println(request.getServletContext().getRealPath("/"));
              String realPath = request.getServletContext().getRealPath("/resources/upload");
              File file1 = new File(realPath,file.getOriginalFilename());
              try {
                  file.transferTo(file1);
              } catch (IOException e) {
                  e.printStackTrace();
              }
              return "redirect:/hello.jsp";
          }
      }

### 三、改变文件名预防同名覆盖

* 表单

      <%--
        Created by IntelliJ IDEA.
        User: sunny
        Date: 2018/8/17
        Time: 20:23
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <html>
      <head>
          <title>文件上传</title>
      </head>
      <body>
          <form action="${ctx}/upload" method="post" enctype="multipart/form-data">
              <input type="file" name="file"/>
              <input type="submit" value="提交">
          </form>
      </html>

* UpLoadController.java

      package com.edu.controller;

      import org.springframework.stereotype.Controller;
      import org.springframework.web.bind.annotation.RequestMapping;
      import org.springframework.web.multipart.MultipartFile;

      import javax.servlet.http.HttpServletRequest;
      import java.io.File;
      import java.io.IOException;
      import java.util.UUID;

      @Controller
      public class UpLoadController {
          @RequestMapping("/upload")
          public String upload(MultipartFile file, HttpServletRequest request){
              //得到上传路径的硬盘路径
              String realPath = request.getServletContext().getRealPath("/resources/upload");

              //得到文件扩展名
              String oldFilename = file.getOriginalFilename();
              String ext = oldFilename.substring(oldFilename.indexOf("."));

              //新文件名
              String newFileName = UUID.randomUUID() + ext;

              System.out.println(newFileName);
              File file1 = new File(realPath,newFileName);
              try {
                  //存文件
                  file.transferTo(file1);
              } catch (IOException e) {
                  e.printStackTrace();
              }
              return "redirect:/hello.jsp";
          }
      }

### 四、ajax异步上传之单图显示：

* 使用插件：hfileuploadify(fileupload山寨版): [文件下载](https://github.com/ComputerEreop/Huploadify)

* 使用步骤:

     * 1.导入 hfileuploadify的css及js(它依赖jquery，得把jquery也导入)

     * 2.自己不要写file控件了,在想出现上传控件的地方按顺序添加3个div
     
         >eg:
      
            <input type="hidden" name="xx"/>    <%--(回填表单,用于提交表单的图片数据库存储)--%>
            <div class="imageUpload"></div>   <%--(上传控件)--%>
            <div class="preview"></div>       <%-- (预览)--%>

* 实例

     * 表单

            <%--
              Created by IntelliJ IDEA.
              User: sunny
              Date: 2018/8/17
              Time: 20:23
              To change this template use File | Settings | File Templates.
            --%>
            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <html>
            <head>
                <title>文件上传</title>
                <jsp:include page="/resources/layout/_css.jsp"/>
                <link rel="stylesheet" href="${ctx}/resources/css/plugins/Huploadify/Huploadify.css"/>
            </head>
            <body>

                <input type="text" name="xx"/>    <%--(回填表单,用于提交表单的图片数据库存储)--%>
                <div class="imageUpload"></div>   <%--(上传控件)--%>
                <div class="preview"></div>       <%-- (预览)--%>

            </body>
            <jsp:include page="/resources/layout/_script.jsp"/>
            <script src="${ctx}/resources/js/plugins/Huploadify/jquery.Huploadify.js"></script>
            <script>
            $('.imageUpload').each(function(){
                var aa = function(obj){
                    obj.Huploadify({
                        auto:true,
                        fileTypeExts:'*.*;',
                        multi:true,
                        //formData:{name:'image0'},
                        fileSizeLimit:9999,
                        showUploadedPercent:true,//是否实时显示上传的百分比，如20%
                        showUploadedSize:true,
                        removeTimeout:9999999,
                        uploader:'${ctx}/upload',
                        onUploadComplete:function(file,data,response){
                            //获得回填数据
                            data_json= JSON.parse(data);
                            src = "${ctx}"+data_json.path;
                            //此处obj为上传控件  ,代表每个imageUpload
                            //回填表单
                            obj.prev().val(data_json.path);
                            //填充预览图
                            obj.next().html("<img src='"+src+"' style='height:250px'/>");
                            setTimeout(function(){
                                obj.find('.uploadify-queue-item').html('');
                            },1000);
                        }
                    });
                }
                aa($(this));
            });
            </script>
            </html>



     * UpLoadController.java

            package com.edu.controller;

            import com.edu.util.R;
            import org.springframework.stereotype.Controller;
            import org.springframework.web.bind.annotation.RequestMapping;
            import org.springframework.web.bind.annotation.ResponseBody;
            import org.springframework.web.multipart.MultipartFile;

            import javax.servlet.http.HttpServletRequest;
            import java.io.File;
            import java.io.IOException;
            import java.util.UUID;

            @Controller
            public class UpLoadController {
                @ResponseBody
                @RequestMapping("/upload")
                public R upload(MultipartFile file, HttpServletRequest request){
                    //得到上传路径的硬盘路径
                    String realPath = request.getServletContext().getRealPath("/resources/upload");

                    //得到文件扩展名
                    String oldFilename = file.getOriginalFilename();
                    String ext = oldFilename.substring(oldFilename.indexOf("."));

                    //新文件名
                    String newFileName = UUID.randomUUID() + ext;

                    System.out.println(newFileName);
                    File file1 = new File(realPath,newFileName);
                    try {
                        //存文件
                        file.transferTo(file1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return R.ok().put("path","/resources/upload/" + newFileName);
                }
            }

### 五、ajax异步上传之多图显示：

* 使用插件：hfileuploadify(fileupload山寨版): [文件下载](https://github.com/ComputerEreop/Huploadify)

* 使用步骤:

     * 1.导入 hfileuploadify的css及js(它依赖jquery，得把jquery也导入)

     * 2.自己不要写file控件了,在想出现上传控件的地方按顺序添加3个div
     
         >eg:
      
            <input type="hidden" name="xx"/>    <%--(回填表单,用于提交表单的图片数据库存储)--%>
            <div class="imageUpload"></div>   <%--(上传控件)--%>

* 实例

     * 表单

            <%--
              Created by IntelliJ IDEA.
              User: sunny
              Date: 2018/8/17
              Time: 20:23
              To change this template use File | Settings | File Templates.
            --%>
            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <html>
            <head>
                <title>文件上传</title>
                <jsp:include page="/resources/layout/_css.jsp"/>
                <link rel="stylesheet" href="${ctx}/resources/css/plugins/Huploadify/Huploadify.css"/>
            </head>
            <body>

                <input type="text" name="xx"/>    <%--(回填表单,用于提交表单的图片数据库存储)--%>
                <div class="imageUpload"></div>   <%--(上传控件)--%>

            </body>
            <jsp:include page="/resources/layout/_script.jsp"/>
            <script src="${ctx}/resources/js/plugins/Huploadify/jquery.Huploadify.js"></script>
            <script>
            $('.imageUpload').each(function(){
              var aa = function(obj){
                  obj.Huploadify({
                      auto:true,
                      fileTypeExts:'*.jpg;*.png;',
                      multi:true,
                      //formData:{name:'image0'},
                      fileSizeLimit:9999,
                      showUploadedPercent:true,//是否实时显示上传的百分比，如20%
                      showUploadedSize:true,
                      removeTimeout:9999999,
                      uploader:'${ctx}/upload',
                      onUploadComplete:function(file,data,response){
                          //获得回填数据
                          data_json = JSON.parse(data);
                          src = "${ctx}"+data_json.path;
                          //此处obj为上传控件  ,代表每个imageUpload
                          //回填表单
                          obj.prev().val(data_json.path);
                          //填充预览图
                          str = '<div class="preview"></div>';
                          obj.after(str);
                          obj.next().append("<img src='"+src+"'/>");
                          setTimeout(function(){
                              obj.find('.uploadify-queue-item').html('');
                          },1000);
                      }
                  });
               }
               aa($(this));
            });
            </script>
            </html>

     * UpLoadController.java

            package com.edu.controller;

            import com.edu.util.R;
            import org.springframework.stereotype.Controller;
            import org.springframework.web.bind.annotation.RequestMapping;
            import org.springframework.web.bind.annotation.ResponseBody;
            import org.springframework.web.multipart.MultipartFile;

            import javax.servlet.http.HttpServletRequest;
            import java.io.File;
            import java.io.IOException;
            import java.util.UUID;

            @Controller
            public class UpLoadController {
                @ResponseBody
                @RequestMapping("/upload")
                public R upload(MultipartFile file, HttpServletRequest request){
                    //得到上传路径的硬盘路径
                    String realPath = request.getServletContext().getRealPath("/resources/upload");

                    //得到文件扩展名
                    String oldFilename = file.getOriginalFilename();
                    String ext = oldFilename.substring(oldFilename.indexOf("."));

                    //新文件名
                    String newFileName = UUID.randomUUID() + ext;

                    System.out.println(newFileName);
                    File file1 = new File(realPath,newFileName);
                    try {
                        //存文件
                        file.transferTo(file1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return R.ok().put("path","/resources/upload/" + newFileName);
                }
            }

### 六、文件上传优化

* 使用插件：hfileuploadify(fileupload山寨版): [文件下载](https://github.com/ComputerEreop/Huploadify)

* 使用步骤:

     * 1.导入 hfileuploadify的css及js(它依赖jquery，得把jquery也导入)

     * 2.自己不要写file控件了,在想出现上传控件的地方按顺序添加3个div
     
         >eg:
      
            <div class="imageUpload"></div>   <%--(上传控件)--%>

* my.js

      /**
       * @param el         上传按钮el
       * @param multi      是否支持多文件上传 value=true|false
       * @param url        上传地址
       * @param webpath    web根路径
       */
      function upload(el,multi,url,webpath){
          el.each(function(){
              var aa = function(obj){
                  obj.Huploadify({
                      auto:true,
                      fileTypeExts:'*.jpg;*.png;',
                      multi:multi,
                      //formData:{name:'image0'},
                      fileSizeLimit:9999,
                      showUploadedPercent:true,//是否实时显示上传的百分比，如20%
                      showUploadedSize:true,
                      removeTimeout:9999999,
                      uploader:url,
                      onInit:function(file){
                      },
                      onUploadComplete:function(file,path,response){
                          //获得回填数据
                          src = webpath+path;
                          //此处obj为上传控件  ,代表每个imageUpload
                          //回填表单
                          obj.before('<input type="text" name="'+obj.attr('name')+'" value="'+path+'"/>');
                          //填充预览图
                          obj.after('<img src="'+src+'" style="height: 100px;"/>');
                          setTimeout(function(){
                              obj.find('.uploadify-queue-item').html('');
                          },1000);
                      }
                  });
              }
              aa($(this));
          });
      }


* 实例

     * 表单

            <%--
              Created by IntelliJ IDEA.
              User: sunny
              Date: 2018/8/18
              Time: 9:05
              To change this template use File | Settings | File Templates.
            --%>
            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <html>
            <head>
                <title>Title</title>
                <jsp:include page="/resources/layout/_css.jsp"/>
                <link rel="stylesheet" href="${ctx}/resources/css/plugins/Huploadify/Huploadify.css">
            </head>
            <body>

                  <h1>上传相册</h1>
                  <form action="${ctx}/uptest1">
                      <div id="imageUpload" name="img"></div>    <%--   (上传控件)--%>
                      <input type="submit" value="提交">
                  </form>

                  <h1>上传头像</h1>
                  <form action="${ctx}/uptest2">
                      <div id="imageUpload2" name="img2"></div>    <%--   (上传控件)--%>
                      <input type="submit" value="提交">
                  </form>

            </body>
            <jsp:include page="/resources/layout/_script.jsp"/>
            <script src="${ctx}/resources/js/plugins/Huploadify/jquery.Huploadify.js"></script>
            <script src="${ctx}/resources/js/my.js"></script>
            <script>
                upload($('#imageUpload'),true,'${ctx}/upload2','${ctx}');
                upload($('#imageUpload2'),false,'${ctx}/upload2','${ctx}');
            </script>
            </html>        

     * UpLoadController.java

            package com.edu.controller;

            import com.edu.util.R;
            import org.springframework.stereotype.Controller;
            import org.springframework.web.bind.annotation.PostMapping;
            import org.springframework.web.bind.annotation.RequestMapping;
            import org.springframework.web.bind.annotation.ResponseBody;
            import org.springframework.web.multipart.MultipartFile;

            import javax.servlet.http.HttpServletRequest;
            import java.io.File;
            import java.io.IOException;
            import java.util.Arrays;
            import java.util.UUID;

            @Controller
            public class UpLoadController {

                @ResponseBody
                @PostMapping("/upload2")
                public String upload(MultipartFile file, HttpServletRequest request) {
                    String dir = request.getServletContext().getRealPath("/resources/upload");
                    String oldName = file.getOriginalFilename();
                    String ext = oldName.substring(oldName.lastIndexOf("."));
                    String newName = UUID.randomUUID()+ext;
                    System.out.println(newName);
                    File file2 = new File(dir,newName);
                    try {
                        file.transferTo(file2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return "/resources/upload/"+newName;
                }

                @RequestMapping("/uptest1")
                @ResponseBody
                public R uptest(String[] img){
                    System.out.println(Arrays.toString(img));
                    return R.ok();
                }

                @RequestMapping("/uptest2")
                @ResponseBody
                public R uptest2(String img2){
                    System.out.println(img2);
                    return R.ok();
                }
            }
           



