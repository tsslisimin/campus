package cn.csjava.campus.question.controller;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.HttpResponseCodeEnum;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.question.entity.QuestionTemplateEntity;
import cn.csjava.campus.question.service.QuestionTemplateService;
import cn.csjava.campus.question.vo.QuestionTemplateRequest;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Api(description = "问卷模版")
@RestController
@RequestMapping("question/template")
public class QuestionTemplateController extends AbstractController {

    @Autowired
    private QuestionTemplateService questionTemplateService;

    @PostMapping
    public Results save(@RequestBody @Valid QuestionTemplateRequest.Add entity, BindingResult result) {
        QuestionTemplateEntity target = new QuestionTemplateEntity();
        BeanUtils.copyProperties(entity, target);

        int count = questionTemplateService.save(target);

        if (count == 0) {
            return fail(HttpResponseCodeEnum.INSERT_ERROR);
        }
        return ok();
    }

    @DeleteMapping("/{id}")
    public Results delete(@PathVariable Long id) {
        int ret = questionTemplateService.delete(id);

        if (ret == 0) {
            return fail(HttpResponseCodeEnum.DELETE_ERROR);
        }
        return ok();
    }

    @GetMapping("/{id}")
    public Results findOne(@PathVariable Long id) {
        QuestionTemplateEntity entity = questionTemplateService.findOne(id);
        if (entity == null) {
            return fail(HttpResponseCodeEnum.NOT_FOUND_ERROR);
        }
        QuestionTemplateRequest.Find target = new QuestionTemplateRequest.Find(entity);
        target.setStartDate(new DateTime(entity.getStartDate()).toString("yyyy-MM-dd HH:mm:ss"));
        target.setEndDate(new DateTime(entity.getEndDate()).toString("yyyy-MM-dd HH:mm:ss"));
        return ok(target);
    }

    @GetMapping
    public Results query(QuestionTemplateEntity entity, Integer page, Integer count) {
        List<QuestionTemplateEntity> list = questionTemplateService.query(entity, page, count);
        List<QuestionTemplateRequest.Find> ret = list.stream().map(QuestionTemplateRequest.Find::new)
                .collect(Collectors.toList());
        return ok(ret);

    }

    @PutMapping
    public Results update(QuestionTemplateEntity entity) {

        int update = questionTemplateService.update(entity);
        if (update == 0) {
            return fail(HttpResponseCodeEnum.UPDATE_ERROR);
        }
        return ok();
    }


}
