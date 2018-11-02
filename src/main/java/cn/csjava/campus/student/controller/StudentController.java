package cn.csjava.campus.student.controller;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.CampusResults;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.openapi.manager.CampusApiManager;
import cn.csjava.campus.question.service.QuestionService;
import cn.csjava.campus.school.entity.SchoolYearDetailResponse;
import cn.csjava.campus.student.controller.vo.StudentApplyRequest;
import cn.csjava.campus.student.controller.vo.StudentApplyResponse;
import cn.csjava.campus.student.controller.vo.StudentRequest;
import cn.csjava.campus.student.controller.vo.StudentResponse;
import cn.csjava.campus.student.entity.StudentApplyEntity;
import cn.csjava.campus.student.entity.StudentEntity;
import cn.csjava.campus.student.services.StudentService;
import cn.csjava.campus.user.controller.vo.UserStudentResponse;
import cn.csjava.campus.user.entity.UserEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 学生控制
 *
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7 0007
 */
@Api(description = "学生")
@RestController
@RequestMapping("student")
public class StudentController extends AbstractController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private QuestionService questionService;

    @PostMapping("/addStudentApply")
    public Results addStudentApply(@Valid StudentApplyRequest studentApplyRequest, BindingResult result) {
        Long row = studentService.addStudentApply(studentApplyRequest);
        if (row == null) {
            return fail("添加失败");
        } else if (row < 0) {
            return fail("请勿重复添加");
        } else if (row == 0) {
            return fail("信息未录入");
        }
        return ok();
    }

    @GetMapping("/apply/{id}")
    public Results findImageById(@PathVariable long id) {
        StudentApplyEntity applyEntity=studentService.findImageById(id);
        if (applyEntity==null) {
            return fail("该学生不存在");
        }
        return ok(applyEntity);
    }

    @GetMapping
    public Results getStudentDetails(String saId) {
        StudentApplyResponse sar = studentService.getStudentDetails(saId);
        if (null != sar) {
            return ok(sar);
        }
        return fail(500, "没有学生信息");
    }

    @GetMapping("/{userId}")
    public Results findStudentTemplate(@PathVariable String userId) {
        List<UserStudentResponse> list = studentService.findStudentTemplate(userId);
        return ok(list);
    }

    @GetMapping("/findStudentDetails")
    public Results findStudentDetails(StudentRequest entity, Integer page, Integer limit) {
        List<UserStudentResponse> list = studentService.findStudentDetails(entity);
        return ok(list);
    }

    @PostMapping("import")
    public Results imports(MultipartFile excel) throws Exception {
        int ret = studentService.importStudent(excel.getOriginalFilename(), excel.getInputStream());
        if (ret < 0) {
            return fail("有学生身份证为空");
        }
        if (ret == 0) {
            return fail("导入失败");
        }
        return ok();
    }


    @GetMapping("export")
    public void export(StudentEntity entity, HttpServletResponse response) throws Exception {
        //根据条件查询 数据 进行导出
        response.setContentType("application/octet-stream");
        response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader("Content-Disposition", "attachment; filename=students.xlsx");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");
        OutputStream outputStream = response.getOutputStream();
        studentService.exportStudent(entity, response);
    }

    @GetMapping("export/answer")
    public void exportAnswer(StudentEntity entity, HttpServletResponse response) throws Exception {
        //根据条件查询 数据 进行导出
        response.setContentType("application/octet-stream");
        response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader("Content-Disposition", "attachment; filename=students.xlsx");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");
        questionService.export(entity, response);
    }

    @GetMapping("query")
    public Results query(StudentEntity entity, Integer page, Integer limit) {
        List<StudentResponse> entities = studentService.query(entity, page, limit);
        return ok(entities);
    }

//    @GetMapping("queryStudentApplyJoin")
//    public Results queryStudentApplyJoin(Integer page, Integer limit) {
//        List<StudentEntity> entities = studentService.queryStudentApplyJoin(page, limit);
//        return ok(entities);
//    }

    @GetMapping("findStudentExcelDetails")
    public Results findStudentExcelDetails(Integer id) {
        StudentResponse studentEntity = studentService.findStudentExcelDetails(id);
        if (null != studentEntity) {
            return ok(studentEntity);
        }
        return fail(500, "没有数据");
    }

    @GetMapping("findStudentExcelPage")
    public Results findStudentExcel(Integer id) {
        StudentResponse studentEntity = studentService.findStudentExcelDetails(id);
        if (null != studentEntity) {
            return ok(studentEntity);
        }
        return fail(500, "没有数据");
    }

    @PostMapping("updateStudentExcel")
    public Results updateStudentExcel(StudentEntity entity) {
        int row = studentService.updateStudentExcel(entity);
        if (row > 0) {
            return ok();
        }
        return fail(500, "更新失败");
    }

    @DeleteMapping
    public Results deleteStudent(Integer id) {
        if (null != id && id > 0) {
            int row = studentService.deleteStudent(id);
            if (row > 0)
                return ok();
        }
        return fail(500, "删除失败");
    }

    @GetMapping("sum")
    public Results sum() {
        return ok(studentService.sumStudents());
    }

    @PostMapping("/createStudent")
    public Results createStudent(StudentEntity entity) {
        int row = studentService.createStudent(entity);
        if (row > 0)
            return ok();
        return fail(500, "添加失败");
    }
}
