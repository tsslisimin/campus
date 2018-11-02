package cn.csjava.campus.school.services;

import cn.csjava.campus.school.controller.vo.SchoolYearVenueRequest;
import cn.csjava.campus.school.controller.vo.SchoolYearVenueResponse;
import cn.csjava.campus.school.entity.SchoolYearEntity;
import cn.csjava.campus.school.entity.SchoolYearVenueEntity;

import java.util.List;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7 0007
 */
public interface SchoolYearVenueService {
    Integer addSchoolYearVuen(SchoolYearVenueRequest syvRequest);

    List<SchoolYearVenueResponse> schoolYearVenueList(SchoolYearVenueEntity copy);

    Integer updateSchoolYearVenue(Integer id, Integer status);

    SchoolYearVenueEntity findSchoolYearVenue(Integer id);

    int editSchoolYearVenue(SchoolYearVenueEntity schoolYearVenueEntity);

    int deleteSchoolYearVenue(Integer id);
}
