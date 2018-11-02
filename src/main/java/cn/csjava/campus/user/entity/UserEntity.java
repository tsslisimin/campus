package cn.csjava.campus.user.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.*;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/8 0008
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "user")
@Data
@Builder
public class UserEntity extends AbstractEntity {

    /**
     * 自增长ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * IP
     */
    private String ip;
    /**
     * 手机号
     */
    private String mobile;
    /**
     *状态
     */
    private String status;
    /**
     *角色
     */
    private  String role;
    /**
     * 登入次数
     */
    private Integer enterNumber;
    /**
     * 登入错误次数
     */
    private Integer enterErrorNumber;
    /**
     * 创建时间
     */
    private Date createDate;
}
