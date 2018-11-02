package cn.csjava.campus.question.service.impl;

import cn.csjava.campus.question.constant.QuestionTemplateConstant;
import cn.csjava.campus.question.entity.QuestionAnswerEntity;
import cn.csjava.campus.question.entity.QuestionItemEntity;
import cn.csjava.campus.question.entity.QuestionItemOptionEntity;
import cn.csjava.campus.question.entity.QuestionTemplateEntity;
import cn.csjava.campus.question.mapper.QuestionAnswerMapper;
import cn.csjava.campus.question.mapper.QuestionItemMapper;
import cn.csjava.campus.question.mapper.QuestionItemOptionMapper;
import cn.csjava.campus.question.mapper.QuestionTemplateMapper;
import cn.csjava.campus.question.service.QuestionTemplateService;
import cn.csjava.campus.student.mapper.StudentApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class QuestionTemplateServiceImpl implements QuestionTemplateService {
    @Autowired
    private QuestionTemplateMapper questionTemplateMapper;
    @Autowired
    private QuestionItemMapper questionItemMapper;
    @Autowired
    private QuestionItemOptionMapper questionItemOptionMapper;
    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;
    @Autowired
    private StudentApplyMapper studentApplyMapper;

    @PostConstruct
    public void init() {
    }

    @Override
    public int save(QuestionTemplateEntity entity) {
        int status = entity.getStartDate().getTime() >= System.currentTimeMillis() ? QuestionTemplateConstant.STATUS_START : QuestionTemplateConstant.STATUS_NORNOM;
        entity.setStatus(status);
        entity.setCreateDate(new Date());
        return questionTemplateMapper.insert(entity);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        questionTemplateMapper.delete(QuestionTemplateEntity.builder().id(id).build());
        questionItemMapper.delete(QuestionItemEntity.builder().templateId(id).build());
        questionItemOptionMapper.delete(QuestionItemOptionEntity.builder().templateId(id).build());
        questionAnswerMapper.delete(QuestionAnswerEntity.builder().templateId(id).build());

        studentApplyMapper.unsetTemplateId(id);

        return 1;
    }

    @Override
    public QuestionTemplateEntity findOne(Long id) {
        return questionTemplateMapper.selectOne(QuestionTemplateEntity.builder().id(id).build());
    }

    @Override
    public List<QuestionTemplateEntity> query(QuestionTemplateEntity entity, Integer page, Integer count) {
        return questionTemplateMapper.select(entity);
    }

    @Override
    public int update(QuestionTemplateEntity entity) {
        return questionTemplateMapper.updateByPrimaryKeyBySelective(entity);
    }
}
