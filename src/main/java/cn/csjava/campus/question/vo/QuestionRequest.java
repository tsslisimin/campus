package cn.csjava.campus.question.vo;

import cn.csjava.campus.common.options.map.ObjectHashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionRequest {

    private Long templateId;
    private List<Item> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private int index;
        private long dependId;
        private long dependOptionId;
        private Integer questionType;
        private Integer status;
        private Integer itemId;
        private String describes;
        private Boolean editorType;
        private int inputText;
        /**
         * 是否是健康问卷
         */
        private int health;

        private List<ObjectHashMap> options;
    }


}
