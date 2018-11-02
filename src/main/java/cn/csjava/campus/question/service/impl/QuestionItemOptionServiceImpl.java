package cn.csjava.campus.question.service.impl;

import cn.csjava.campus.question.entity.QuestionItemEntity;
import cn.csjava.campus.question.entity.QuestionItemOptionEntity;
import cn.csjava.campus.question.entity.QuestionTemplateEntity;
import cn.csjava.campus.question.entity.QuestionTypeEntity;
import cn.csjava.campus.question.mapper.QuestionItemMapper;
import cn.csjava.campus.question.mapper.QuestionItemOptionMapper;
import cn.csjava.campus.question.mapper.QuestionTemplateMapper;
import cn.csjava.campus.question.mapper.QuestionTypeMapper;
import cn.csjava.campus.question.service.QuestionItemOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Service
public class QuestionItemOptionServiceImpl implements QuestionItemOptionService {
    @Autowired
    private QuestionItemMapper questionItemMapper;
    @Autowired
    private QuestionTemplateMapper questionTemplateMapper;
    @Autowired
    private QuestionTypeMapper questionTypeMapper;
    @Autowired
    private QuestionItemOptionMapper questionItemOptionMapper;

    @Override
    public String save(QuestionItemOptionEntity entity) {
        int templateCount = questionTemplateMapper.selectCount(QuestionTemplateEntity.builder().id(entity.getTemplateId()).build());
        if (templateCount == 0) {
            return String.format("无此模版[%d]", entity.getQuestionItemId());
        }

        int itemCount = questionItemMapper.selectCount(QuestionItemEntity.builder()
                .id(entity.getQuestionItemId()).build());
        if (itemCount == 0) {
            return String.format("无此问卷[%d]", entity.getQuestionItemId());
        }

        questionItemOptionMapper.insert(entity);
        return null;
    }

    @Override
    public int delete(Long id) {
        return questionItemOptionMapper.delete(QuestionItemOptionEntity.builder().id(id).build());
    }

    @Override
    public QuestionItemOptionEntity findOne(Long id) {
        return questionItemOptionMapper.selectOne(QuestionItemOptionEntity.builder().id(id).build());
    }

    @Override
    public List<QuestionItemOptionEntity> query(QuestionItemOptionEntity entity, Integer page, Integer count) {
        return null;
    }

    @Override
    public int update(QuestionItemOptionEntity entity) {
        return 0;
    }
}
