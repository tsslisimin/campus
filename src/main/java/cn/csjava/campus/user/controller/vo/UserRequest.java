package cn.csjava.campus.user.controller.vo;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/8 0008
 */
@Data
public class UserRequest extends AbstractEntity {

    /**
     * 自增长ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;
    /**
     * IP
     */
    private String ip;
    /**
     * 手机号
     */
    @NotNull(message = "手机号码不能为空")
    private String mobile;
    /**
     *状态
     */
    private String status;
    /**
     *角色
     */
    private  String role;

}
