package cn.csjava.campus.question.vo;

import cn.csjava.campus.common.configures.JsonDateSerializer;
import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.question.entity.QuestionTemplateEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
public class QuestionTemplateRequest {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Find {
        private Long id;
        /**
         * 开始时间
         */
        private String startDate;
        /**
         * 结束时间
         */
        private String endDate;
        /**
         * 创建时间
         */
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createDate;
        /**
         * 状态
         */
        private Integer status;
        /**
         * 描述
         */
        private String describes;

        public Find(QuestionTemplateEntity o) {
            BeanUtils.copyProperties(o, this);
            setStartDate(new DateTime(o.getStartDate()).toString("yyyy-MM:dd HH:mm:ss"));
            setEndDate(new DateTime(o.getEndDate()).toString("yyyy-MM:dd HH:mm:ss"));
        }
    }

    @Data
    public static class Add {
        /**
         * 开始时间
         */
        @NotNull(message = "开始时间不能为空")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date startDate;
        /**
         * 结束时间
         */
        @NotNull(message = "结束时间不能为空")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date endDate;
        /**
         * 状态
         */
        private Integer status;
        /**
         * 描述
         */
        private String describes;

    }
}
