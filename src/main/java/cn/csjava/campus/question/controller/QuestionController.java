package cn.csjava.campus.question.controller;

import cn.csjava.campus.common.base.AbstractController;
import cn.csjava.campus.common.options.map.ObjectHashMap;
import cn.csjava.campus.common.results.Results;
import cn.csjava.campus.question.service.QuestionService;
import cn.csjava.campus.question.vo.QuestionRequest;
import cn.csjava.campus.student.services.StudentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/9
 */
@Api(description = "问卷")
@RestController
@RequestMapping("question")
public class QuestionController extends AbstractController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public Results save(@RequestBody QuestionRequest vo) {
        int ret = questionService.save(1, null);
        if (ret == 0) {
            return fail("添加失败");
        }
        return ok();
    }

    @PostMapping("all")
    public Results saveAll(String templateName, Integer sign, String body) throws IOException {
        String json = new String(Base64Utils.decode(body.getBytes()), Charset.forName("UTF-8"));
        List<QuestionRequest.Item> vo = objectMapper.readValue(json, new TypeReference<List<QuestionRequest.Item>>() {
        });
        ObjectHashMap map = (ObjectHashMap) new ObjectHashMap("vo", vo)
                .append("templateId", 1)
                .append("templateName", templateName);
        questionService.save(sign, map);
        return ok(body);
    }

    @PostMapping("edit/{id}")
    public Results editAll(@PathVariable long id, String templateName, int sign, String body) throws IOException {
        String json = new String(Base64Utils.decode(body.getBytes()), Charset.forName("UTF-8"));
        List<QuestionRequest.Item> vo = objectMapper.readValue(json, new TypeReference<List<QuestionRequest.Item>>() {
        });
        ObjectHashMap map = (ObjectHashMap) new ObjectHashMap("vo", vo).append("templateName", templateName)
                .append("id", id).append("sign", sign);
        int edit = questionService.edit(map);
        if (edit == 0) {
            return fail("修改失败");
        }
        return ok(body);
    }

    @PostMapping("commit")
    public Results commit(long saId, String url, String userId, String body) throws IOException {
        String json = new String(Base64Utils.decode(body.getBytes()), Charset.forName("UTF-8"));
        List<ObjectHashMap> vo = objectMapper.readValue(json, new TypeReference<List<ObjectHashMap>>() {
        });
        int commit = questionService.commit(saId, userId, url, vo);
        if (commit == 1) {
            return ok();
        }
        return fail("请勿重复提交");
    }

    @GetMapping("/{id}")
    public Results findById(@PathVariable long id) {
        return ok(questionService.findById(id));
    }

    @GetMapping("answer")
    public Results findAnswerById(long templateId, long saId) {
        return ok(questionService.findByStudentTemplateId(templateId, saId));
    }
}
