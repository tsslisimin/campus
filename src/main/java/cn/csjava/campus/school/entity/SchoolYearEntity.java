package cn.csjava.campus.school.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hcqi .
 * describe:学年信息
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/5
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "school_year")
@Builder
public class SchoolYearEntity extends AbstractEntity {
    /**
     * id 序号 自增长
     */
    @Id
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
