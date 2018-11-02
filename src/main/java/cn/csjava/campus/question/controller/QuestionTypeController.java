package cn.csjava.campus.question.controller;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.HttpResponseCodeEnum;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.question.entity.QuestionTypeEntity;
import cn.csjava.campus.question.service.QuestionTypeService;
import cn.csjava.campus.question.vo.QuestionTypeRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Api(description = "问卷类型")
@RestController
@RequestMapping("question/type")
public class QuestionTypeController extends AbstractController {

    @Autowired
    private QuestionTypeService questionTypeService;

    @PostMapping
    public Results save(@Valid QuestionTypeRequest.Add entity, HttpServletResponse response, BindingResult result) {
        QuestionTypeEntity questionTypeEntity = new QuestionTypeEntity();
        BeanUtils.copyProperties(entity, questionTypeEntity);

        int count = questionTypeService.save(questionTypeEntity);

        if (count == 0) {
            return fail(HttpResponseCodeEnum.INSERT_ERROR);
        }
        return ok();
    }

    @DeleteMapping("/{id}")
    public Results delete(@PathVariable Long id) {
        int ret = questionTypeService.delete(id);

        if (ret == 0) {
            return fail(HttpResponseCodeEnum.DELETE_ERROR);
        }
        return ok();
    }

    @GetMapping("/{id}")
    public Results findOne(@PathVariable Long id) {
        QuestionTypeEntity entity = questionTypeService.findOne(id);
        if (entity == null) {
            return fail(HttpResponseCodeEnum.NOT_FOUND_ERROR);
        }
        return ok(entity);
    }

    @GetMapping
    public Results query(QuestionTypeEntity entity, Integer page, Integer limit, HttpServletResponse response) {


        List<QuestionTypeEntity> list = questionTypeService.query(entity, page, limit);
        return ok(list);

    }

    @PutMapping
    public Results update(QuestionTypeEntity entity) {

        int update = questionTypeService.update(entity);
        if (update == 0) {
            return fail(HttpResponseCodeEnum.UPDATE_ERROR);
        }
        if (update == -1) {
            return fail(HttpResponseCodeEnum.PARAMS_ERROR, "类型已存在");
        }
        return ok();
    }


}
