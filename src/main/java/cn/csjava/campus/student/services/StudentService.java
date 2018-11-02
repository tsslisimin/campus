package cn.csjava.campus.student.services;

import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.student.controller.vo.StudentApplyRequest;
import cn.csjava.campus.student.controller.vo.StudentApplyResponse;
import cn.csjava.campus.student.controller.vo.StudentRequest;
import cn.csjava.campus.student.controller.vo.StudentResponse;
import cn.csjava.campus.student.entity.StudentApplyEntity;
import cn.csjava.campus.student.entity.StudentEntity;
import cn.csjava.campus.user.controller.vo.UserStudentResponse;
import cn.csjava.campus.user.entity.UserEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7 0007
 */
public interface StudentService {
    /**
     * 添加报名信息
     * @param studentApplyRequest
     * @return
     */
    Long addStudentApply(StudentApplyRequest studentApplyRequest);

    /**
     * 查询学生详细信息
     * @param saId
     * @return
     */
    StudentApplyResponse getStudentDetails(String saId);

    /**
     * 修改问卷状态
     * @param saId
     * @param templateId
     * @return
     */
    int updateStudentTemplate (Long saId,Long templateId);

    /**
     * 查询学生报名部分信息
     * @param userId
     * @return
     */
    List<UserStudentResponse> findStudentTemplate(String userId);

    /**
     * 后台查询学生报名所有信息
     * @return
     */
    List<UserStudentResponse> findStudentDetails(StudentRequest entity);

    int importStudent(String name, InputStream inputStream) throws IOException, Exception;

    void exportStudent(StudentEntity entity, HttpServletResponse response) throws Exception;

    List<StudentResponse> query(StudentEntity entity, Integer page, Integer limit);

    /**
     * 后台Exceli查询详情
     * @param id
     * @return
     */
    StudentResponse findStudentExcelDetails(Integer id);

    int updateStudentExcel(StudentEntity entity);

    /**
     * 删除学生信息
     * @param id
     * @return
     */
    int deleteStudent(Integer id);

    Object sumStudents();



    /**
     * 后台基本信息添加学生
     * @param entity
     * @return
     */
    int createStudent(StudentEntity entity);

    StudentApplyEntity findImageById(long id);
}
