package cn.csjava.campus.school.controller;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.school.controller.vo.SchoolYearVenueRequest;
import cn.csjava.campus.school.controller.vo.SchoolYearVenueResponse;
import cn.csjava.campus.school.entity.SchoolYearEntity;
import cn.csjava.campus.school.entity.SchoolYearVenueEntity;
import cn.csjava.campus.school.services.SchoolYearVenueService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("school/year/venue")
public class SchoolYearVenueController extends AbstractController {
    @Autowired
    private SchoolYearVenueService schoolYearVenueService;

    @GetMapping
    public Results schoolYearVenueList(SchoolYearVenueRequest syvRequest, Integer page, Integer limit) {
        RowBounds rowBounds = new RowBounds(page, limit);
        Page pages = PageHelper.startPage(page, limit, true);


        SchoolYearVenueEntity copy = new SchoolYearVenueEntity();
        BeanUtils.copyProperties(syvRequest, copy);
        List<SchoolYearVenueResponse> schoolYearvEenueEntities = schoolYearVenueService.schoolYearVenueList(copy);
        return ok(schoolYearvEenueEntities);
    }

    @PostMapping
    public Results addSchoolYearVenue(SchoolYearVenueRequest formParam) {
        Integer integer = schoolYearVenueService.addSchoolYearVuen(formParam);
        if (1 == integer) {
            return ok();
        } else if (500 == integer) {
            return fail(500, "添加的开始时间冲突了");
        }
        return fail(500, "添加失败");
    }

    @PostMapping("/updateSchoolYearVenue")
    public Results updateSchoolYearVenue(Integer id, Integer status) {
        Integer row = schoolYearVenueService.updateSchoolYearVenue(id, status);
        if (null != row && row > 0) {
            return ok();
        }
        return fail(500, "修改失败");
    }

    @GetMapping("/findSchoolYearVenue")
    public Results findSchoolYearVenue(Integer id) {
        SchoolYearVenueEntity schoolYearVenueEntity = schoolYearVenueService.findSchoolYearVenue(id);
        if (null != schoolYearVenueEntity) {
            return ok(schoolYearVenueEntity);
        }
        return fail(500, "查询信息错误");
    }

    @PostMapping("/editSchoolYearVenue")
    public Results editSchoolYearVenue(SchoolYearVenueEntity entity, Integer syvId) {
        entity.setSyvId(syvId.longValue());
        int row = schoolYearVenueService.editSchoolYearVenue(entity);
        if (row > 0)
            return ok();
        return fail(500, "修改失败");
    }

    @DeleteMapping
    public Results deleteSchoolYearVenue(Integer id) {
        int row = schoolYearVenueService.deleteSchoolYearVenue(id);
        if (row > 0)
            return ok();
        return fail(500, "删除失败");
    }
}
