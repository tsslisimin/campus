package cn.csjava.campus.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author：hcqi .
 * des:日志记录拦截器
 * email:hechuanqi.top@gmail.com
 * date: 2017/10/10
 */
public class LoggerInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Origin","*");
        response.addHeader("Access-Control-Allow-Methods","GET,POST,DELETE,PUT");
        printInfo(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
  }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }


    private void printInfo(HttpServletRequest request) {


        logger.info("<<======================== Request info  Begin =======================>");
        logger.info("method={}", request.getMethod());
        logger.info("requestURI={}", request.getRequestURI());
        logger.info("pathInfo={}", request.getPathInfo());
        logger.info("queryString={}", request.getQueryString());
        logger.info("remoteUser={}", request.getRemoteAddr());
        logger.info("contentType={}", request.getContentType());
        logger.info("contentLength={}", request.getContentLengthLong());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            logger.info("headers 　key={}  value={}", key, request.getHeader(key));
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            logger.info("parameter 　key={}  value={}", key, request.getParameter(key));
        }
        logger.info("<<======================== Request info End =======================>");
    }
}
