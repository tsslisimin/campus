package cn.csjava.campus.question.service.impl;

import cn.csjava.campus.question.entity.QuestionTypeEntity;
import cn.csjava.campus.question.mapper.QuestionTypeMapper;
import cn.csjava.campus.question.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
    @Autowired
    private QuestionTypeMapper questionTypeMapper;

    @PostConstruct
    public void init() {
    }

    @Override
    public int save(QuestionTypeEntity entity) {
        QuestionTypeEntity condition = QuestionTypeEntity.builder().type(entity.getType()).build();
        QuestionTypeEntity exists = questionTypeMapper.selectOne(condition);
        if (exists != null) {
            return 0;
        }
        return questionTypeMapper.insert(entity);

    }

    @Override
    public int delete(Long id) {
        return questionTypeMapper.delete(QuestionTypeEntity.builder().id(id).build());
    }

    @Override
    public QuestionTypeEntity findOne(Long id) {
        return questionTypeMapper.selectOne(QuestionTypeEntity.builder().id(id).build());
    }

    @Override
    public List<QuestionTypeEntity> query(QuestionTypeEntity entity, Integer page, Integer count) {
        return questionTypeMapper.select(entity);
    }

    @Override
    public int update(QuestionTypeEntity entity) {
        int count = questionTypeMapper.selectCount(QuestionTypeEntity.builder().type(entity.getType()).build());
        if (count > 0) {
            return -1;
        }

        return questionTypeMapper.updateByPrimaryKeyBySelective(entity);
    }
}
