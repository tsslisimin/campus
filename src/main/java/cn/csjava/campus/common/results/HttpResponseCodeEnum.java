package cn.csjava.campus.common.results;


/**
 * @author：hcqi .
 * describe: http 回复code码 枚举  所有回复必须在此定义 通用回复可不定义message
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/2
 */
public enum HttpResponseCodeEnum {
    PARAMS_ERROR(400, "参数错误"),
    NOT_FOUND_ERROR(404, "数据不存在"),
    DELETE_ERROR(550, "删除失败"),
    UPDATE_ERROR(550, "数据已存在或者修改失败"),
    INSERT_ERROR(550, "数据已存在或者新增失败"),
    SERVER_ERROR(500, "服务器异常"),
    COMMON_ERROR(400);

    private Integer code;
    private String message;

    HttpResponseCodeEnum(Integer code) {
        this.code = code;
    }

    HttpResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
