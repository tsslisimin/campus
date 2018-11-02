package cn.csjava.campus.student.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.student.entity.StudentEntity;
import cn.csjava.campus.student.entity.StudentExcel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/11 0011
 */
@Component
public interface StudentMapper extends BaseMapper<StudentEntity> {
    int updateByCardNo(@Param("entity") StudentEntity entity);


    List<StudentEntity> queryByCondition(@Param("entity") StudentEntity entity);
}
