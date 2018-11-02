package cn.csjava.campus.school.services;

import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.school.entity.SchoolYearEntity;
import cn.csjava.campus.school.controller.vo.SchoolYearRequest;
import cn.csjava.campus.school.mapper.SchoolYearMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7
 */
@Service
public class SchoolYearServiceImpl implements SchoolYearService {

    @Autowired
    private SchoolYearMapper schoolYearMapper;

    @Override
    public Integer createSchoolYear(SchoolYearRequest schoolYear) {
        SchoolYearEntity copy = new SchoolYearEntity();
        BeanUtils.copyProperties(schoolYear, copy);
        SchoolYearEntity schools = schoolYearMapper.selectByStartYear(copy.getStartYear());
        if (null == schools) {
            copy.setFormatYear(schoolYear.getStartYear() + "-" + schoolYear.getEndYear());
            int insert = schoolYearMapper.insert(copy);
            return insert;
        }
        return null;
    }

    @Override
    public Integer deleteSchoolYear(Integer id) {
        int i = schoolYearMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return i;
        }
        return null;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(SchoolYearRequest schoolYear) {
        SchoolYearEntity entity = new SchoolYearEntity();
        BeanUtils.copyProperties(schoolYear, entity);
        List<SchoolYearEntity> list = schoolYearMapper.select(SchoolYearEntity.builder().formatYear(schoolYear.getFormatYear()).build());
        if (!list.isEmpty()) {
            entity = list.get(0);
            entity.setFormatYear(schoolYear.getFormatYear());
            entity.setStartYear(schoolYear.getStartYear());
            entity.setEndYear(schoolYear.getEndYear());
            schoolYearMapper.updateByEntity(entity);
        } else {

            schoolYearMapper.insert(entity);
        }

        return 1;
    }

    @Override
    public SchoolYearEntity findBySchoolStatus(Integer status) {
        SchoolYearEntity build = SchoolYearEntity.builder().enable(status).build();
        SchoolYearEntity schoolYearEntity = schoolYearMapper.selectOne(build);
        if (null != schoolYearEntity) {
            return schoolYearEntity;
        }
        return null;
    }

    @Override
    public Integer updateSchoolYear(Integer id, Integer status) {
        SchoolYearEntity schoolYearEntity = schoolYearMapper.selectByPrimaryKey(id);
        if (null != schoolYearEntity) {
            SchoolYearEntity build = SchoolYearEntity.builder().enable(1).build();
            SchoolYearEntity schoolYear = schoolYearMapper.selectOne(build);//查询是否有在启用的
            if (null != schoolYear) {
                schoolYearMapper.updateSchoolYear(2, schoolYear.getId());
            }
            int i = schoolYearMapper.updateSchoolYear(status, id.longValue());
            return i;
        }
        return null;
    }

    @Override
    public List<SchoolYearEntity> schoolYearList(SchoolYearEntity copy) {
        List<SchoolYearEntity> select = schoolYearMapper.select(copy);
        return select;
    }
}
