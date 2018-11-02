package cn.csjava.campus.school.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.school.controller.vo.SchoolYearVenueResponse;
import cn.csjava.campus.school.entity.SchoolYearVenueEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7 0007
 */
@Component
public interface SchoolYearVenueMapper extends BaseMapper<SchoolYearVenueEntity> {
    List<SchoolYearVenueResponse> selectSchoolYearVenueJoin(@Param("param") SchoolYearVenueEntity copy);

    int updateSchoolYearVenue(@Param("id") Integer id, @Param("status") Integer status);

    List<SchoolYearVenueEntity> findYearByStatus(@Param("entity") SchoolYearVenueEntity entity);

    int update(@Param("schoolYearVenueEntity") SchoolYearVenueEntity schoolYearVenueEntity);

}
