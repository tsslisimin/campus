package cn.csjava.campus.common.exception;

import cn.csjava.campus.common.results.Results;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author：hcqi .
 * des:
 * email:hechuanqi.top@gmail.com
 * date: 2018/3/30
 */
@RestController
public class GlobalErrorControllerHandler extends GlobalExceptionHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public Results errorMapping(HttpServletResponse resp, HttpServletRequest req, Exception e) {
        return error(resp, req, e);
    }
}
