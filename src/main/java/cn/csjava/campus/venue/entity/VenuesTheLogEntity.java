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
public class VenuesTheLogEntity extends AbstractEntity {

    /**
     * 自增长
     */
    private Long logId;
    /**
     * 自定义ID
     */
    private String theLogId;
    /**
     * 教师ID
     */
    private String teachersId;
    /**
     * 操作管理员
     */
    private String operationAdm;
    /**
     * 提交时间
     */
    private Date submitDate;
    /**
     * 操作时间（同意预定，拒绝预定）
     */
    private Date operationDate;
    /**
     * 状态   1 同意预定， 2 拒绝预定
     */
    private String venuesStatus;
    /**
     * 拒绝说明
     */
    private String instructions;
    /**
     * 场馆id
     */
    private String venuesId;
    /**
     * 预定座位数量
     */
    private Integer seatNum;
    /**
     * 预定开始时间
     */
    private Date reservationStart;
    /**
     * 预定结束时间
     */
    private Date reservationEnd;
    /**
     * 说明
     */
    private String remark;


}
