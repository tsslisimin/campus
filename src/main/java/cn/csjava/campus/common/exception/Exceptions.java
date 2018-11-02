package cn.csjava.campus.common.exception;

import cn.csjava.campus.common.results.Results;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketTimeoutException;
import java.util.Optional;

/**
 * @author：hcqi .
 * des:
 * email:hechuanqi.top@gmail.com
 * date: 2018/3/6
 */
class Exceptions {
    static Results ofException(HttpServletRequest req, Exception e) {
        if (e instanceof NotOfficeXmlFileException) {
            return Results.fail(400, "文件已损坏");
        }
        Integer code = (Integer) Optional.ofNullable(req.getAttribute("javax.servlet.error.status_code")).orElse(500);
        String message = String.valueOf(Optional.ofNullable(req.getAttribute("javax.servlet.error.message")).orElse("服务器异常"));
        return Results.fail(code, message);
//        return Results.ofError(ResultEntry.serverError());
    }

}
