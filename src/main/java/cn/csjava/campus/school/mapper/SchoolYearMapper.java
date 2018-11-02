package cn.csjava.campus.school.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.school.entity.SchoolYearEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7 0007
 */
@Component
public interface SchoolYearMapper extends BaseMapper<SchoolYearEntity> {

    SchoolYearEntity selectByStartYear( String startYear);

    int updateSchoolYear(@Param("status") Integer status,@Param("id") Long id);

    long updateByEntity(@Param("entity") SchoolYearEntity entity);
}
