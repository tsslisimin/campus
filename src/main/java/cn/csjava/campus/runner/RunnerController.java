package cn.csjava.campus.runner;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.question.entity.QuestionItemEntity;
import cn.csjava.campus.question.entity.QuestionItemOptionEntity;
import cn.csjava.campus.question.enums.QuestionTypeEnum;
import cn.csjava.campus.question.mapper.QuestionItemMapper;
import cn.csjava.campus.question.mapper.QuestionItemOptionMapper;
import cn.csjava.campus.question.mapper.QuestionTemplateMapper;
import cn.csjava.campus.question.mapper.QuestionTypeMapper;
import cn.csjava.campus.question.service.QuestionItemOptionService;
import cn.csjava.campus.question.service.QuestionItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/9
 */
@Api(description = "系统初始调用")
@RestController
@RequestMapping("runner")
public class RunnerController extends AbstractController {
    @Autowired
    private QuestionItemService questionItemService;

    @Autowired
    private QuestionItemOptionService questionItemOptionService;

    @GetMapping
    public Results execute() {
//        questionItemService.save(QuestionItemEntity.builder()
//                .editorType(1)
//                .createDate(new Date())
//                .index(1)
//                .status(1)
//                .templateId(1L)
//                .describes("学生姓名：")
//                .questionType(QuestionTypeEnum.SINGLE_SELECTION.getType())
//                .build());

        questionItemOptionService.save(QuestionItemOptionEntity.builder()
                .content("男")
                .questionIndex(1)
                .questionItemId(2L)
                .templateId(1L).build());


        return ok();
    }

}
