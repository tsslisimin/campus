package cn.csjava.campus.school.services;

import cn.csjava.campus.school.entity.SchoolYearEntity;
import cn.csjava.campus.school.controller.vo.SchoolYearRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学年信息服务
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7
 */
public interface SchoolYearService {
    /**
     * 修改学年状态  启用  禁用
     * @param id
     * @param status
     * @return
     */
    Integer updateSchoolYear(Integer id, Integer status);

    /**
     * 查询所有学年信息
     * @param copy
     * @return
     */
    List<SchoolYearEntity> schoolYearList(SchoolYearEntity copy);

    /**
     * 添加学年信息
     * @param syRequest
     * @return
     */
    Integer createSchoolYear(SchoolYearRequest syRequest);

    /**
     * 删除学年信息
     * @param id
     * @return
     */
    Integer deleteSchoolYear(Integer id);

    @Transactional(rollbackFor = Exception.class)
    int save(SchoolYearRequest schoolYear);

    SchoolYearEntity findBySchoolStatus(Integer status);
}
