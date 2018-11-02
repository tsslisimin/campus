package cn.csjava.campus.venue.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VenuesJoinEntity extends AbstractEntity {
    /**
     * 自增长ID
     */
    private Long vjId;
    /**
     * 场馆ID
     */
    private String venuesId;
    /**
     * 管理员ID
     */
    private String userId;
    /**
     * 创建时间
     */
    private Date createDate;
}
