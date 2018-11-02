package cn.csjava.campus.question.controller;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.HttpResponseCodeEnum;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.question.entity.QuestionItemEntity;
import cn.csjava.campus.question.service.QuestionItemService;
import cn.csjava.campus.question.vo.QuestionItemRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@Api(description = "问卷")
@RestController
@RequestMapping("question/item")
public class QuestionItemController extends AbstractController {

    @Autowired
    private QuestionItemService questionItemService;

    @ApiImplicitParams({@ApiImplicitParam(
            name = "entity", value = "增加",
            dataType = "QuestionItemRequest.Add"
    )})
    @PostMapping
    public Results save(@RequestBody @Valid QuestionItemRequest.Add entity, BindingResult result) {
        QuestionItemEntity target = new QuestionItemEntity();
        BeanUtils.copyProperties(entity, target);

        int count = questionItemService.save(target);

        if (count == 0) {
            return fail(HttpResponseCodeEnum.INSERT_ERROR);
        }
        return ok();
    }

    @DeleteMapping("/{id}")
    public Results delete(@PathVariable Long id) {
        int ret = questionItemService.delete(id);

        if (ret == 0) {
            return fail(HttpResponseCodeEnum.DELETE_ERROR);
        }
        return ok();
    }

    @GetMapping("/{id}")
    public Results findOne(@PathVariable Long id) {
        QuestionItemEntity entity = questionItemService.findOne(id);
        if (entity == null) {
            return fail(HttpResponseCodeEnum.NOT_FOUND_ERROR);
        }
        QuestionItemRequest.Find target = new QuestionItemRequest.Find();
        BeanUtils.copyProperties(entity, target);
        return ok(target);
    }

    @GetMapping
    public Results query(QuestionItemEntity entity, Integer page, Integer count) {
        List<QuestionItemEntity> list = questionItemService.query(entity, page, count);
        List<QuestionItemEntity> ret = list.stream().map(QuestionItemEntity::new).collect(Collectors.toList());
        return ok(ret);

    }

    @PutMapping
    public Results update(QuestionItemEntity entity) {
        if (entity.getId() == null) {
            return fail(HttpResponseCodeEnum.PARAMS_ERROR);
        }
        int update = questionItemService.update(entity);
        if (update == 0) {
            return fail(HttpResponseCodeEnum.UPDATE_ERROR);
        }
        return ok();
    }


}
