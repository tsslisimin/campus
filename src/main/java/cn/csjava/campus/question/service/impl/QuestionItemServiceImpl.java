package cn.csjava.campus.question.service.impl;

import cn.csjava.campus.question.constant.QuestionItemConstant;
import cn.csjava.campus.question.entity.QuestionItemEntity;
import cn.csjava.campus.question.entity.QuestionTemplateEntity;
import cn.csjava.campus.question.entity.QuestionTypeEntity;
import cn.csjava.campus.question.mapper.QuestionItemMapper;
import cn.csjava.campus.question.mapper.QuestionTemplateMapper;
import cn.csjava.campus.question.mapper.QuestionTypeMapper;
import cn.csjava.campus.question.service.QuestionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Service
public class QuestionItemServiceImpl implements QuestionItemService {
    @Autowired
    private QuestionItemMapper questionItemMapper;
    @Autowired
    private QuestionTemplateMapper questionTemplateMapper;
    @Autowired
    private QuestionTypeMapper questionTypeMapper;

    @PostConstruct
    public void init() {
    }

    @Override
    public int save(QuestionItemEntity entity) {
        int templateCount = questionTemplateMapper.selectCount(QuestionTemplateEntity.builder()
                .id(entity.getTemplateId()).build());
        if (templateCount == 0) {
            // 模板不存在
            return 0;
        }
        int typeCount = questionTypeMapper.selectCount(QuestionTypeEntity.builder()
                .type(entity.getQuestionType()).build());

        if (typeCount == 0) {
            // 问卷类型不存在
            return 0;
        }
        entity.setCreateDate(new Date());
        entity.setStatus(QuestionItemConstant.STATUS_NORNOM);
        return (int) questionItemMapper.save(entity);

    }

    @Override
    public int delete(Long id) {
        return questionItemMapper.delete(QuestionItemEntity.builder().id(id).build());
    }

    @Override
    public QuestionItemEntity findOne(Long id) {
        return questionItemMapper.selectOne(QuestionItemEntity.builder().id(id).build());
    }

    @Override
    public List<QuestionItemEntity> query(QuestionItemEntity entity, Integer page, Integer count) {
        return questionItemMapper.selectByCondition(entity);
    }

    @Override
    public int update(QuestionItemEntity entity) {
        return questionItemMapper.updateByPrimaryKeyBySelective(entity);
    }
}
