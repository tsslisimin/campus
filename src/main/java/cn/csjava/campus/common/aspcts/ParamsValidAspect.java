package cn.csjava.campus.common.aspcts;

import cn.csjava.campus.common.results.HttpResponseCodeEnum;
import cn.csjava.campus.common.results.Results;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author：hcqi .
 * describe: 参数校验 拦截器  方法参数返回值 必须为 {@link Results}
 * 方法签名必须带有 {@link BindingResult} 否则释放拦截
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/2
 */
@Aspect
@Component
public class ParamsValidAspect {

    @Pointcut("execution(cn.csjava.campus.common.results.Results cn.csjava.campus.*.controller.*.*(..)))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Optional<BindingResult> optional = Arrays.stream(pjp.getArgs())
                .filter(o -> o instanceof BindingResult)
                .map(o -> (BindingResult) o).findFirst();
        if (optional.isPresent()) {
            BindingResult result = optional.get();
            if (result.hasErrors()) {
                FieldError fieldError = result.getFieldError();
                String message = Optional.ofNullable(fieldError)
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("缺少参数");
                return Results.fail(HttpResponseCodeEnum.PARAMS_ERROR, message);
            }
        }

        return pjp.proceed();
    }
}
