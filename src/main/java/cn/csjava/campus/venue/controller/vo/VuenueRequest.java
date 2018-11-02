package cn.csjava.campus.venue.controller.vo;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/2 0002
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VuenueRequest extends AbstractEntity {
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
    @NotNull(message = "容乃人数为空")
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
