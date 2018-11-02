package cn.csjava.campus.school.controller.vo;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.*;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7 0007
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchoolYearRequest extends AbstractEntity {
    /**
     * id 序号 自增长
     */
    private Long id;
    /**
     * 学年开始时间
     */
    private String startYear;
    /**
     * 学年结束时间
     */
    private String endYear;
    /**
     * 格式化后时间  2017-2018
     */

    private String formatYear;
    /**
     * 状态  开启  禁用
     */
    private Integer enable;
}
