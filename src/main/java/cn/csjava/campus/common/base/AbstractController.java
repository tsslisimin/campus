package cn.csjava.campus.common.base;

import cn.csjava.campus.common.results.HttpResponseCodeEnum;
import cn.csjava.campus.common.results.PageResults;
import cn.csjava.campus.common.results.Results;

/**
 * @author：hcqi .
 * describe: 基类控制器
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/1
 */
public abstract class AbstractController {

    protected Results ok(Object data) {
        return Results.ok(data);
    }

    protected Results ok() {
        return ok(null);
    }

    protected Results fail(Integer code, String message) {
        return Results.fail(code, message);
    }

    protected Results fail(String message) {
        return Results.fail(HttpResponseCodeEnum.PARAMS_ERROR.getCode(), message);
    }

    protected Results fail(HttpResponseCodeEnum codeEnum, String message) {
        return Results.fail(codeEnum, message);
    }

    protected Results fail(HttpResponseCodeEnum codeEnum) {
        return Results.fail(codeEnum);
    }

    protected Results empty() {
        return PageResults.empty();
    }

    protected Results ok(Integer page, Integer count, Integer totalCount) {
        return PageResults.ok(page, count, totalCount);
    }
}
