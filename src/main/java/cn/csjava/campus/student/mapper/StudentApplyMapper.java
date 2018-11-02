package cn.csjava.campus.student.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.student.controller.vo.StudentRequest;
import cn.csjava.campus.student.entity.StudentApplyEntity;
import cn.csjava.campus.student.entity.StudentEntity;
import cn.csjava.campus.user.controller.vo.UserStudentResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/11 0011
 */
@Component
public interface StudentApplyMapper extends BaseMapper<StudentApplyEntity> {

    int updateStudentTemplate(@Param("saId") Long saId, @Param("templateId") Long templateId,
                              @Param("updateTime") Date updateTime, @Param("status") int status,
                              @Param("image") String url);

    List<UserStudentResponse> findStudentTemplate(@Param("userId") String userId);

    List<UserStudentResponse> findStudentDetails(@Param("entity") StudentRequest entity);

    long unsetTemplateId(Long id);

    StudentApplyEntity findImageById(long id);
}
