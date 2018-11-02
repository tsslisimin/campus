package cn.csjava.campus.question.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/12
 */
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "check_point")
public class CheckPointEntity extends AbstractEntity {
    @Id private Long id;
    private String regular;
    private String describes;
    private Date createTime;
}
