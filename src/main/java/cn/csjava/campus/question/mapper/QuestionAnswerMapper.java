package cn.csjava.campus.question.mapper;

import cn.csjava.campus.common.base.BaseMapper;
import cn.csjava.campus.question.entity.QuestionAnswerEntity;
import cn.csjava.campus.question.entity.QuestionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/13
 */
@Component
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswerEntity> {

    void deleteByTemplateId(@Param("templateId") Long templateId, @Param("saId") Long saId);
}
