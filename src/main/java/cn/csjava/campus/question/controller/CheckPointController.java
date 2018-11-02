package cn.csjava.campus.question.controller;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.HttpResponseCodeEnum;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.question.entity.CheckPointEntity;
import cn.csjava.campus.question.service.CheckPointService;
import cn.csjava.campus.question.vo.CheckPointRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Api(description = "校验规则")
@RestController
@RequestMapping("checkPoint")
public class CheckPointController extends AbstractController {

    @Autowired
    private CheckPointService checkPointService;

    @PostMapping
    public Results save(@RequestBody @Valid CheckPointRequest.Add entity, BindingResult result) {
        CheckPointEntity target = new CheckPointEntity();
        BeanUtils.copyProperties(entity, target);

        int count = checkPointService.save(target);

        if (count == 0) {
            return fail(HttpResponseCodeEnum.INSERT_ERROR);
        }
        return ok();
    }

    @DeleteMapping("/{id}")
    public Results delete(@PathVariable Long id) {
        int ret = checkPointService.delete(id);

        if (ret == 0) {
            return fail(HttpResponseCodeEnum.DELETE_ERROR);
        }
        return ok();
    }

    @GetMapping("/{id}")
    public Results findOne(@PathVariable Long id) {
        CheckPointEntity entity = checkPointService.findOne(id);
        if (entity == null) {
            return fail(HttpResponseCodeEnum.NOT_FOUND_ERROR);
        }
        return ok(entity);
    }

    @GetMapping
    public Results query(CheckPointEntity entity, Integer page, Integer count) {
        List<CheckPointEntity> ret = checkPointService.query(entity, page, count);
        return ok(ret);

    }

    @PutMapping
    public Results update(CheckPointEntity entity) {
        if (entity.getId() == null) {
            return fail(HttpResponseCodeEnum.PARAMS_ERROR);
        }
        int update = checkPointService.update(entity);
        if (update == 0) {
            return fail(HttpResponseCodeEnum.UPDATE_ERROR);
        }
        return ok();
    }


}
