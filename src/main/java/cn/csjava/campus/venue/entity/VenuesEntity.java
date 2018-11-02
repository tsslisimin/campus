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
public class VenuesEntity extends AbstractEntity {
    /**
     * 自定义ID
     */
    private String venuesId;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 状态  1-空闲 ，2 预约使用中
     */
    private Integer status;
    /**
     * 可以容纳人数
     */
    private Integer number;
    /**
     * 是否有投影仪
     */
    private Integer hasProjector;
    /**
     * 信息来源
     */
    private String source;
    /**
     * 地址
     */
    private String address;
    /**
     * 说明
     */
    private String remark;
    /**
     * 场馆名称
     */
    private String venuesName;
}
