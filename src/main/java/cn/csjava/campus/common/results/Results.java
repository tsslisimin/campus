package cn.csjava.campus.common.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collection;

/**
 * @author：hcqi .
 * describe:结果回复
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Results {
    private Integer code;
    @JsonProperty("msg")
    private String message;
    private Object data;
    private int count;

    public static Results ok(Object data) {
        ResultsBuilder builder = Results.builder().code(0)
                .message("成功")
                .data(data);
        if (data != null) {
            if (data instanceof Collection) {
                Collection c = (Collection) data;
                builder.count(c.size());
            }
        }
        return builder
                .build();
    }

    public static Results ok() {
        return ok(null);
    }

    public static Results fail(Integer code, String message) {
        return Results.builder().code(code)
                .message(message)
                .build();
    }

    public static Results fail(HttpResponseCodeEnum codeEnum, String message) {
        return Results.builder().code(codeEnum.getCode())
                .message(message)
                .build();
    }

    public static Results fail(HttpResponseCodeEnum codeEnum) {
        return Results.builder().code(codeEnum.getCode())
                .message(codeEnum.getMessage())
                .build();
    }


}
