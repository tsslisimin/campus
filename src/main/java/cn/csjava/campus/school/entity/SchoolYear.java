package cn.csjava.campus.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/11 0011
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SchoolYear {
    @JsonProperty("year_id")
    private String yearId;
    @JsonProperty("year_name")
    private String  yearName;
    @JsonProperty("start_year")
    private String startYear;
    @JsonProperty("end_year")
    private String endYear;
}
