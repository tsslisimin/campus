package cn.csjava.campus.question.service.impl;

import cn.csjava.campus.question.dto.AnswerExport;
import cn.csjava.campus.question.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionServiceImplTest {
@Autowired private QuestionService questionService;
    @Test
    public void export() {
//        List<AnswerExport> export = questionService.export(entity, response);
//        System.out.println(export);
    }
}