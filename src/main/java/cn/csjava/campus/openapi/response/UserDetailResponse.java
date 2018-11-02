package cn.csjava.campus.openapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author：hcqi .
 * describe: 用户详情 回复
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/2
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDetailResponse {

    @JsonProperty("name")
    private String name;
    @JsonProperty("wxuserid")
    private String wxUserId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("user_no")
    private String userNo;
    @JsonProperty("head")
    private String head;
    @JsonProperty("is_subscribe")
    private String isSubscribe;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("join_date")
    private String joinDate;
    @JsonProperty("duty")
    private String duty;
    @JsonProperty("teach_title")
    private String teachTitle;
    @JsonProperty("departid")
    private String departId;
    @JsonProperty("wxdepartid")
    private String wxDepartId;
    @JsonProperty("cellphone")
    private String cellphone;
    @JsonProperty("other_departid")
    private List<String> otherDepartId;
    @JsonProperty("other_wxdepartid")
    private List<String> otherWxDepartId;


}
