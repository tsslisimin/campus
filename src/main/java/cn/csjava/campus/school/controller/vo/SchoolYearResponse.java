package cn.csjava.campus.school.controller.vo;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.Data;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/15 0015
 */
@Data
public class SchoolYearResponse extends AbstractEntity {
    /**
     * 学年Id
     */
    private String yearId;
    /**
     * 格式化后时间  2017-2018
     */
    private String  yearName;
    /**
     * 学年开始时间
     */
    private String startYear;
    /**
     * 学年结束时间
     */
    private String endYear;
}
