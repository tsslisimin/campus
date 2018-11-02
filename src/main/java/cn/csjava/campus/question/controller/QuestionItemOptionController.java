package cn.csjava.campus.question.controller;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.HttpResponseCodeEnum;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.question.entity.QuestionItemOptionEntity;
import cn.csjava.campus.question.service.QuestionItemOptionService;
import cn.csjava.campus.question.vo.QuestionItemOptionRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/9
 */
@Api(description = "问卷类型选项")
@RestController
@RequestMapping("question/item/option")
public class QuestionItemOptionController extends AbstractController {
    @Autowired
    private QuestionItemOptionService questionItemOptionService;

    @ApiImplicitParams({@ApiImplicitParam(
            name = "vo", value = "增加",
            dataType = "QuestionItemOptionRequest"
    )})
    @PostMapping
    public Results save(@RequestBody @Valid QuestionItemOptionRequest vo, BindingResult result) {
        QuestionItemOptionEntity entity = new QuestionItemOptionEntity();
        BeanUtils.copyProperties(vo, entity);
        String ret = questionItemOptionService.save(entity);
        if (ret != null) {
            return fail(ret);
        }
        return ok();
    }


    @DeleteMapping("/{id}")
    public Results delete(@PathVariable Long id) {
        int ret = questionItemOptionService.delete(id);

        if (ret == 0) {
            return fail(HttpResponseCodeEnum.DELETE_ERROR);
        }
        return ok();
    }

    @GetMapping("/{id}")
    public Results findOne(@PathVariable Long id) {
        QuestionItemOptionEntity entity = questionItemOptionService.findOne(id);
        if (entity == null) {
            return fail(HttpResponseCodeEnum.NOT_FOUND_ERROR);
        }
//        QuestionItemOptionRequest.Find target = new QuestionItemOptionRequest.Find(entity);
        return ok(entity);
    }

    @GetMapping
    public Results query(QuestionItemOptionEntity entity, Integer page, Integer count) {
        List<QuestionItemOptionEntity> list = questionItemOptionService.query(entity, page, count);
//        List<QuestionItemOptionRequest.Find> ret = list.stream().map(QuestionItemOptionRequest.Find::new)
//                .collect(Collectors.toList());
        return ok(list);

    }

    @PutMapping
    public Results update(QuestionItemOptionEntity entity) {

        int update = questionItemOptionService.update(entity);
        if (update == 0) {
            return fail(HttpResponseCodeEnum.UPDATE_ERROR);
        }
        return ok();
    }
}
