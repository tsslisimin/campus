package cn.csjava.campus.openapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author：hcqi .
 * describe: 用户基本信息 回复
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/2
 */
@Data
public class UserBasicResponse {
    @JsonProperty("userid")
    private String userId;
    private String name;

}
