package cn.csjava.campus.user.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.user.controller.vo.UserStudentResponse;
import cn.csjava.campus.user.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/8 0008
 */
@Component
public interface UserMapper  extends BaseMapper<UserEntity> {
    List<UserStudentResponse> getPatriarchChild (@Param("userId") String userId);
}
