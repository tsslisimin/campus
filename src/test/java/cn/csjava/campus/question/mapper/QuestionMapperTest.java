package cn.csjava.campus.question.mapper;

import cn.csjava.campus.question.entity.QuestionEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionMapperTest {
    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void findByStudentTemplateId() {
        List<QuestionEntity> list = questionMapper.findByStudentTemplateId(1L, 4L);

        System.out.println(list);
    }
}