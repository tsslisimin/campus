package cn.csjava.campus.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：yyl .
 * describe: 学年信息
 * email:zxc7752948@qq.com
 * date: 2018/7/11 0011
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SchoolYearDetailResponse {
    @JsonProperty("dataList")
    private List<SchoolYear> dataList = new ArrayList<SchoolYear>();
}
