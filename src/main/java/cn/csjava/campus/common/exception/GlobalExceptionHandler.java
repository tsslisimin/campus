package cn.csjava.campus.common.exception;

import cn.csjava.campus.common.results.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author：hcqi .
 * des:全局异常处理器
 * email:hechuanqi.top@gmail.com
 * date: 2017/10/16
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    public Results error(HttpServletResponse resp, HttpServletRequest req, Exception e) {
        logger.error("error", e);
        return Exceptions.ofException(req, e);
    }
}
