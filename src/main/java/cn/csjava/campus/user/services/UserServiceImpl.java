package cn.csjava.campus.user.services;

import cn.csjava.campus.common.ObjectId;
import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.student.mapper.StudentApplyMapper;
import cn.csjava.campus.user.controller.vo.UserRequest;
import cn.csjava.campus.user.controller.vo.UserStudentResponse;
import cn.csjava.campus.user.entity.UserEntity;
import cn.csjava.campus.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.LongConsumer;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/8 0008
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentApplyMapper studentApplyMapper;

    @Override
    public Long createUser(UserRequest userRequest) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userRequest, entity);
        UserEntity mobile = UserEntity.builder().password(entity.getPassword()).mobile(entity.getMobile()).role("1").build(); //角色为 1 就是普通用户
        UserEntity userEntity = userMapper.selectOne(mobile);
        if (null == userEntity) {
            entity.setRole("1");
            entity.setCreateDate(new Date());
            userMapper.insert(entity);
            Long id = entity.getId();
            return id;
        }
        return userEntity.getId();
    }

    @Override
    public List<UserStudentResponse> getPatriarchChild(String userId) {
        UserEntity build = UserEntity.builder().id(Long.valueOf(userId)).build();
        UserEntity entity = userMapper.selectOne(build);
        if (null != entity) {
            List<UserStudentResponse> list = userMapper.getPatriarchChild(entity.getId().toString());
            return list;
        }
        return null;
    }
}
