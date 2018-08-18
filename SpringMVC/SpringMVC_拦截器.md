# SpringMVC_拦截器

### 一、在`spring_mvc.xml`中添加配置

    <mvc:interceptors>
        <bean class="com.edu.interceptor.ActionLogInterceptor"/>
    </mvc:interceptors>

### 二、拦截器

    package com.edu.interceptor;

    import org.apache.log4j.Logger;
    import org.springframework.web.servlet.HandlerInterceptor;
    import org.springframework.web.servlet.ModelAndView;

    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.text.DateFormat;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.Map;

    public class ActionLogInterceptor implements HandlerInterceptor {
        private Logger logger = Logger.getLogger(ActionLogInterceptor.class);

        private long startTime = 0;
        private String url = null;
        private String viewName = null;
        private Date date = null;
        private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse httpServletResponse,
                                 Object handler) throws Exception {
            startTime = System.currentTimeMillis();
            date = new Date();

            url = request.getRequestURI();

            //如果访问静态资源，不打印：
            if (!url.contains("resources")) {
                logger.debug("--------------action请求-----------:"
                        + dateFormat.format(date));
                String str = "";
                if (handler != null) {
                    System.out.println(handler);
                    str = handler.toString().trim();
                    //去掉修饰符和参数
                    int index = str.indexOf(" ");
                    if (index != -1) str = str.substring(index, str.length() - 1).trim();
                    index = str.indexOf(" ");
                    if (index != -1) str = str.substring(index + 1, str.length() - 1).trim();
                    index = str.indexOf("(");
                    if (index != -1) str = str.substring(0, index).trim();
                }
                logger.debug("url: " + url);
                logger.debug("method: " + request.getMethod());
                logger.debug("调用方法：" + str);
                logger.debug("参数：");
                Map parameterMap = request.getParameterMap();
                for (Object key : parameterMap.keySet()) {
                    String name = (String) key;
                    String value = request.getParameter(name);
                    logger.debug(name + " : " + value);
                }
            }
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse,
                               Object o, ModelAndView modelAndView)
                throws Exception {
            if (modelAndView != null)
                viewName = modelAndView.getViewName();
        }

        @Override
        public void afterCompletion(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Object handler, Exception e)
                throws Exception {
            if (!url.contains("resources")) {
                if (viewName != null) {
                    logger.debug("viewName: " + viewName);
                }
                logger.debug("耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
                logger.debug("----------------------: " + dateFormat.format(new Date()));
            }
        }

    }

