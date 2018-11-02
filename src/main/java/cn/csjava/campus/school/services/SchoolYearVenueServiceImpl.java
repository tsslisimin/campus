package cn.csjava.campus.school.services;

import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.school.controller.vo.SchoolYearVenueResponse;
import cn.csjava.campus.school.entity.SchoolYearEntity;
import cn.csjava.campus.school.entity.SchoolYearVenueEntity;
import cn.csjava.campus.school.controller.vo.SchoolYearVenueRequest;
import cn.csjava.campus.school.mapper.SchoolYearMapper;
import cn.csjava.campus.school.mapper.SchoolYearVenueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7 0007
 */
@Service
public class SchoolYearVenueServiceImpl implements SchoolYearVenueService {

    @Autowired
    private SchoolYearVenueMapper schoolYearVenueMapper;
    @Autowired
    private SchoolYearMapper schoolYearMapper;

    @Override
    public List<SchoolYearVenueResponse> schoolYearVenueList(SchoolYearVenueEntity copy) {
        List<SchoolYearVenueResponse> list = new ArrayList<>();
        List<SchoolYearVenueResponse> syvResponse = schoolYearVenueMapper.selectSchoolYearVenueJoin(copy);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (SchoolYearVenueResponse yearVenueResponse : syvResponse) {
            if(null != yearVenueResponse.getStartDate() && null != yearVenueResponse.getEndDate() ){
                String var = sdf.format(yearVenueResponse.getStartDate())+"~~"+sdf.format(yearVenueResponse.getEndDate());
                yearVenueResponse.setStartEndDate(var);
                list.add(yearVenueResponse);
            }
        }
        return list;
    }

    @Override
    public Integer updateSchoolYearVenue(Integer id, Integer status) {
        if (status == 1) { //开启招生
//            SchoolYearEntity build = SchoolYearEntity.builder().id(id.longValue()).enable(1).build();
//            SchoolYearEntity schoolYearEntity = schoolYearMapper.selectOne(build);
//            if(null != schoolYearEntity && schoolYearEntity.getEnable() == 1){
            int row = schoolYearVenueMapper.updateSchoolYearVenue(id, status);
            if (row > 0)
                return row;
//            }else{

//            }
        } else if (status == 2) { //关闭招生
            int row = schoolYearVenueMapper.updateSchoolYearVenue(id, status);
            if (row > 0)
                return row;
        }
        return null;
    }

    @Override
    public SchoolYearVenueEntity findSchoolYearVenue(Integer id) {
        return schoolYearVenueMapper.selectOne(SchoolYearVenueEntity.builder().syvId(id.longValue()).build());
    }

    @Override
    public int editSchoolYearVenue(SchoolYearVenueEntity schoolYearVenueEntity) {
        int update = schoolYearVenueMapper.update(schoolYearVenueEntity);
        return update;
    }

    @Override
    public int deleteSchoolYearVenue(Integer id) {
        SchoolYearVenueEntity schoolYearVenueEntity = schoolYearVenueMapper.selectOne(SchoolYearVenueEntity.builder().syvId(id.longValue()).build());
        if(null != schoolYearVenueEntity)
            return schoolYearVenueMapper.delete(schoolYearVenueEntity);
        return 0;
    }

    @Override
    public Integer addSchoolYearVuen(SchoolYearVenueRequest syvRequest) {
        SchoolYearVenueEntity schoolYearVenue = new SchoolYearVenueEntity();
        BeanUtils.copyProperties(syvRequest,schoolYearVenue);
        List<SchoolYearVenueResponse> syvResponse = schoolYearVenueMapper.selectSchoolYearVenueJoin(schoolYearVenue);
        for (SchoolYearVenueResponse schoolYearVenueResponse : syvResponse) {
            boolean before = schoolYearVenueResponse.getEndDate().before(syvRequest.getStartDate());
                if (false == before)
                    return 500;
        }
        SchoolYearVenueEntity schoolYearVenueEntity = new SchoolYearVenueEntity();
        BeanUtils.copyProperties(syvRequest,schoolYearVenueEntity);
        int insert = schoolYearVenueMapper.insert(schoolYearVenueEntity);
        if (insert > 0) {
            return insert;
        }
        return null;
    }


}
