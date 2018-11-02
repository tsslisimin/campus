package cn.csjava.campus.user.services;

import cn.csjava.campus.user.controller.vo.UserRequest;
import cn.csjava.campus.user.controller.vo.UserStudentResponse;

import java.util.List;

/**
 * 用户服务
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/8 0008
 */
public interface UserService {
    /**
     * 注册
     * @param userRequest
     * @return
     */
    Long createUser(UserRequest userRequest);

    /**
     * 查询家长的孩子
     * @param userId
     * @return
     */
    List<UserStudentResponse> getPatriarchChild(String userId);
}
