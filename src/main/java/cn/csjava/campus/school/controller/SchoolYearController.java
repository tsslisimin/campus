package cn.csjava.campus.school.controller;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.CampusResults;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.openapi.manager.CampusApiManager;
import cn.csjava.campus.school.controller.vo.SchoolYearResponse;
import cn.csjava.campus.school.entity.SchoolYear;
import cn.csjava.campus.school.entity.SchoolYearDetailResponse;
import cn.csjava.campus.school.entity.SchoolYearEntity;
import cn.csjava.campus.school.controller.vo.SchoolYearRequest;
import cn.csjava.campus.school.services.SchoolYearService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import org.apache.ibatis.session.RowBounds;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 学年信息控制
 *
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7
 */
@Api(description = "学年信息")
@RestController
@RequestMapping("school/year")
public class SchoolYearController extends AbstractController {

    @Autowired
    private SchoolYearService schoolYearService;
    @Autowired
    private CampusApiManager campusApiManager;

    @GetMapping
    public Results schoolYearList(SchoolYearRequest syRequest, Integer page, Integer limit) {
        List<SchoolYearResponse> schoolYearResponsesList = new ArrayList<SchoolYearResponse>();
        SchoolYearResponse syr;
        CampusResults<SchoolYearDetailResponse> schoolYear = campusApiManager.getSchoolYear(1, 10);
        SchoolYearDetailResponse data = schoolYear.getData();
        List<SchoolYear> dataList = data.getDataList();
        if (null != dataList) {
            for (SchoolYear year : dataList) {
                syr = new SchoolYearResponse();
                BeanUtils.copyProperties(year, syr);
                schoolYearResponsesList.add(syr);
                schoolYearService.save(SchoolYearRequest.builder()
                        .startYear(year.getStartYear())
                        .endYear(year.getEndYear())
                        .formatYear(year.getYearName()).build());
            }
        }
        return ok(schoolYearResponsesList);
    }


    @PostMapping
    public Results addSchoolYear(SchoolYearRequest syRequest) {

        CampusResults<SchoolYearDetailResponse> schoolYear = campusApiManager.getSchoolYear(1, 10);
        SchoolYearDetailResponse data = schoolYear.getData();


        DateTime dateTime = new DateTime(syRequest.getStartYear()).plusYears(1);
        syRequest.setEndYear(dateTime.toString("yyyy"));
        Integer bySchoolYear = schoolYearService.createSchoolYear(syRequest);
        if (null != bySchoolYear) {
            return ok();
        }
        return fail(0, "重复操作");
    }

    @PostMapping("/update")
    public Results updateSchoolYear(Integer id, Integer status) {
        Integer integer = schoolYearService.updateSchoolYear(id, status);
        if (null != integer) {
            return ok();
        }
        return fail(0, "错误操作");
    }

    @PostMapping("/delete")
    public Results deleteSchoolYear(Integer id) {
        Integer integer = schoolYearService.deleteSchoolYear(id);
        if (null != integer) {
            return ok();
        }
        return fail(0, "错误操作");
    }

    @GetMapping("/findBySchoolStatus")
    public Results findBySchoolStatus(Integer status) {
        SchoolYearEntity schoolYearEntity = schoolYearService.findBySchoolStatus(status);
        if (null != schoolYearEntity) {
            return ok(schoolYearEntity);
        }
        return fail(500, "请求异常");
    }

}
